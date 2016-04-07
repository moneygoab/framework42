package org.json;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public enum RESTJSONCaller {

    INSTANCE;

    private final Logger logger = Logger.getLogger("org.json");

    private RESTJSONCaller() {

    }

    public RESTJSONResponse makeGetCall(String consumerKey, String targetURL) throws IOException {

        return makeGetCall("X-Consumer-Key", consumerKey, targetURL, "");
    }

    public RESTJSONResponse makePATCHCall(String headerName,String headerValue,String url,String jsonData,String contentType)  throws IOException{

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPatch httpPatch = new HttpPatch(url);
        httpPatch.addHeader(headerName,headerValue);
        httpPatch.addHeader("Content-Type",contentType);
        StringEntity se =new StringEntity(jsonData);
        httpPatch.setEntity(se);
        CloseableHttpResponse response = httpClient.execute(httpPatch);
        return new RESTJSONResponse(response.getStatusLine().getStatusCode(),new JSONObject(EntityUtils.toString(response.getEntity())));
    }


    public RESTJSONResponse makeGetCall(String consumerKeyParameterName, String consumerKey, String targetURL, String urlParameters) throws IOException {

        URL url;
        HttpURLConnection connection = null;
        try {
            //Create connection
            if(urlParameters!=null && urlParameters.length()>0) {
                url = new URL(targetURL+"?"+ urlParameters);
            } else {
                url = new URL(targetURL);
            }
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty(consumerKeyParameterName, consumerKey);

            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Get Response
            if(connection.getResponseCode()==HttpURLConnection.HTTP_OK || connection.getResponseCode()==HttpURLConnection.HTTP_CREATED) {

                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\n');
                }
                rd.close();
                logger.debug(connection.getResponseCode());
                logger.debug(response.toString());

                if(response.length()>0) {

                    if(response.toString().startsWith("[")) {

                        JSONArray arr = new JSONArray(response.toString());

                        JSONObject obj = arr.getJSONObject(0);

                        return new RESTJSONResponse(connection.getResponseCode(), obj);

                    } else {
                        return new RESTJSONResponse(connection.getResponseCode(), new JSONObject(response.toString()));
                    }

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
                    response.append('\n');
                }
                rd.close();
                logger.debug(connection.getResponseCode());
                logger.debug(response.toString());

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

    public RESTJSONArrayResponse makeGetArrayCall(String consumerKey, String targetURL) throws IOException {

        return makeGetArrayCall("X-Consumer-Key", consumerKey, targetURL, "");
    }

    public RESTJSONArrayResponse makeGetArrayCall(String consumerKeyParameterName, String consumerKey, String targetURL, String urlParameters) throws IOException {

        URL url;
        HttpURLConnection connection = null;
        try {
            //Create connection
            if(urlParameters!=null && urlParameters.length()>0) {
                url = new URL(targetURL+"?"+ urlParameters);
            } else {
                url = new URL(targetURL);
            }
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty(consumerKeyParameterName, consumerKey);

            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Get Response
            if(connection.getResponseCode()==HttpURLConnection.HTTP_OK || connection.getResponseCode()==HttpURLConnection.HTTP_CREATED) {

                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\n');
                }
                rd.close();
                logger.debug(connection.getResponseCode());
                logger.debug(response.toString());

                if(response.length()>0) {

                    if(response.toString().startsWith("[")) {

                        JSONArray arr = new JSONArray(response.toString());

                        return new RESTJSONArrayResponse(connection.getResponseCode(), arr);

                    } else {

                        JSONArray arr = new JSONArray();

                        arr.put(new JSONObject(response.toString()));

                        return new RESTJSONArrayResponse(connection.getResponseCode(), arr);
                    }

                } else {

                    return new RESTJSONArrayResponse(connection.getResponseCode(), new JSONArray());
                }

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
                logger.debug(response.toString());

                try {

                    JSONArray arr = new JSONArray();
                    arr.put(new JSONObject(response.toString()));

                    return new RESTJSONArrayResponse(connection.getResponseCode(), arr);
                } catch(JSONException e) {
                    JSONObject obj = new JSONObject();
                    obj.put("status_code", connection.getResponseCode());
                    obj.put("error_message", response.toString());

                    JSONArray arr = new JSONArray();
                    arr.put(obj);

                    return new RESTJSONArrayResponse(connection.getResponseCode(), arr);
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

    public RESTJSONResponse makePostCallWithBasicAuth(String targetURL, String postData, String contentType,String username,String password, boolean trustedSSL) throws IOException,NoSuchAlgorithmException,KeyManagementException{

        SSLSocketFactory defaultSSLSocketFactory =  null;
        HostnameVerifier defaultHostnameVerifier = null;
        if(!trustedSSL){

            defaultSSLSocketFactory =  HttpsURLConnection.getDefaultSSLSocketFactory();
            defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();

            TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

        }

        String auth = username + ":" +password;

        String base64Encode = Base64.encode(auth.getBytes());

        RESTJSONResponse response = makePostCall("Authorization","Basic " + base64Encode,targetURL,postData, contentType);

        if(!trustedSSL){
            HttpsURLConnection.setDefaultSSLSocketFactory(defaultSSLSocketFactory);
            HttpsURLConnection.setDefaultHostnameVerifier(defaultHostnameVerifier);
        }

        return response;
    }

    public RESTJSONResponse makePATCHCallWithBasicAuth(String targetURL, String postData, String contentType,String username,String password, boolean trustedSSL) throws IOException,NoSuchAlgorithmException,KeyManagementException{

        SSLSocketFactory defaultSSLSocketFactory =  null;
        HostnameVerifier defaultHostnameVerifier = null;
        if(!trustedSSL){

            defaultSSLSocketFactory =  HttpsURLConnection.getDefaultSSLSocketFactory();
            defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();

            TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

        }

        String auth = username + ":" +password;

        String base64Encode = Base64.encode(auth.getBytes());

        RESTJSONResponse response = makePATCHCall("Authorization","Basic " + base64Encode,targetURL,postData,contentType);

        if(!trustedSSL){
            HttpsURLConnection.setDefaultSSLSocketFactory(defaultSSLSocketFactory);
            HttpsURLConnection.setDefaultHostnameVerifier(defaultHostnameVerifier);
        }

        return response;
    }


    public RESTJSONResponse makePostCall(String consumerKeyParameterName, String consumerKey, String targetURL, String postData, String contentType) throws IOException {

        URL url;
        HttpURLConnection connection = null;
        try {


            //Create connection
            url = new URL(targetURL);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty(consumerKeyParameterName, consumerKey);
            connection.setRequestProperty("Content-Type", contentType);
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Length", ""+Integer.toString(postData.getBytes().length));

            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            byte[] postBytes = postData.getBytes("UTF-8");
            wr.write(postBytes, 0, postBytes.length);
            //wr.writeBytes(postData);
            //wr.writeUTF(postData);
            wr.flush();
            wr.close();

            //Get Response
            if(connection.getResponseCode()==HttpURLConnection.HTTP_OK || connection.getResponseCode()==HttpURLConnection.HTTP_CREATED) {

                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\n');
                }
                rd.close();
                logger.debug(connection.getResponseCode());
                logger.debug(response.toString());

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
                    response.append('\n');
                }
                rd.close();
                logger.debug(connection.getResponseCode());
                logger.debug(response.toString());

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
            JSONObject obj = new JSONObject();
            obj.put("status_code", connection.getResponseCode());
            obj.put("error_message", e.getMessage());
            return new RESTJSONResponse(connection.getResponseCode(), obj);

        } finally {

            if(connection != null) {
                connection.disconnect();
            }
        }
    }

    public RESTJSONResponse makePutCall(String consumerKey, String targetURL) throws IOException {

        return makePutCall("X-Consumer-Key", consumerKey, targetURL, "");
    }

    public RESTJSONResponse makePutCall(String consumerKeyParameterName, String consumerKey, String targetURL, String urlParameters) throws IOException {

        URL url;
        HttpURLConnection connection = null;
        try {
            if(urlParameters.length()>0) {
                urlParameters = "?"+urlParameters;
            }

            //Create connection
            url = new URL(targetURL+urlParameters);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty(consumerKeyParameterName, consumerKey);

            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Put Response
            if(connection.getResponseCode()==HttpURLConnection.HTTP_OK || connection.getResponseCode()==HttpURLConnection.HTTP_NO_CONTENT) {

                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\n');
                }
                rd.close();
                logger.debug(connection.getResponseCode());
                logger.debug(response.toString());

                if(response.length()>0) {

                    if(response.toString().startsWith("[")) {

                        JSONArray arr = new JSONArray(response.toString());

                        JSONObject obj = arr.getJSONObject(0);

                        return new RESTJSONResponse(connection.getResponseCode(), obj);

                    } else {
                        return new RESTJSONResponse(connection.getResponseCode(), new JSONObject(response.toString()));
                    }

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
                    response.append('\n');
                }
                rd.close();
                logger.debug(connection.getResponseCode());
                logger.debug(response.toString());

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
            JSONObject errorObj = new JSONObject();
            errorObj.put("error_message", e.toString());
            return new RESTJSONResponse(connection.getResponseCode(), errorObj);

        } finally {

            if(connection != null) {
                connection.disconnect();
            }
        }
    }

    public RESTJSONResponse makePutCall(String consumerKeyParameterName, String consumerKey, String targetURL, String postData, String contentType) throws IOException {

        URL url;
        HttpURLConnection connection = null;
        try {
            //Create connection
            url = new URL(targetURL);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty(consumerKeyParameterName, consumerKey);
            connection.setRequestProperty("Content-Type", contentType);
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Length", ""+Integer.toString(postData.getBytes().length));

            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            byte[] postBytes = postData.getBytes("UTF-8");
            wr.write(postBytes, 0, postBytes.length);
            //wr.writeBytes(postData);
            //wr.writeUTF(postData);
            wr.flush();
            wr.close();

            //Get Response
            if(connection.getResponseCode()==HttpURLConnection.HTTP_OK || connection.getResponseCode()==HttpURLConnection.HTTP_CREATED) {

                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\n');
                }
                rd.close();
                logger.debug(connection.getResponseCode());
                logger.debug(response.toString());

                if(response.length()>0) {

                    return new RESTJSONResponse(connection.getResponseCode(), new JSONObject(response.toString()));

                } else {

                    return new RESTJSONResponse(connection.getResponseCode(), new JSONObject());
                }

            } else if(connection.getResponseCode()==HttpURLConnection.HTTP_NO_CONTENT) {

                return new RESTJSONResponse(connection.getResponseCode(), new JSONObject());

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
                logger.debug(response.toString());

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
            JSONObject obj = new JSONObject();
            obj.put("status_code", connection.getResponseCode());
            obj.put("error_message", e.getMessage());
            return new RESTJSONResponse(connection.getResponseCode(), obj);

        } finally {

            if(connection != null) {
                connection.disconnect();
            }
        }
    }

    public RESTJSONResponse makeDeleteCall(String consumerKey, String targetURL) throws IOException {

        return makeDeleteCall("X-Consumer-Key", consumerKey, targetURL, "");
    }

    public RESTJSONResponse makeDeleteCall(String consumerKeyParameterName, String consumerKey, String targetURL, String urlParameters) throws IOException {

        URL url;
        HttpURLConnection connection = null;
        try {
            if(urlParameters.length()>0) {
                urlParameters = "?"+urlParameters;
            }

            //Create connection
            url = new URL(targetURL+urlParameters);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty(consumerKeyParameterName, consumerKey);

            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Put Response
            if(connection.getResponseCode()==HttpURLConnection.HTTP_OK || connection.getResponseCode()==HttpURLConnection.HTTP_NO_CONTENT) {

                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\n');
                }
                rd.close();
                logger.debug(connection.getResponseCode());
                logger.debug(response.toString());

                if(response.length()>0) {

                    if(response.toString().startsWith("[")) {

                        JSONArray arr = new JSONArray(response.toString());

                        JSONObject obj = arr.getJSONObject(0);

                        return new RESTJSONResponse(connection.getResponseCode(), obj);

                    } else {
                        return new RESTJSONResponse(connection.getResponseCode(), new JSONObject(response.toString()));
                    }

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
                    response.append('\n');
                }
                rd.close();
                logger.debug(connection.getResponseCode());
                logger.debug(response.toString());

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
            JSONObject errorObj = new JSONObject();
            errorObj.put("error_message", e.toString());
            return new RESTJSONResponse(connection.getResponseCode(), errorObj);

        } finally {

            if(connection != null) {
                connection.disconnect();
            }
        }
    }

}
