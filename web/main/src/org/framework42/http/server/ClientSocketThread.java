package org.framework42.http.server;

import org.framework42.http.StatusCode;
import org.framework42.http.server.exceptions.HttpRequestLineException;
import org.framework42.http.server.exceptions.URLNotFoundException;
import org.framework42.http.RequestMethod;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientSocketThread implements Runnable {

    private final Logger logger = Logger.getLogger("org.nummer42.http");

    private final Socket socket;

    private final HttpServerEnvironment serverEnv;

    private final static RequestData errorRequest = new RequestData(RequestMethod.GET, "", new HashMap<String,String>(), new HashMap<String,String>());

    public ClientSocketThread(Socket socket, HttpServerEnvironment serverEnv) {

        this.socket = socket;
        this.serverEnv = serverEnv;
    }

    @Override
    public void run() {

        try {

            OutputStream out = socket.getOutputStream();

            try {

                RequestData req = RequestReader.INSTANCE.readInData(socket.getInputStream(), serverEnv.getBufferSize());

                if(req.getHeaderMap().containsKey("accept") && req.getHeaderMap().get("accept").startsWith("image")) {

                    FileRenderer.INSTANCE.renderFile(socket.getOutputStream(), req.getUrl());

                } else if(req.getHeaderMap().containsKey("accept") && req.getHeaderMap().get("accept").startsWith("text/css")) {

                    FileRenderer.INSTANCE.renderFile(socket.getOutputStream(), req.getUrl());

                } else {

                    ServerEndPoint endPoint = serverEnv.findEndPointByUrl(req.getUrl());

                    ResponseData resp = endPoint.createResponseData();

                    List<LogicWorker> logicWorkerList = endPoint.getPreRenderLogicList();
                    for(LogicWorker logicWorker: logicWorkerList) {

                        logicWorker.performLogic(serverEnv, req, resp);
                    }

                    out.write(endPoint.renderEndPointResponse(serverEnv, req, resp));
                }

            } catch (HttpRequestLineException e) {

                out.write(serverEnv.findErrorEndPointByStatusCode(StatusCode.BAD_REQUEST_400).renderEndPointResponse(serverEnv, errorRequest, new ResponseDataImpl(StatusCode.BAD_REQUEST_400)));

                logger.log(Level.INFO, e.getMessage());

            }  catch (URLNotFoundException e) {

                out.write(serverEnv.findErrorEndPointByStatusCode(StatusCode.NOT_FOUND_404).renderEndPointResponse(serverEnv, errorRequest, new ResponseDataImpl(StatusCode.NOT_FOUND_404)));

                logger.log(Level.INFO, e.getMessage());
            }

            out.flush();
            out.close();

        } catch (IOException e) {

            logger.log(Level.SEVERE, e.getMessage());
        }
    }

}
