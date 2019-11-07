package org.framework42.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestUtils {

    public static void getOutput(OutputStream outputStream, String url, Map<String, String> headers) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }

        connection.setDoInput(true);
        connection.setDoOutput(true);
        InputStream in = connection.getInputStream();
        inputToOutput(in, outputStream);
    }


    public static void postOutput(OutputStream outputStream, String url, String postData, Map<String, String> headers) throws IOException {
        HttpClient httpclient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost(url);
        HttpEntity entity = new ByteArrayEntity(postData.getBytes("UTF-8"));
        post.setEntity(entity);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                post.addHeader(entry.getKey(), entry.getValue());
            }
        }

        HttpResponse response = httpclient.execute(post);

        if (response.getStatusLine().getStatusCode() == 200) {
            inputToOutput(response.getEntity().getContent(), outputStream);
        } else {
            throw new RuntimeException("HttpRequestUtils - couldn't not send request status code " + response.getStatusLine().getStatusCode());
        }
    }


    private static void inputToOutput(InputStream in, OutputStream out) throws IOException {

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        out.close();
        in.close();
    }




}
