package org.framework42.utils;

import org.framework42.annotations.AllowNull;
import org.framework42.annotations.GreaterThenZero;
import org.framework42.annotations.NotZero;
import org.framework42.annotationsinterface.ExecutorObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import static org.framework42.utils.GreaterThenZeroChecker.greaterThenZero;
import static org.framework42.utils.NullChecker.notNull;
import static org.framework42.utils.ZeroChecker.notZero;

public abstract class AbstractNullChecker {

    protected void notNullChecks(Method method, Object[] argumentList) {

        if(argumentList!=null) {
            int i = 1;
            for(Object argument: argumentList) {

                if(shouldCheckNull(method.getParameterAnnotations()[i-1]) && !(argument instanceof ExecutorObject)) {

                    notNull(argument, generateErrorMessage(method, i));
                }

                if(shouldCheckZero(method.getParameterAnnotations()[i-1])) {

                    if(argument instanceof Integer) {
                        notZero((Integer)argument, generateZeroCheckErrorMessage(method, i));
                    } else if(argument instanceof Float) {
                        notZero((Float)argument, generateZeroCheckErrorMessage(method, i));
                    } else if(argument instanceof Long) {
                        notZero((Long)argument, generateZeroCheckErrorMessage(method, i));
                    } if(argument instanceof Double) {
                        notZero((Double)argument, generateZeroCheckErrorMessage(method, i));
                    }

                } else if(shouldCheckGreaterThenZero(method.getParameterAnnotations()[i-1])) {

                    if(argument instanceof Integer) {
                        greaterThenZero((Integer)argument, generateGreaterThenZeroCheckErrorMessage(method, i));
                    } else if(argument instanceof Float) {
                        greaterThenZero((Float)argument, generateGreaterThenZeroCheckErrorMessage(method, i));
                    } else if(argument instanceof Long) {
                        greaterThenZero((Long)argument, generateGreaterThenZeroCheckErrorMessage(method, i));
                    } if(argument instanceof Double) {
                        greaterThenZero((Double)argument, generateGreaterThenZeroCheckErrorMessage(method, i));
                    }
                }

                i++;
            }
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

    private boolean shouldCheckZero(Annotation[] parameterAnnotations) {

        for(Annotation annotation: parameterAnnotations) {

            if(annotation.annotationType() == NotZero.class) {

                return true;
            }
        }

        return false;
    }

    private boolean shouldCheckGreaterThenZero(Annotation[] parameterAnnotations) {

        for(Annotation annotation: parameterAnnotations) {

            if(annotation.annotationType() == GreaterThenZero.class) {

                return true;
            }
        }

        return false;
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

    private String generateZeroCheckErrorMessage(Method method, int parameterId) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Parameter number ");
        stringBuilder.append(parameterId);
        stringBuilder.append(" in class ");
        stringBuilder.append(method.getDeclaringClass().getName());
        stringBuilder.append(" and method ");
        stringBuilder.append(method.getName());
        stringBuilder.append(" can't be 0!");

        return stringBuilder.toString();
    }

    private String generateGreaterThenZeroCheckErrorMessage(Method method, int parameterId) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Parameter number ");
        stringBuilder.append(parameterId);
        stringBuilder.append(" in class ");
        stringBuilder.append(method.getDeclaringClass().getName());
        stringBuilder.append(" and method ");
        stringBuilder.append(method.getName());
        stringBuilder.append(" can't be less then 0!");

        return stringBuilder.toString();
    }

}
