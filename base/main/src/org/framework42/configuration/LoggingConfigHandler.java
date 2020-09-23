package org.framework42.configuration;

import org.apache.log4j.PropertyConfigurator;

import java.util.Properties;

public enum LoggingConfigHandler {

    INSTANCE;

    private LoggingConfigHandler() {
    }

    public void load(String nameOfConfigFile) {

        try{
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream(nameOfConfigFile));
            PropertyConfigurator.configure(props);
        } catch(Exception e) {

            try{

                PropertyConfigurator.configure(System.getProperty("java.io.tmpdir")+System.getProperty("file.separator")+nameOfConfigFile);
            } catch(Exception ex) {

                throw new RuntimeException("Problem loading logging settings file for application: " + e + "\n" + ex);
            }
        }
    }

}

