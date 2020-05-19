package org.json;

import org.apache.log4j.Logger;
import org.framework42.dao.JSONRetryQueueDataDAO;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.framework42.utils.NullChecker.notNull;

public class PostCallRunnable implements Runnable {

    private final Logger logger = Logger.getLogger("org.json");

    private final JSONRetryQueueDataDAO dao;
    private final String consumerKeyParameterName;
    private final String consumerKey;
    private final String targetURL;
    private final String postData;
    private final ByteArrayOutputStream stream;
    private final String contentType;
    private final HashMap<String, String> headers;

    public PostCallRunnable(JSONRetryQueueDataDAO dao, String consumerKeyParameterName, String consumerKey, String targetURL, String postData, ByteArrayOutputStream stream, String contentType, HashMap<String, String> headers) {
        this.dao = notNull(dao);
        this.consumerKeyParameterName = consumerKeyParameterName;
        this.consumerKey = consumerKey;
        this.targetURL = targetURL;
        this.postData = postData;
        this.stream = stream;
        this.contentType = contentType;
        this.headers = headers;
    }

    @Override
    public void run() {

        URL url;
        HttpURLConnection connection = null;
        try {


            //Create connection
            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            if(consumerKeyParameterName!=null&& consumerKeyParameterName.length()>0) {
                connection.setRequestProperty(consumerKeyParameterName, consumerKey);
            }
            connection.setRequestProperty("Content-Type", contentType);
            DataOutputStream wr;

            if (stream == null) {
                connection.setRequestProperty("Accept-Charset", "UTF-8");
                connection.setRequestProperty("Content-Length", "" + Integer.toString(postData.getBytes().length));

                if (headers != null) {
                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                        connection.setRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                wr = new DataOutputStream(connection.getOutputStream());
                //Send request
                byte[] postBytes = postData.getBytes("UTF-8");
                wr.write(postBytes, 0, postBytes.length);

            } else {

                connection.setRequestProperty("Content-Length", "" + Integer.toString(stream.toByteArray().length));
                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                //Send request

                wr = new DataOutputStream(connection.getOutputStream());
                wr.write(stream.toByteArray(), 0, stream.toByteArray().length);

            }

            wr.flush();
            wr.close();

            //Get Response
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK || connection.getResponseCode() == HttpURLConnection.HTTP_CREATED || connection.getResponseCode() == HttpURLConnection.HTTP_ACCEPTED) {


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
                logger.debug(connection.getResponseCode());
                if (response.length() < 1024) {
                    logger.debug(response.toString());
                } else {
                    logger.debug("Response larger then 1024 bytes, wont print");
                }

                dao.add(new JSONRetryQueueDataImpl(targetURL, contentType, headers, postData));
            }

        } catch (MalformedURLException e) {

            try {

                e.printStackTrace();
                JSONObject obj = new JSONObject();
                obj.put("status_code", connection.getResponseCode());
                obj.put("error_message", e.getMessage());

                dao.add(new JSONRetryQueueDataImpl(targetURL, contentType, headers, postData));

            } catch (IOException ex){


            }

        } catch (IOException e) {

            dao.add(new JSONRetryQueueDataImpl(targetURL, contentType, headers, postData));

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }

}
