package org.framework42.examples.hello_servicebinder;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
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

        setupDatabaseConnector();

        defaultLocale = Locale.getDefault();
        
        BasicConfigurator.configure();

    }

    @Override
    public void loadConfigFiles() {

        applicationProperties = ApplicationConfigHandler.INSTANCE.load("hello_service_binder_settings.xml");

    }

    private void setupDatabaseConnector() {

        databaseConnector = DatabaseConnector.INSTANCE;

        String server = applicationProperties.getProperty("database.server");
        String port = applicationProperties.getProperty("database.port");
        String database = applicationProperties.getProperty("database.name");
        String user = applicationProperties.getProperty("database.user");
        String password = applicationProperties.getProperty("database.password");

        MysqlConnectionPoolDataSource cpds= new MysqlConnectionPoolDataSource();
        cpds.setUrl("jdbc:mysql://"+server+":"+port+"/"+database);
        cpds.setPassword(password);
        cpds.setUser(user);
        
        databaseConnector.setUpEnvironment(cpds, 5);

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
