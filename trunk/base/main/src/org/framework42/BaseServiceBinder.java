package org.framework42;

import org.apache.log4j.Logger;
import org.framework42.configuration.ApplicationConfigHandler;
import org.framework42.configuration.LoggingConfigHandler;
import org.framework42.database.DatabaseConnector;
import org.framework42.utils.TimeSource;
import org.framework42.utils.TimeSourceControllable;
import org.framework42.utils.TimeSourceSystem;

import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Properties;

public abstract class BaseServiceBinder implements ServiceBinderInterface {

    protected TimeSource timeSource;

    protected final Logger logger;

    protected Properties properties;

    protected String systemUserSalt;

    protected Locale locale;

    protected boolean allowUndefinedParameters;

    protected DatabaseConnector databaseConnector;

    public BaseServiceBinder(String loggerId) {

        this.logger = Logger.getLogger(loggerId);

        locale = Locale.getDefault();
        allowUndefinedParameters = false;

        loadConfigFiles();
    }

    @Override
    public void loadConfigFiles() {

        properties = ApplicationConfigHandler.INSTANCE.load("application-settings.xml");

        setUpProperties();

        LoggingConfigHandler.INSTANCE.load("log4j.xml");
    }

    private void setUpProperties() {

        locale = new Locale(properties.getProperty("defaultLanguage"), properties.getProperty("defaultCountry"));

        systemUserSalt = properties.getProperty("system.user.salt");
        allowUndefinedParameters = Boolean.parseBoolean(properties.getProperty("system.allowUndefinedParameters"));

        if("Controllable".equalsIgnoreCase(properties.getProperty("system.timeSource"))) {

            logger.info("Controllable time source activated!");
            timeSource = new TimeSourceControllable(new GregorianCalendar());

        } else {

            logger.info("System default time source used.");
            timeSource = new TimeSourceSystem();
        }

        setUpSpecificProperties();
    }

    protected abstract void setUpSpecificProperties();

    @Override
    public Properties getApplicationProperties() {
        return properties;
    }

    @Override
    public Locale getDefaultLocale() {
        return locale;
    }

    @Override
    public DatabaseConnector getDatabaseConnector() {
        return databaseConnector;
    }

    @Override
    public TimeSource getTimeSource() {
        return timeSource;
    }

    public boolean isAllowUndefinedParameters() {
        return allowUndefinedParameters;
    }

}
