package org.json;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class RESTHttpURLConnection extends HttpURLConnection {

    public RESTHttpURLConnection(URL u) {
        super(u);

    }

    @Override
    public void disconnect() {

    }

    @Override
    public boolean usingProxy() {
        return false;
    }

    @Override
    public void connect() throws IOException {

    }
}
