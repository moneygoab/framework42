package org.framework42.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by marcus on 2016-05-20.
 */
public class HttpRequestUtils {

    public static void GetOutput(OutputStream outputStream, String url, Map<String,String> headers) throws IOException{
        HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();

        for(Map.Entry<String,String> entry: headers.entrySet()){
            connection.setRequestProperty(entry.getKey(),entry.getValue());
        }

        connection.setDoInput(true);
        connection.setDoOutput(true);
        InputStream in = connection.getInputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1)
        {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.close();
        in.close();
    }


}
