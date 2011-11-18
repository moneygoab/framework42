package org.framework42.configuration;

import org.apache.log4j.PropertyConfigurator;

public enum LoggingConfigHandler {

    INSTANCE;

    private LoggingConfigHandler() {
    }

    public void load(String nameOfConfigFile) {

        try{

            String baseUrl = getClass().getClassLoader().getResource("").getPath();
            baseUrl = baseUrl.replaceAll("%20", " ");
            PropertyConfigurator.configure(baseUrl+nameOfConfigFile);
        } catch(Exception e) {

            try{

                PropertyConfigurator.configure(System.getProperty("java.io.tmpdir")+System.getProperty("file.separator")+nameOfConfigFile);
            } catch(Exception ex) {

                throw new RuntimeException("Problem loading logging settings file for application: " + e + "\n" + ex);
            }

        }

    }

}

