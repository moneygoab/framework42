package org.framework42.dao;

import org.apache.log4j.Logger;
import org.framework42.database.DatabaseConnector;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;

import static org.framework42.utils.NullChecker.notNull;

public abstract class ProxyDAO<T> {

    protected final Logger logger;

    protected final DatabaseConnector databaseConnector;

    protected ProxyDAO(String loggerName, DatabaseConnector databaseConnector) {

        logger = Logger.getLogger(loggerName);
        this.databaseConnector = notNull(databaseConnector);
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

        return generateSQLErrorMessage(e, daoClass, method, "", true);

    }

    public String generateSQLErrorMessage(SQLException e, Class daoClass, Method method, boolean logError) throws RuntimeException {

        return generateSQLErrorMessage(e, daoClass, method, "", logError);

    }

    public String generateSQLErrorMessage(SQLException e, Class daoClass, Method method, String customErrorMessage) throws RuntimeException {

        return generateSQLErrorMessage(e, daoClass, method, "", true);
    }

    public String generateSQLErrorMessage(SQLException e, Class daoClass, Method method, String customErrorMessage, boolean logError) throws RuntimeException {

        StringBuilder msgBuilder = new StringBuilder(100);
        msgBuilder.append(daoClass.getName());
        msgBuilder.append(".");
        if(method != null) {
            msgBuilder.append(method.getName());
        }
        msgBuilder.append(e);

        msgBuilder.append(". ");
        msgBuilder.append(customErrorMessage);

        String msg = msgBuilder.toString();

        if(logError) {
            logger.error(msg);
        }

        return msg;

    }

}
