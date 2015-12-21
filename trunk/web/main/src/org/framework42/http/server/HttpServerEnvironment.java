package org.framework42.http.server;

import org.framework42.http.StatusCode;
import org.framework42.http.server.exceptions.URLNotFoundException;

import java.io.File;
import java.util.List;
import java.util.Locale;

public class HttpServerEnvironment {

    private final String serverName;

    private final int port;

    private final int bufferSize;

    private final File dataFilesRoot;

    private final List<ServerEndPoint> endPointList;

    private final List<ServerEndPoint> errorEndPointList;

    private final ServerEndPoint defaultErrorEndPoint;

    private final List<Locale> availableLanguages;

    public HttpServerEnvironment(String serverName, int port, int bufferSize, File dataFilesRoot, List<ServerEndPoint> endPointList, List<ServerEndPoint> errorEndPointList, ServerEndPoint defaultErrorEndPoint, List<Locale> availableLanguages) {

        this.serverName = serverName;
        this.port = port;
        this.bufferSize = bufferSize;
        this.dataFilesRoot = dataFilesRoot;
        this.endPointList = endPointList;
        this.errorEndPointList = errorEndPointList;
        this.defaultErrorEndPoint = defaultErrorEndPoint;
        this.availableLanguages = availableLanguages;
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

    public File getDataFilesRoot() {
        return dataFilesRoot;
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

    public List<Locale> getAvailableLanguages() {
        return availableLanguages;
    }
}
