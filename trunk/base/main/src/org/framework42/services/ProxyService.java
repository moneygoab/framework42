package org.framework42.services;

import java.lang.reflect.Proxy;

public abstract class ProxyService<T> {

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
