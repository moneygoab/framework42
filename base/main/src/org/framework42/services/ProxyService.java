package org.framework42.services;

import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class ProxyService<T> {

    protected final Logger logger;

    protected ProxyService(String loggerId) {
        this.logger = Logger.getLogger(loggerId);
    }

    @SuppressWarnings("unchecked")
    public T generateProxy() {

        T oldInstance = (T) this;

        return (T) Proxy.newProxyInstance(
                oldInstance.getClass().getClassLoader(),
                oldInstance.getClass().getInterfaces(),
                getServiceHandler()
        );

    }
    
    @SuppressWarnings("unchecked")
    /**
     * Override this method if you want to provide a non standard handler
     * */
    protected ServiceHandler getServiceHandler() {

        return new ServiceHandler<T>((T) this);
    }



}
