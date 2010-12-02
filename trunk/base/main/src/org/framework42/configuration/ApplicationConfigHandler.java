package org.framework42.configuration;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

/**
 * Class that handles loading the application configurations from disc.
 * */
public enum ApplicationConfigHandler {

    INSTANCE;

    private final Logger logger = Logger.getLogger("org.framework42");

    /**
     * Loads the config file for the application with the sent in filename
     * @param settingsFileName      The filename of the configuration
     * @return The properties as a java Properties object.
     * */
    public Properties load(String settingsFileName) {

        String url = getClass().getClassLoader().getResource("").getPath();
        url = url.replaceAll("%20", " ");
        url += "resources" + System.getProperty("file.separator");

        Properties properties = new Properties();

        try {

            properties.loadFromXML(new FileInputStream(url + settingsFileName));

        } catch(Exception ex) {
            try{

                properties.loadFromXML(new FileInputStream("/tmp/"+settingsFileName));

            } catch (Exception e) {

                String errorMess = "Problem loading settings file "+settingsFileName+": ";

                logger.error(errorMess+e);
                throw new RuntimeException(errorMess+e);

            }
        }

        return properties;

    }

}
