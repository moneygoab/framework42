package org.framework42.services;

import java.lang.reflect.Proxy;

public abstract class ProxyService<T> {

    @SuppressWarnings("unchecked")
    public T generateProxy() {

        T oldInstance = (T) this;

        return (T) Proxy.newProxyInstance(
                oldInstance.getClass().getClassLoader(),
                oldInstance.getClass().getInterfaces(),
                new ServiceHandler<T>(oldInstance)
        );

    }  

}
