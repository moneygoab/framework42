package org.framework42.http.server;

import org.framework42.http.server.exceptions.URLNotFoundException;

import java.util.List;

public class HttpServerEnvironment {

    private final String serverName;

    private final int port;

    private final List<ServerEndPoint> endPointList;

    public HttpServerEnvironment(String serverName, int port, List<ServerEndPoint> endPointList) {

        this.serverName = serverName;
        this.port = port;
        this.endPointList = endPointList;
    }

    public String getServerName() {
        return serverName;
    }

    public int getPort() {
        return port;
    }

    public ServerEndPoint findByUrl(String uri) throws URLNotFoundException {

        for(ServerEndPoint endPoint: endPointList) {

            for(int i=0;i<endPoint.getPath().size();i++) {

                if (endPoint.isCatchAllBelow() && uri.startsWith(endPoint.getPath().get(i).toString())) {

                    return endPoint;

                } else if (endPoint.getPath().get(i).toString().equalsIgnoreCase(uri)) {

                    return endPoint;
                }
            }
        }

        throw new URLNotFoundException("URL "+uri+" not found on server!");
    }

    public List<ServerEndPoint> getEndPointList() {
        return endPointList;
    }
}
