package org.framework42.configuration;

import org.apache.log4j.Logger;
import org.json.RESTJSONCaller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

        Properties properties = new Properties();

        try {

            properties.loadFromXML(new FileInputStream(url + settingsFileName));

        } catch(Exception ex) {
            try{

                properties.loadFromXML(new FileInputStream(System.getProperty("java.io.tmpdir")+System.getProperty("file.separator")+settingsFileName));

            } catch (Exception e) {

                String errorMess = "Problem loading settings file "+settingsFileName+": ";

                logger.fatal(errorMess+"\n"+e+"\n"+ex);
                throw new RuntimeException(errorMess+"\n"+e+"\n"+ex);

            }
        }

        if("true".equalsIgnoreCase(properties.getProperty("load_config_from_server"))) {

            try {

                properties = load(properties.getProperty("settings_server_url"), properties.getProperty("setting_consumer_key_parameter_name"), properties.getProperty("setting_consumer_key"));

            } catch (Exception e) {}
        }

        return properties;

    }

    public Properties load(String settingsServerUrl, String consumerKeyParameterName, String consumerKey) {

        Properties properties = new Properties();

        try {

            URL url;
            HttpURLConnection connection = null;
            try {
                url = new URL(settingsServerUrl);

                connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty(consumerKeyParameterName, consumerKey);
                connection.setRequestProperty("Content-Type", "application/xml");

                connection.setUseCaches (false);
                connection.setDoInput(true);
                connection.setDoOutput(true);

                //Get Response
                if(connection.getResponseCode()==HttpURLConnection.HTTP_OK || connection.getResponseCode()==HttpURLConnection.HTTP_CREATED) {

                    properties.loadFromXML(connection.getInputStream());

                } else {

                    String errorMess = "Problem loading settings file from server "+settingsServerUrl+"Got HTTP response "+connection.getResponseCode();

                    logger.fatal(errorMess);
                    throw new RuntimeException(errorMess);
                }

            } finally {

                if(connection != null) {
                    connection.disconnect();
                }
            }

        } catch(Exception ex) {

                String errorMess = "Problem loading settings file from server "+settingsServerUrl;

                logger.fatal(errorMess+"\n"+ex+"\n"+ex);
                throw new RuntimeException(errorMess+"\n"+ex+"\n"+ex);
        }

        return properties;

    }

}
