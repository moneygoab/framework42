package org.framework42;

import org.framework42.database.DatabaseConnector;
import org.framework42.utils.TimeSource;

import java.util.Locale;
import java.util.Properties;

/**
 * This is the interface that your service binder implementations must implement.
 * */
public interface ServiceBinderInterface {

    /**
     * This method should load the configuration information of your application and store it in a configuration registry.
     * */
    public void loadConfigFiles();

    /**
     * This method returns a properties object that contains all the configuration of your application.
     * @return The properties object.
     * */
    public Properties getApplicationProperties();

    /**
     * This method returns the default locale of your application.
     * @return the default locale.
     * */
    public Locale getDefaultLocale();

    /**
     * This method returns the database connector of your application.
     * @return The database connector.
     * */
    public DatabaseConnector getDatabaseConnector();

    public TimeSource getTimeSource();

}
