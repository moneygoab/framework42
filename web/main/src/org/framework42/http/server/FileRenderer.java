package org.framework42.http.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public enum FileRenderer {

    INSTANCE;

    public void renderFile(OutputStream outImg, String url) throws IOException {

        byte[] imgbuf = new byte[10240];
        int len;
        InputStream imageStream = new FileInputStream("/home/fredrik/Downloads"+url);
        while ((len = imageStream.read(imgbuf)) != -1) {
            outImg.write(imgbuf, 0, len);
        }

        outImg.flush();
        outImg.close();
    }

}
