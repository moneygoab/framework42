package org.json;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public enum RESTJSONCaller {

    INSTANCE;

    private RESTJSONCaller() {

    }

    public RESTJSONResponse makeGetCall(String consumerKey, String targetURL) throws IOException {

        return makeGetCall("X-Consumer-Key", consumerKey, targetURL, "");
    }

    public RESTJSONResponse makeGetCall(String consumerKeyParameterName, String consumerKey, String targetURL, String urlParameters) throws IOException {

        URL url;
        HttpURLConnection connection = null;
        try {
            //Create connection
            url = new URL(targetURL);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty(consumerKeyParameterName, consumerKey);

            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Send request
            /*DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream ());
            wr.writeBytes (urlParameters);
            wr.flush ();
            wr.close ();
*/
            //Get Response
            if(connection.getResponseCode()==HttpURLConnection.HTTP_OK || connection.getResponseCode()==HttpURLConnection.HTTP_CREATED) {

                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                rd.close();
                System.out.println(connection.getResponseCode());
                System.out.println(response.toString());

                if(response.length()>0) {

                    return new RESTJSONResponse(connection.getResponseCode(), new JSONObject(response.toString()));

                } else {

                    return new RESTJSONResponse(connection.getResponseCode(), new JSONObject());
                }

            } else {

                InputStream is = connection.getErrorStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                rd.close();
                System.out.println(connection.getResponseCode());
                System.out.println(response.toString());

                try {
                    return new RESTJSONResponse(connection.getResponseCode(), new JSONObject(response.toString()));
                } catch(JSONException e) {
                    JSONObject obj = new JSONObject();
                    obj.put("status_code", connection.getResponseCode());
                    obj.put("error_message", response.toString());

                    return new RESTJSONResponse(connection.getResponseCode(), obj);
                }
            }

        } catch (MalformedURLException e) {

            e.printStackTrace();
            return null;

        } finally {

            if(connection != null) {
                connection.disconnect();
            }
        }
    }

    public RESTJSONResponse makePostCall(String consumerKey, String targetURL) throws IOException {

        return makePostCall("X-Consumer-Key", consumerKey, targetURL, "", "application/json");
    }

    public RESTJSONResponse makePostCall(String consumerKeyParameterName, String consumerKey, String targetURL, String urlParameters, String contentType) throws IOException {

        URL url;
        HttpURLConnection connection = null;
        try {
            //Create connection
            url = new URL(targetURL);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty(consumerKeyParameterName, consumerKey);
            connection.setRequestProperty("Content-Type", contentType);
            connection.setRequestProperty("Content-Length", ""+Integer.toString(urlParameters.getBytes().length));

            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream ());
            wr.writeBytes (urlParameters);
            wr.flush ();
            wr.close ();

            //Get Response
            if(connection.getResponseCode()==HttpURLConnection.HTTP_OK || connection.getResponseCode()==HttpURLConnection.HTTP_CREATED) {

                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                rd.close();
                System.out.println(connection.getResponseCode());
                System.out.println(response.toString());

                if(response.length()>0) {

                    return new RESTJSONResponse(connection.getResponseCode(), new JSONObject(response.toString()));

                } else {

                    return new RESTJSONResponse(connection.getResponseCode(), new JSONObject());
                }

            } else {

                InputStream is = connection.getErrorStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                rd.close();
                System.out.println(connection.getResponseCode());
                System.out.println(response.toString());

                try {
                    return new RESTJSONResponse(connection.getResponseCode(), new JSONObject(response.toString()));
                } catch(JSONException e) {
                    JSONObject obj = new JSONObject();
                    obj.put("status_code", connection.getResponseCode());
                    obj.put("error_message", response.toString());

                    return new RESTJSONResponse(connection.getResponseCode(), obj);
                }
            }

        } catch (MalformedURLException e) {

            e.printStackTrace();
            return null;

        } finally {

            if(connection != null) {
                connection.disconnect();
            }
        }
    }
}
