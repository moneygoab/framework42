package org.framework42.http.server;

import org.framework42.http.StatusCode;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpServer {

    protected final Logger logger = Logger.getLogger("org.nummer42.http");

    protected final HttpServerEnvironment environment;

    public static boolean SERVER_RUNNING = true;

    public static void main(String[] args) {

        List<ServerEndPoint> endPointList = new ArrayList<>();

        endPointList.add(new TestEndPoint(Arrays.asList("/"), new ArrayList<LogicWorker>()));
        endPointList.add(new TestEndPoint(Arrays.asList("/test"), new ArrayList<LogicWorker>()));
        endPointList.add(new TestEndPoint(Arrays.asList("/all/*"), new ArrayList<LogicWorker>()));

        List<ServerEndPoint> errorEndPointList = new ArrayList<>();

        errorEndPointList.add(new DefaultWWWErrorEndPoint(Arrays.asList(StatusCode.NOT_FOUND_404.getName()), "Page not found!", "Could not find a page with the provided url!"));

        new HttpServer(
                new HttpServerEnvironment(
                        "#42",
                        8000,
                        8192,
                        endPointList,
                        errorEndPointList,
                        new DefaultWWWErrorEndPoint(Arrays.asList("/error"), "An error occurred!", "Unfortunately an error has occurred that we couldn't handle.")
                )
        );
    }

    protected HttpServer(HttpServerEnvironment environment) {

        this.environment = environment;

        logger.setLevel(Level.ALL);

        try {

            ServerSocket serverSocket = new ServerSocket(environment.getPort());

            logger.info("Http server "+environment.getServerName()+" running on port "+environment.getPort()+".");

            while(SERVER_RUNNING) {

                try {

                    new ClientSocketThread(serverSocket.accept(), environment).run();

                } catch(IOException e) {

                    logger.log(Level.SEVERE, e.getMessage());
                }
            }

        } catch(IOException e) {

            logger.log(Level.SEVERE, e.getMessage());
        }

        System.exit(0);
    }
}
