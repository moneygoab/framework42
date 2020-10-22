package org.framework42.configuration;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.RESTJSONArrayResponse;
import org.json.RESTJSONCaller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Class that handles loading the application configurations from disc.
 */
public enum ApplicationConfigHandler {

    INSTANCE;

    private final Logger logger = Logger.getLogger("org.framework42");

    /**
     * Loads the config file for the application with the sent in filename
     *
     * @param settingsFileName The filename of the configuration
     * @return The properties as a java Properties object.
     */
    public Properties load(String settingsFileName) {

        InputStream fileStream = getClass().getClassLoader().getResourceAsStream(settingsFileName);

        Properties properties = new Properties();

        try {

            properties.loadFromXML(fileStream);

        } catch (Exception ex) {

            try {

                properties.loadFromXML(new FileInputStream(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + settingsFileName));

            } catch (Exception e) {

                String errorMess = "Problem loading settings file " + settingsFileName + ": ";

                logger.fatal(errorMess + "\n" + e + "\n" + ex);
                throw new RuntimeException(errorMess + "\n" + e + "\n" + ex);

            }
        }

        if ("true".equalsIgnoreCase(properties.getProperty("load_config_from_server"))) {

            try {

                properties = load(properties.getProperty("settings_server_url"), properties.getProperty("setting_consumer_key_parameter_name"), properties.getProperty("setting_consumer_key"));


            } catch (Exception e) {
            }

        } else if (properties.containsKey("settings_server_url") && properties.containsKey("setting_control")) {

            Enumeration props = properties.propertyNames();

            JSONArray settings = new JSONArray();

            while (props.hasMoreElements()) {
                settings.put((String) props.nextElement());
            }

            try {
                RESTJSONArrayResponse response = RESTJSONCaller.INSTANCE.makePostArrayCall("", "", properties.getProperty("settings_server_url") + "/" + properties.getProperty("setting_control") + "/control", settings.toString(), "application/json");

                if (response.getArray().length() > 0) {
                    throw new RuntimeException("---!!!Properties are missing parameters!!!--- " + response.getArray().toString(2));
                }

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
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
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty(consumerKeyParameterName, consumerKey);
                connection.setRequestProperty("Content-Type", "application/xml");

                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);

                //Get Response
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK || connection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {

                    properties.loadFromXML(connection.getInputStream());

                } else {
                    InputStream is = connection.getErrorStream();
                    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                    String line;
                    StringBuffer response = new StringBuffer();
                    while ((line = rd.readLine()) != null) {
                        response.append(line);
                        response.append('\n');
                    }
                    rd.close();

                    String errorMess = connection.getResponseCode() + " - " + response.toString();

                    logger.fatal(errorMess);
                    throw new RuntimeException(errorMess);
                }

            } finally {

                if (connection != null) {
                    connection.disconnect();
                }
            }

        } catch (Exception ex) {

            String errorMess = "Problem loading settings file from server " + settingsServerUrl;

            logger.fatal(errorMess + "\n" + ex + "\n" + ex);
            throw new RuntimeException(errorMess + "\n" + ex + "\n" + ex);
        }

        return properties;

    }

}
