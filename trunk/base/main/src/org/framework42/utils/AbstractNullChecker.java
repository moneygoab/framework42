package org.framework42.utils;

import org.framework42.annotations.AllowNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import static org.framework42.utils.NullChecker.notNull;

public abstract class AbstractNullChecker {

    protected void notNullChecks(Method method, Object[] argumentList) {

        int i = 1;
        for(Object argument: argumentList) {

            if(shouldCheckNull(method.getParameterAnnotations()[i-1])) {

                notNull(argument, generateErrorMessage(method, i));
            }
            i++;
        }
    }

    private boolean shouldCheckNull(Annotation[] parameterAnnotations) {

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
