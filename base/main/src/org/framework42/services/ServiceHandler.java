package org.framework42.services;

import org.apache.log4j.Logger;
import org.framework42.annotations.Authorization;
import org.framework42.annotations.Executor;
import org.framework42.annotations.Executors;
import org.framework42.annotationsinterface.ExecutorListener;
import org.framework42.annotationsinterface.ExecutorObject;
import org.framework42.authorization.AuthType;
import org.framework42.authorization.UserAuthAction;
import org.framework42.authorization.UserAuthPerformer;
import org.framework42.exceptions.NotAuthorizedException;
import org.framework42.model.service.ExecuteRunType;
import org.framework42.model.users.User;
import org.framework42.utils.AbstractNullChecker;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ServiceHandler<T> extends AbstractNullChecker implements InvocationHandler {

    protected T delegate;

    private final Logger logger = Logger.getLogger("org.framework42.base");

    public ServiceHandler(T delegate) {

        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] argumentList) throws Throwable {

        try {

            notNullChecks(method, argumentList);
            authorization(method, argumentList);

            execute(method, argumentList, ExecuteRunType.BEFORE);

            Object object = method.invoke(delegate, argumentList);

            execute(method, argumentList, ExecuteRunType.AFTER);

            return object;

        } catch (InvocationTargetException e) {
            String msg = method.getDeclaringClass().getName() + "." + method.getName() + " - " + e.getCause();
            logger.fatal(msg);
            throw e.getCause();
        }
    }

    private void execute(Method method, Object[] argumentList, ExecuteRunType type) {
        for (Annotation annotation : method.getAnnotations()) {
            if (annotation.annotationType() == Executors.class) {

                for (Executor executor : ((Executors) annotation).value()) {

                    ExecutorObject executorObject = null;

                    for (Object object : argumentList) {
                        if (object instanceof ExecutorObject) {
                            executorObject = (ExecutorObject) object;
                            break;
                        }
                    }

                    if (executor.parameters()) {
                        if (executorObject == null) {
                            String message = generateExecutorErrorMessage(method);
                            logger.fatal(message);
                            throw new IllegalArgumentException(message);
                        }
                    }

                    try {
                        if (executor.runType() == ExecuteRunType.BOTH) {
                            execute(executor.id(), executorObject, method, type);
                        } else if (executor.runType() == type) {
                            execute(executor.id(), executorObject, method, type);
                        }
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                        logger.error("Invoke Execute error -- " + ex.getMessage());
                    }

                }
            }
        }
    }

    private void execute(int id, ExecutorObject object,Method method, ExecuteRunType type) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        try{
            ExecutorListener listener = (ExecutorListener)delegate;
            listener.invokeMethod(id,object,method,type);
        }catch (Exception ex){
            logger.error("Invoke Execute error Class not implementing ExecutorListener -- " + ex.getMessage());
        }
    }

    private void authorization(Method method, Object[] argumentList) throws NotAuthorizedException {

        for (Annotation annotation : method.getAnnotations()) {

            if (annotation.annotationType() == Authorization.class) {

                performAuthorization((Authorization) annotation, method, argumentList);
            }
        }
    }

    private void performAuthorization(Authorization authAnnotation, Method method, Object[] argumentList) throws NotAuthorizedException {

        if (authAnnotation.authType() == AuthType.USER_AUTH) {

            UserAuthPerformer userAuthPerformer = new UserAuthPerformer(
                    getInvocationUser(method, argumentList),
                    Arrays.asList(authAnnotation.accessRoles()),
                    Arrays.asList(authAnnotation.denyRoles())
            );

            userAuthPerformer.authorize(UserAuthAction.HAS_VALID_ROLE, method.getDeclaringClass().getCanonicalName() + ":" + method.getName());

        } else if (authAnnotation.authType() == AuthType.SPECIAL_SUB_CLASSED) {

            subClassedAuthorization(authAnnotation, method, argumentList, getInvocationUser(method, argumentList));

        }
    }

    protected void subClassedAuthorization(Authorization authAnnotation, Method method, Object[] argumentList, User invocationUser) throws NotAuthorizedException {
    }

    private User getInvocationUser(Method method, Object[] argumentList) {

        try {
            for (Object obj : argumentList) {

                if (obj instanceof User) {

                    return (User) obj;
                }
            }
        } catch (NullPointerException e) {

            logger.fatal(method.getName() + " has authorization annotation and must have a invocation user as in parameter!");
        }

        String message = generateInvocationUserErrorMessage(method);
        logger.fatal(message);
        throw new IllegalArgumentException(message);
    }

    private String generateInvocationUserErrorMessage(Method method) {

        StringBuilder messageBuilder = new StringBuilder();

        messageBuilder.append("No invocation user sent into method ");
        messageBuilder.append(method.getName());
        messageBuilder.append(" of class ");
        messageBuilder.append(method.getDeclaringClass().getName());

        return messageBuilder.toString();
    }

    private static String generateExecutorErrorMessage(Method method) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Missing Executor object for method ");
        messageBuilder.append(method.getName());
        messageBuilder.append(" of class ");
        messageBuilder.append(method.getDeclaringClass().getName());
        return messageBuilder.toString();
    }
}
