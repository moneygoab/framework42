package org.framework42.examples.hello_servicebinder;

import org.apache.log4j.BasicConfigurator;
import org.framework42.ServiceBinderInterface;
import org.framework42.configuration.ApplicationConfigHandler;
import org.framework42.database.DatabaseConnector;

import java.util.Locale;
import java.util.Properties;

public enum HelloServiceBinder implements ServiceBinderInterface {

    INSTANCE();

    private DatabaseConnector databaseConnector;

    private Locale defaultLocale;

    private Properties applicationProperties;

    private HelloServiceBinder() {

        loadConfigFiles();

        databaseConnector = DatabaseConnector.INSTANCE;

        defaultLocale = Locale.getDefault();
        
        BasicConfigurator.configure();

    }

    @Override
    public void loadConfigFiles() {

        applicationProperties = ApplicationConfigHandler.INSTANCE.load("hello_service_binder_settings.xml");

    }

    @Override
    public Properties getApplicationProperties() {
        return applicationProperties;
    }

    @Override
    public Locale getDefaultLocale() {
        return defaultLocale;
    }

    @Override
    public DatabaseConnector getDatabaseConnector() {
        return databaseConnector;
    }

}
