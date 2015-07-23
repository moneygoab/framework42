package org.framework42.http.server;

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

        new HttpServer(
                new HttpServerEnvironment(
                        "#42",
                        8000,
                        endPointList
                )
        );
    }

    protected HttpServer(HttpServerEnvironment environment) {

        this.environment = environment;

        logger.setLevel(Level.ALL);

        try {

            ServerSocket serverSocket = new ServerSocket(environment.getPort());

            while(SERVER_RUNNING) {

                new ClientSocketThread(serverSocket.accept(), environment).run();
            }

        } catch(IOException e) {

            logger.log(Level.SEVERE, e.getMessage());
        }

        System.exit(0);
    }
}
