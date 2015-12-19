package org.framework42.http.server;

import org.framework42.http.StatusCode;
import org.framework42.http.server.exceptions.URLNotFoundException;

import java.util.List;

public class HttpServerEnvironment {

    private final String serverName;

    private final int port;

    private final int bufferSize;

    private final List<ServerEndPoint> endPointList;

    private final List<ServerEndPoint> errorEndPointList;

    private final ServerEndPoint defaultErrorEndPoint;

    public HttpServerEnvironment(String serverName, int port, int bufferSize, List<ServerEndPoint> endPointList, List<ServerEndPoint> errorEndPointList, ServerEndPoint defaultErrorEndPoint) {

        this.serverName = serverName;
        this.port = port;
        this.bufferSize = bufferSize;
        this.endPointList = endPointList;
        this.errorEndPointList = errorEndPointList;
        this.defaultErrorEndPoint = defaultErrorEndPoint;
    }

    public String getServerName() {
        return serverName;
    }

    public int getPort() {
        return port;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public ServerEndPoint findEndPointByUrl(String url) throws URLNotFoundException {

        for(ServerEndPoint endPoint: endPointList) {

            for(int i=0;i<endPoint.getPath().size();i++) {

                if (endPoint.isCatchAllBelow() && url.startsWith(endPoint.getPath().get(i).toString())) {

                    return endPoint;

                } else if (endPoint.getPath().get(i).toString().equalsIgnoreCase(url)) {

                    return endPoint;
                }
            }
        }

        throw new URLNotFoundException("URL "+url+" not found on server!");
    }

    public ServerEndPoint findErrorEndPointByStatusCode(StatusCode statusCode) {

        for(ServerEndPoint endPoint: errorEndPointList) {

            List<String> pathList = endPoint.getPath();

            for(String path: pathList) {

                if(path.equalsIgnoreCase(statusCode.getName())) {

                    return endPoint;
                }
            }
        }

        return defaultErrorEndPoint;
    }

}
