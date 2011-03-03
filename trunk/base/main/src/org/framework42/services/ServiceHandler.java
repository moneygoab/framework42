package org.framework42.services;

import org.framework42.annotations.AllowNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.framework42.utils.NullChecker.notNull;

public class ServiceHandler<T> implements InvocationHandler {

    protected T delegate;

    public ServiceHandler(T delegate) {
        this.delegate = delegate;
    }

    public Object invoke(Object proxy, Method method, Object[] argumentList) throws Throwable {

        try {

            notNullChecks(method, argumentList);

            Object result = method.invoke(delegate, argumentList);
            return result;

        } catch (InvocationTargetException e) {

            throw e.getTargetException();

        } finally {


        }

    }

    private void notNullChecks(Method method, Object[] argumentList) {

        int i = 1;
        for(Object argument: argumentList) {

            if(checkNull(method.getParameterAnnotations()[i-1])) {
                notNull(argument, generateErrorMessage(method, i));
            }
            i++;
        }
    }

    private boolean checkNull(Annotation[] parameterAnnotations) {

        for(Annotation annotation: parameterAnnotations) {

            if(annotation.annotationType() == AllowNull.class) {

                return false;
            }
        }

        return true;
    }

    private String generateErrorMessage(Method method, int parameterId) {

        StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("Parameter number ");
            stringBuilder.append(parameterId);
            stringBuilder.append(" in class ");
            stringBuilder.append(method.getDeclaringClass().getName());
            stringBuilder.append(" and method ");
            stringBuilder.append(method.getName());
            stringBuilder.append(" can't be null!");

        return stringBuilder.toString();        
    }

}
