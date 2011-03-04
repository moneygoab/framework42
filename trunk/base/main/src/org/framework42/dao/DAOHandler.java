package org.framework42.dao;

import org.apache.log4j.Logger;
import org.framework42.utils.AbstractNullChecker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DAOHandler<T> extends AbstractNullChecker implements InvocationHandler {

    protected T delegate;

    private final Logger logger = Logger.getLogger("org.framework42.base");

    public DAOHandler(T delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] argumentList) throws Throwable {

        try {

            notNullChecks(method, argumentList);

            return method.invoke(delegate, argumentList);

        } catch (InvocationTargetException e) {
            
            String msg = method.getDeclaringClass().getName()+"."+method.getName() + " - " + e.getCause();
            logger.fatal(msg);
            throw e.getCause();
        }
    }

}
