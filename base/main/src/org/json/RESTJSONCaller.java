package org.json;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum RESTJSONCaller {

    INSTANCE;

    private final Logger logger = Logger.getLogger("org.json");

    RESTJSONCaller() {

    }

    public RESTJSONResponse makeGetCall(String consumerKeyParameterName, String consumerKey, String targetURL, String urlParameters, HashMap<String, String> headers) throws IOException {

        URL url;
        HttpURLConnection connection = null;
        try {
            //Create connection
            if (urlParameters != null && urlParameters.length() > 0) {
                url = new URL(targetURL + "?" + urlParameters);
            } else {
                url = new URL(targetURL);
            }
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            if(consumerKeyParameterName!=null&& consumerKeyParameterName.length()>0) {
                connection.setRequestProperty(consumerKeyParameterName, consumerKey);
            }
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Get Response
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK || connection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {

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
                if (response.length() < 1024) {
                    logger.debug(response.toString());
                } else {
                    logger.debug("Response larger then 1024 bytes, wont print");
                }

                if (response.length() > 0) {

                    if (response.toString().startsWith("[")) {

                        JSONArray arr = new JSONArray(response.toString());

                        JSONObject obj = arr.getJSONObject(0);

                        return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), obj);

                    } else {
                        return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), new JSONObject(response.toString()));
                    }

                } else {

                    return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), new JSONObject());
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
                if (response.length() < 1024) {
                    logger.debug(response.toString());
                } else {
                    logger.debug("Response larger then 1024 bytes, wont print");
                }

                try {
                    return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), new JSONObject(response.toString()));
                } catch (JSONException e) {
                    JSONObject obj = new JSONObject();
                    obj.put("status_code", connection.getResponseCode());
                    obj.put("error_message", response.toString());

                    return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), obj);
                }
            }

        } catch (MalformedURLException e) {

            e.printStackTrace();
            return null;

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }


    public RESTJSONResponse makeSecureGetCall(String consumerKeyParameterName, String consumerKey, String targetURL, String urlParameters,FileInputStream keyStoreFile, String keystorePassword, HashMap<String, String> headers) throws IOException {

        URL url;
        HttpsURLConnection connection = null;
        try {
            //Create connection
            if (urlParameters != null && urlParameters.length() > 0) {
                url = new URL(targetURL + "?" + urlParameters);
            } else {
                url = new URL(targetURL);
            }
            KeyStore keyStore = KeyStore.getInstance("pkcs12");
            keyStore.load(keyStoreFile, keystorePassword.toCharArray());

            final SSLContext sslContext = SSLContexts.custom()
                    .loadTrustMaterial(keyStore, null)
                    .loadKeyMaterial(keyStore, keystorePassword.toCharArray())
                    .build();


            //Create connection
            url = new URL(targetURL);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setSSLSocketFactory(sslContext.getSocketFactory());
            connection.setRequestMethod("GET");
            connection.setRequestProperty(consumerKeyParameterName, consumerKey);
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Get Response
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK || connection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {

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
                if (response.length() < 1024) {
                    logger.debug(response.toString());
                } else {
                    logger.debug("Response larger then 1024 bytes, wont print");
                }

                if (response.length() > 0) {

                    if (response.toString().startsWith("[")) {

                        JSONArray arr = new JSONArray(response.toString());

                        JSONObject obj = arr.getJSONObject(0);

                        return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), obj);

                    } else {
                        return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), new JSONObject(response.toString()));
                    }

                } else {

                    return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), new JSONObject());
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
                if (response.length() < 1024) {
                    logger.debug(response.toString());
                } else {
                    logger.debug("Response larger then 1024 bytes, wont print");
                }

                try {
                    return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), new JSONObject(response.toString()));
                } catch (JSONException e) {
                    JSONObject obj = new JSONObject();
                    obj.put("status_code", connection.getResponseCode());
                    obj.put("error_message", response.toString());

                    return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), obj);
                }
            }

        } catch (MalformedURLException | NoSuchAlgorithmException | KeyStoreException | UnrecoverableKeyException | KeyManagementException | CertificateException e) {

            e.printStackTrace();
            return null;

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }


    public RESTJSONArrayResponse makeGetArrayCall(String consumerKey, String targetURL) throws IOException {

        return makeGetArrayCall("X-Consumer-Key", consumerKey, targetURL, "",null);
    }

    public RESTJSONArrayResponse makeGetArrayCall(String consumerKeyParameterName, String consumerKey, String targetURL, String urlParameters, HashMap<String, String> headers) throws IOException {

        URL url;
        HttpURLConnection connection = null;
        try {
            //Create connection
            if (urlParameters != null && urlParameters.length() > 0) {
                url = new URL(targetURL + "?" + urlParameters);
            } else {
                url = new URL(targetURL);
            }
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            if(consumerKeyParameterName!=null&& consumerKeyParameterName.length()>0) {
                connection.setRequestProperty(consumerKeyParameterName, consumerKey);
            }
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Get Response
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK || connection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {

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
                if (response.length() < 1024) {
                    logger.debug(response.toString());
                } else {
                    logger.debug("Response larger then 1024 bytes, wont print");
                }

                if (response.length() > 0) {

                    if (response.toString().startsWith("[")) {

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
                if (response.length() < 1024) {
                    logger.debug(response.toString());
                } else {
                    logger.debug("Response larger then 1024 bytes, wont print");
                }

                try {

                    JSONArray arr = new JSONArray();
                    arr.put(new JSONObject(response.toString()));

                    return new RESTJSONArrayResponse(connection.getResponseCode(), arr);
                } catch (JSONException e) {
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

            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public RESTJSONArrayResponse makePostArrayCall(String consumerKeyParameterName, String consumerKey, String targetURL, String postData, String contentType) throws IOException {

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


            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(postData.getBytes().length));


            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            wr = new DataOutputStream(connection.getOutputStream());
            //Send request
            byte[] postBytes = postData.getBytes("UTF-8");
            wr.write(postBytes, 0, postBytes.length);


            wr.flush();
            wr.close();

            //Get Response
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK || connection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {

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
                if (response.length() < 1024) {
                    logger.debug(response.toString());
                } else {
                    logger.debug("Response larger then 1024 bytes, wont print");
                }

                if (response.length() > 0) {

                    if (response.toString().startsWith("[")) {

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
                if (response.length() < 1024) {
                    logger.debug(response.toString());
                } else {
                    logger.debug("Response larger then 1024 bytes, wont print");
                }

                try {

                    JSONArray arr = new JSONArray();
                    arr.put(new JSONObject(response.toString()));

                    return new RESTJSONArrayResponse(connection.getResponseCode(), arr);
                } catch (JSONException e) {
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

            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public RESTJSONResponse makePostCall(String consumerKey, String targetURL) throws IOException {

        return makePostCall("X-Consumer-Key", consumerKey, targetURL, "", "application/json");
    }

    public RESTJSONResponse makePostCallWithBasicAuth(String targetURL, ByteArrayOutputStream stream, String contentType, String username, String password, boolean trustedSSL) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        return makePostCallWithBasicAuth(targetURL, "", stream, contentType, username, password, trustedSSL);
    }

    public RESTJSONResponse makePostCallWithBasicAuth(String targetURL, String postData, String contentType, String username, String password, boolean trustedSSL) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        return makePostCallWithBasicAuth(targetURL, postData, null, contentType, username, password, trustedSSL);
    }

    public RESTJSONResponse makePostCallWithBasicAuth(String targetURL, String postData, ByteArrayOutputStream stream, String contentType, String username, String password, boolean trustedSSL) throws IOException, NoSuchAlgorithmException, KeyManagementException {

        SSLSocketFactory defaultSSLSocketFactory = null;
        HostnameVerifier defaultHostnameVerifier = null;
        if (!trustedSSL) {

            defaultSSLSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
            defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();

            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
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

        String auth = username + ":" + password;

        String base64Encode = Base64.encode(auth.getBytes());
        RESTJSONResponse response;
        if (stream == null) {
            response = makePostCall("Authorization", "Basic " + base64Encode, targetURL, postData, contentType);
        } else {
            response = postCall("Authorization", "Basic " + base64Encode, targetURL, "", stream, contentType, null);
        }


        if (!trustedSSL) {
            HttpsURLConnection.setDefaultSSLSocketFactory(defaultSSLSocketFactory);
            HttpsURLConnection.setDefaultHostnameVerifier(defaultHostnameVerifier);
        }

        return response;
    }

    public RESTJSONResponse makePostCall(String consumerKeyParameterName, String consumerKey, String targetURL, ByteArrayOutputStream stream) throws IOException {

        return postCall(consumerKeyParameterName, consumerKey, targetURL, null, stream, null, null);
    }

    public RESTJSONResponse makePostCall(String consumerKeyParameterName, String consumerKey, String targetURL, String postData, String contentType) throws IOException {
        return makePostCall(consumerKeyParameterName, consumerKey, targetURL, postData, contentType, null);
    }

    public RESTJSONResponse makePostCall(String consumerKeyParameterName, String consumerKey, String targetURL, String postData, String contentType, HashMap<String, String> headers) throws IOException {
        return postCall(consumerKeyParameterName, consumerKey, targetURL, postData, null, contentType, headers);

    }

    private RESTJSONResponse postCall(String consumerKeyParameterName, String consumerKey, String targetURL, String postData, ByteArrayOutputStream stream, String contentType, HashMap<String, String> headers) throws IOException {

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
                if (response.length() < 1024) {
                    logger.debug(response.toString());
                } else {
                    logger.debug("Response larger then 1024 bytes, wont print");
                }

                if (response.length() > 0) {

                    return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), new JSONObject(response.toString()));

                } else {

                    return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), new JSONObject());
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
                if (response.length() < 1024) {
                    logger.debug(response.toString());
                } else {
                    logger.debug("Response larger then 1024 bytes, wont print");
                }

                try {
                    return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), new JSONObject(response.toString()));
                } catch (JSONException e) {
                    JSONObject obj = new JSONObject();
                    obj.put("status_code", connection.getResponseCode());
                    obj.put("error_message", response.toString());

                    return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), obj);
                }
            }

        } catch (MalformedURLException e) {

            e.printStackTrace();
            JSONObject obj = new JSONObject();
            obj.put("status_code", connection.getResponseCode());
            obj.put("error_message", e.getMessage());
            return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), obj);

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public RESTJSONResponse makeSecurePostCall(String targetURL, String postData, String contentType, FileInputStream keyStoreFile, String keystorePassword, HashMap<String, String> headers) throws IOException {
        URL url;
        HttpsURLConnection connection = null;
        try {
            KeyStore keyStore = KeyStore.getInstance("pkcs12");
            keyStore.load(keyStoreFile, keystorePassword.toCharArray());

            final SSLContext sslContext = SSLContexts.custom()
                    .loadTrustMaterial(keyStore, null)
                    .loadKeyMaterial(keyStore, keystorePassword.toCharArray())
                    .build();


            //Create connection
            url = new URL(targetURL);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setSSLSocketFactory(sslContext.getSocketFactory());
            connection.setRequestMethod("POST");
            DataOutputStream wr;
            connection.setRequestProperty("Content-Type", contentType);
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


            wr.flush();
            wr.close();


            //Get Response
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK || connection.getResponseCode() == HttpURLConnection.HTTP_CREATED || connection.getResponseCode() == HttpURLConnection.HTTP_ACCEPTED) {

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
                if (response.length() < 1024) {
                    logger.debug(response.toString());
                } else {
                    logger.debug("Response larger then 1024 bytes, wont print");
                }

                if (response.length() > 0) {

                    return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), new JSONObject(response.toString()));

                } else {

                    return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), new JSONObject());
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
                if (response.length() < 1024) {
                    logger.debug(response.toString());
                } else {
                    logger.debug("Response larger then 1024 bytes, wont print");
                }

                try {
                    return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), new JSONObject(response.toString()));
                } catch (JSONException e) {
                    JSONObject obj = new JSONObject();
                    obj.put("status_code", connection.getResponseCode());
                    obj.put("error_message", response.toString());

                    return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), obj);
                }
            }

        } catch (MalformedURLException | NoSuchAlgorithmException | KeyStoreException | UnrecoverableKeyException | KeyManagementException | CertificateException e) {

            e.printStackTrace();
            JSONObject obj = new JSONObject();
            obj.put("status_code", connection.getResponseCode());
            obj.put("error_message", e.getMessage());
            return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), obj);

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }



    public RESTJSONResponse makePATCHCallWithBasicAuth(String targetURL, String postData, String contentType, String username, String password, boolean trustedSSL) throws IOException, NoSuchAlgorithmException, KeyManagementException {

        SSLSocketFactory defaultSSLSocketFactory = null;
        HostnameVerifier defaultHostnameVerifier = null;
        if (!trustedSSL) {

            defaultSSLSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
            defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();

            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
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

        String auth = username + ":" + password;

        String base64Encode = Base64.encode(auth.getBytes());

        RESTJSONResponse response = makePATCHCall("Authorization", "Basic " + base64Encode, targetURL, postData, contentType);

        if (!trustedSSL) {
            HttpsURLConnection.setDefaultSSLSocketFactory(defaultSSLSocketFactory);
            HttpsURLConnection.setDefaultHostnameVerifier(defaultHostnameVerifier);
        }

        return response;
    }

    public RESTJSONResponse makePATCHCall(String headerName, String headerValue, String url, String jsonData, String contentType) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPatch httpPatch = new HttpPatch(url);
        if(headerName!=null&&headerName.length()>0) {
            httpPatch.addHeader(headerName, headerValue);
        }
        httpPatch.addHeader("Content-Type", contentType);
        StringEntity se = new StringEntity(jsonData);
        httpPatch.setEntity(se);
        CloseableHttpResponse response = httpClient.execute(httpPatch);
        return new RESTJSONResponse(response.getStatusLine().getStatusCode(), new HashMap<>(), new JSONObject(EntityUtils.toString(response.getEntity())));
    }

    public RESTJSONResponse makePutCall(String consumerKey, String targetURL) throws IOException {

        return makePutCall("X-Consumer-Key", consumerKey, targetURL, "");
    }

    public RESTJSONResponse makePutCall(String consumerKeyParameterName, String consumerKey, String targetURL, String urlParameters) throws IOException {
        return makePutCall(consumerKeyParameterName, consumerKey, targetURL, "", urlParameters, "", new HashMap<String, String>());

    }

    public RESTJSONResponse makePutCall(String consumerKeyParameterName, String consumerKey, String targetURL, String postData, String urlParameters, String contentType, HashMap<String, String> headers) throws IOException {

        URL url;
        HttpURLConnection connection = null;
        try {
            if (urlParameters != null && urlParameters.length() > 0) {
                url = new URL(targetURL + "?" + urlParameters);
            } else {
                url = new URL(targetURL);
            }
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            if(consumerKeyParameterName!=null&& consumerKeyParameterName.length()>0) {
                connection.setRequestProperty(consumerKeyParameterName, consumerKey);
            }
            connection.setRequestProperty("Content-Type", contentType);
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

            //Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            byte[] postBytes = postData.getBytes("UTF-8");
            wr.write(postBytes, 0, postBytes.length);
            //wr.writeBytes(postData);
            //wr.writeUTF(postData);
            wr.flush();
            wr.close();

            //Get Response
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK || connection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {

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
                if (response.length() < 1024) {
                    logger.debug(response.toString());
                } else {
                    logger.debug("Response larger then 1024 bytes, wont print");
                }

                if (response.length() > 0) {

                    return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), new JSONObject(response.toString()));

                } else {

                    return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), new JSONObject());
                }

            } else if (connection.getResponseCode() == HttpURLConnection.HTTP_NO_CONTENT) {

                return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), new JSONObject());

            } else {

                InputStream is = connection.getErrorStream();

                if (is == null) {

                    return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), new JSONObject());

                } else {

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

                    try {
                        return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), new JSONObject(response.toString()));
                    } catch (JSONException e) {
                        JSONObject obj = new JSONObject();
                        obj.put("status_code", connection.getResponseCode());
                        obj.put("error_message", response.toString());

                        return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), obj);
                    }
                }
            }

        } catch (MalformedURLException e) {

            e.printStackTrace();
            JSONObject obj = new JSONObject();
            obj.put("status_code", connection.getResponseCode());
            obj.put("error_message", e.getMessage());
            return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), obj);

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public RESTJSONResponse makeDeleteCall(String consumerKey, String targetURL) throws IOException {

        return makeDeleteCall("X-Consumer-Key", consumerKey, targetURL, "");
    }

    public RESTJSONResponse makeDeleteCall(String consumerKeyParameterName, String consumerKey, String targetURL, String urlParameters) throws IOException {
        return makeDeleteCall(consumerKeyParameterName, consumerKey, targetURL, urlParameters, null);
    }

    public RESTJSONResponse makeDeleteCall(String consumerKeyParameterName, String consumerKey, String targetURL, String urlParameters, HashMap<String, String> headers) throws IOException {

        URL url;
        HttpURLConnection connection = null;
        try {
            if (urlParameters.length() > 0) {
                urlParameters = "?" + urlParameters;
            }

            //Create connection
            url = new URL(targetURL + urlParameters);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            if(consumerKeyParameterName!=null&& consumerKeyParameterName.length()>0) {
                connection.setRequestProperty(consumerKeyParameterName, consumerKey);
            }
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Put Response
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK || connection.getResponseCode() == HttpURLConnection.HTTP_NO_CONTENT) {

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
                if (response.length() < 1024) {
                    logger.debug(response.toString());
                } else {
                    logger.debug("Response larger then 1024 bytes, wont print");
                }

                if (response.length() > 0) {

                    if (response.toString().startsWith("[")) {

                        JSONArray arr = new JSONArray(response.toString());

                        JSONObject obj = arr.getJSONObject(0);

                        return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), obj);

                    } else {
                        return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), new JSONObject(response.toString()));
                    }

                } else {

                    return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), new JSONObject());
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
                if (response.length() < 1024) {
                    logger.debug(response.toString());
                } else {
                    logger.debug("Response larger then 1024 bytes, wont print");
                }

                try {
                    return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), new JSONObject(response.toString()));
                } catch (JSONException e) {
                    JSONObject obj = new JSONObject();
                    obj.put("status_code", connection.getResponseCode());
                    obj.put("error_message", response.toString());

                    return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), obj);
                }
            }

        } catch (MalformedURLException e) {

            e.printStackTrace();
            JSONObject errorObj = new JSONObject();
            errorObj.put("error_message", e.toString());
            return new RESTJSONResponse(connection.getResponseCode(), connection.getHeaderFields(), errorObj);

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }


}
