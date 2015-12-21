package org.framework42.http.server;

import org.framework42.exceptions.NotAuthorizedException;

import java.io.*;

public enum FileRenderer {

    INSTANCE;

    public void renderFile(OutputStream outImg, String url, File dataFilesRoot) throws IOException, NotAuthorizedException {

        if(verifyFile(url, dataFilesRoot)) {

            byte[] imgbuf = new byte[10240];
            int len;
            InputStream imageStream = new FileInputStream(dataFilesRoot.toString() + url);
            while ((len = imageStream.read(imgbuf)) != -1) {
                outImg.write(imgbuf, 0, len);
            }

            outImg.flush();
            outImg.close();

        } else {

            throw new NotAuthorizedException("Client trying to retrieve information outside data files root. Tried path: "+url);
        }

    }

    private boolean verifyFile(String url, File dataFilesRoot) {

        try {

            File callFile = new File(dataFilesRoot.toString() + url).getCanonicalFile();

            File parent = callFile.getParentFile();

            while (parent != null) {

                if (dataFilesRoot.equals(parent)) {

                    return true;
                }
                parent = parent.getParentFile();
            }

            return false;

        } catch (IOException e) {

            return false;
        }
    }

}


