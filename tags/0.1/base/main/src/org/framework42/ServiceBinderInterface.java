package org.framework42;

import org.framework42.database.DatabaseConnector;

import java.util.Locale;
import java.util.Properties;

public interface ServiceBinderInterface {

    public void loadConfigFiles();

    public Properties getApplicationProperties();

    public Locale getDefaultLocale();

    public DatabaseConnector getDatabaseConnector();

}
