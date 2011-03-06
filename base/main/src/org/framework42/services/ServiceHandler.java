package org.framework42.services;

import org.apache.log4j.Logger;
import org.framework42.annotations.Authorization;
import org.framework42.authorization.AuthType;
import org.framework42.authorization.UserAuthAction;
import org.framework42.authorization.UserAuthPerformer;
import org.framework42.exceptions.NotAuthorizedException;
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

    public Object invoke(Object proxy, Method method, Object[] argumentList) throws Throwable {

        try {

            notNullChecks(method, argumentList);

            authorization(method, argumentList);

            return method.invoke(delegate, argumentList);

        } catch (InvocationTargetException e) {
            String msg = method.getDeclaringClass().getName()+"."+method.getName() + " - " + e.getCause();
            logger.fatal(msg);
            throw e.getCause();
        }
    }

    private void authorization(Method method, Object[] argumentList) throws NotAuthorizedException {

        for(Annotation annotation: method.getAnnotations()) {

            if(annotation.annotationType() == Authorization.class) {

                performAuthorization((Authorization)annotation, method, argumentList);
            }
        }
    }

    private void performAuthorization(Authorization authAnnotation, Method method, Object[] argumentList) throws NotAuthorizedException {

        if(authAnnotation.authType() == AuthType.USER_AUTH) {

            UserAuthPerformer userAuthPerformer = new UserAuthPerformer(
                    getInvocationUser(method, argumentList),
                    Arrays.asList(authAnnotation.accessRoles()),
                    Arrays.asList(authAnnotation.denyRoles())
            );

            userAuthPerformer.authorize(UserAuthAction.HAS_VALID_ROLE);

        } else if(authAnnotation.authType() == AuthType.SPECIAL_SUB_CLASSED) {

            subClassedAuthorization(authAnnotation, method, argumentList, getInvocationUser(method, argumentList));
            
        }
    }

    protected void subClassedAuthorization(Authorization authAnnotation, Method method, Object[] argumentList, User invocationUser) throws NotAuthorizedException {}

    private User getInvocationUser(Method method, Object[] argumentList) {

        for(Object obj: argumentList) {

            if(obj instanceof User) {

                return (User)obj;
            }
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

}
