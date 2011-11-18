package org.framework42.dao;

import org.apache.log4j.Logger;
import org.framework42.database.DatabaseConnector;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;

public abstract class ProxyDAO<T> {

    protected final Logger logger;

    protected final DatabaseConnector databaseConnector;

    protected ProxyDAO(String loggerName, DatabaseConnector databaseConnector) {

        logger = Logger.getLogger(loggerName);
        this.databaseConnector = databaseConnector;
    }

    @SuppressWarnings("unchecked")
    public T generateProxy() {

        T oldInstance = (T) this;

        return (T) Proxy.newProxyInstance(
                oldInstance.getClass().getClassLoader(),
                oldInstance.getClass().getInterfaces(),
                new DAOHandler<T>(oldInstance)
        );

    }

    public String generateSQLErrorMessage(SQLException e, Class daoClass, Method method) throws RuntimeException {

        StringBuilder msgBuilder = new StringBuilder(100);
        msgBuilder.append(daoClass.getName());
        msgBuilder.append(".");
        if(method != null) {
            msgBuilder.append(method.getName());
        }
        msgBuilder.append(e);

        String msg = msgBuilder.toString();

        logger.error(msg);
        
        return msg;

    }

}
