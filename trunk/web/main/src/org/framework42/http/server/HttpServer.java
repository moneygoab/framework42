package org.framework42.http.server;

import org.framework42.http.StatusCode;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpServer {

    protected final Logger logger = Logger.getLogger("org.nummer42.http");

    protected final HttpServerEnvironment environment;

    public static boolean SERVER_RUNNING = true;

    public static void main(String[] args) {

        List<ServerEndPoint> endPointList = new ArrayList<>();

        List<LogicWorker> logicList = new ArrayList<>();
        logicList.add(new TestLogicWorker());

        endPointList.add(new TestEndPoint(Arrays.asList("/"), logicList));
        endPointList.add(new TestEndPoint(Arrays.asList("/test"), new ArrayList<LogicWorker>()));
        endPointList.add(new TestEndPoint(Arrays.asList("/all/*"), new ArrayList<LogicWorker>()));

        List<ServerEndPoint> errorEndPointList = new ArrayList<>();

        errorEndPointList.add(new DefaultWWWErrorEndPoint(Arrays.asList(StatusCode.NOT_FOUND_404.getName()), "Page not found!", "Could not find a page with the provided url!"));

        List<Locale> localesList = new ArrayList<>();

        localesList.add(new Locale("sv", "SE"));
        localesList.add(Locale.ENGLISH);

        new HttpServer(
                new HttpServerEnvironment(
                        "#42",
                        8000,
                        8192,
                        endPointList,
                        errorEndPointList,
                        new DefaultWWWErrorEndPoint(Arrays.asList("/error"), "An error occurred!", "Unfortunately an error has occurred that we couldn't handle."),
                        localesList
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
