package org.framework42.http.server;

import org.framework42.http.RequestMethod;
import org.framework42.http.StatusCode;
import org.framework42.http.server.exceptions.HttpException;
import org.framework42.http.server.exceptions.HttpRequestLineException;
import org.framework42.http.server.exceptions.URLNotFoundException;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientSocketThread implements Runnable {

    private final Logger logger = Logger.getLogger("org.nummer42.http");

    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMMM YYYY HH:mm:ss");

    private final Socket socket;

    private final HttpServerEnvironment serverEnv;

    private final int bufferSize = 8192;

    public ClientSocketThread(Socket socket, HttpServerEnvironment environment) {

        this.socket = socket;
        this.serverEnv = environment;
    }

    @Override
    public void run() {

        try {

            OutputStream out = socket.getOutputStream();

            try {

                InputStream inputStream = socket.getInputStream();

                byte[] buffer = new byte[bufferSize];

                int bufferLength = readData(inputStream, buffer);

                String headerString = new String(Arrays.copyOf(buffer, bufferLength));

                verifyHeader(headerString);

                List<String> requestLines = new ArrayList<>();
                for(String s: headerString.split("\r\n")) {
                    requestLines.add(s);
                }

                System.out.println("Headerstring:"+headerString);

                RequestMethod requestMethod = RequestMethod.findByName(headerString.split("\r\n")[0].split(" ")[0].toUpperCase());
                System.out.println("Request method: "+requestMethod);
                String[] sa = headerString.split("\r\n")[0].split(" ")[1].split("\\?");

                String calUrl = sa[0];
                String queryParameters = "";
                if(sa.length>1) { queryParameters = sa[1];}

                System.out.println("call url: "+calUrl);

                RequestData requestData = new RequestData(parseHeaderMap(requestLines), parseRequestMap(queryParameters, requestLines));

                if(requestData.getHeaderMap().get("accept").startsWith("image") || "/favicon.ico".equalsIgnoreCase(calUrl)) {

                    out = socket.getOutputStream();

                    byte[] imgbuf = new byte[10240];
                    int len;
                    InputStream imageStream = new FileInputStream("/home/fredrik/Downloads"+calUrl);
                    while ((len = imageStream.read(imgbuf)) != -1) {
                        out.write(imgbuf, 0, len);
                    }

                    out.flush();
                    out.close();

                } else {

                    ServerEndPoint endPoint = serverEnv.findByUrl(calUrl);

                    out.write(endPoint.renderEndPointResponse(requestData, requestData).getBytes("UTF-8"));
                }

                System.out.println("Read completed\n-----------------------------------------------------------\n\n");

                /*out.print("HTTP/1.1 200 OK\r\n");
                out.print("Connection: close\r\n");
                out.print("\r\n");
                out.print("<html><head></head><body><h1>Test</h1>" + System.currentTimeMillis() + "<br><img src=\"img.png\"></body></html>");
*/
                out.flush();
                out.close();

                inputStream.close();

                socket.close();

            } catch (HttpRequestLineException e) {

                StringBuilder sb = new StringBuilder();

                sb.append("HTTP/1.1 " + StatusCode.BAD_REQUEST_400.getId() + " " + StatusCode.BAD_REQUEST_400.getName());
                sb.append("Connection: close\r\n");
                sb.append("\r\n");

                out.write(sb.toString().getBytes("UTF-8"));

                out.flush();
                out.close();

                socket.close();

                logger.log(Level.INFO, e.getMessage());

            }  catch (URLNotFoundException e) {

                StringBuilder sb = new StringBuilder();

                sb.append("HTTP/1.1 " + StatusCode.NOT_FOUND_404.getId() + " " + StatusCode.NOT_FOUND_404.getName());
                sb.append("Connection: close\r\n");
                sb.append("\r\n");

                out.flush();
                out.close();

                socket.close();

                logger.log(Level.INFO, e.getMessage());
            }

        } catch (IOException e) {

            logger.log(Level.SEVERE, e.getMessage());

        } catch (Exception e) {

            logger.log(Level.SEVERE, e.getMessage());
        }

    }

    private int readData(InputStream inputStream, byte[] buffer) throws HttpRequestLineException {

        int bufferLength = 0;

        int read = 0;
        int splitByte = 0;

        while (read > -1 && splitByte <= 0) {

            try {
                read = inputStream.read(buffer, bufferLength, bufferSize - bufferLength);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            bufferLength += read;

            splitByte = findHeaderEnd(buffer, bufferLength);

            System.out.println(new String(buffer));
        }

        if(bufferLength==0) {

            String errorMess = "Empty request payload from "+socket.getRemoteSocketAddress().toString()+", can't complete request!";

            logger.log(Level.FINE, errorMess);

            throw new HttpRequestLineException(errorMess);
        }

        return bufferLength;
    }

    private void verifyHeader(String headerString) throws HttpRequestLineException {

        String requestLine = headerString.split("\r\n")[0];

        String[] requestLineArray = requestLine.split(" ");

        if(requestLineArray.length!=3) {

            throw new HttpRequestLineException("Request line wrongly formatted");
        }
    }

    private int findHeaderEnd(final byte[] buf, int bufferLength) {

        int splitbyte = 0;
        while (splitbyte + 3 < bufferLength) {
            if (buf[splitbyte] == '\r' && buf[splitbyte + 1] == '\n' && buf[splitbyte + 2] == '\r' && buf[splitbyte + 3] == '\n') {
                return splitbyte + 4;
            }
            splitbyte++;
        }
        return 0;
    }

    private Map<String,String> parseHeaderMap(List<String> dataList) {

        Map<String,String> headerMap = new HashMap<>();

        for(int i=1;i<dataList.size();i++) {

            String[] split = dataList.get(i).split(":");

            if(split.length>1) {

                String data = "";

                for(int si=1;si<split.length;si++) {

                    if(si>1) {

                        data += ":";
                    }

                    data += split[si];
                }

                headerMap.put(split[0].toLowerCase(), data.trim());
            }
        }

        return headerMap;
    }

    private Map<String,String> parseRequestMap(String callURL, List<String> dataList) {

        Map<String,String> requestMap = new HashMap<>();

        if(callURL!=null && callURL.length()>0) {

            String[] sa = callURL.split("&");

            for(String s: sa) {

                String[] sp = s.split("=");

                requestMap.put(sp[0], sp[1]);
            }
        }

        return requestMap;
    }

    //@Override
    /*public void run2() {

        try {

            try {

                out = new PrintWriter(socket.getOutputStream(), true);
                //in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                byte[] byteArray = new byte[new BufferedInputStream(socket.getInputStream()).available()];

                int bytesRead = new BufferedInputStream(socket.getInputStream()).read(byteArray);

                System.out.println(byteArray.length + ":" + bytesRead);

                out.flush();

                List<String> dataList = new ArrayList<>();

                String inputData = new String(byteArray);

                System.out.println(inputData);

                validateHttpData(inputData);

                boolean readPayload = false;

                out.print("HTTP/1.1 200 OK\r\n");
                out.print("\r\n");
                out.print("<html><head></head><body><h1>Test</h1>" + System.currentTimeMillis() + "</body></html>");
                out.print("Connection: close\r\n");

                while (!run) {

                /*String line = in.readLine();

                logger.log(Level.INFO, line);

                if(line==null) {

                    processCall(dataList, payload, out);

                    dataList = new ArrayList<>();

                    run = false;

                } else if(line.length()==0 && readPayload == false) {

                    readPayload = true;

                } else {

                    if(readPayload) {

                        payload += line+"\n";

                    } else {

                        dataList.add(line);
                    }
                }
                */
            /*    }

            } catch (HttpException e) {

                logger.log(Level.SEVERE, e.getMessage());

                out.print("HTTP/1.1 " + BAD_REQUEST_400.getId() + " " + BAD_REQUEST_400.getName() + "\r\n");
                out.print("Connection: close\r\n");
            }

            out.print("\r\n");
            out.flush();

            socket.close();

        } catch(IOException e) {

            logger.log(Level.SEVERE, e.getMessage());

        }
    }*/

    private void validateHttpData(String inputData) throws HttpException {

        String requestLine = inputData.split("\n")[0];

        if (requestLine.length() != 3) {

            throw new HttpException("No request line");
        }

    }

    /*private void processCall(List<String> dataList, String payload, PrintWriter out) {

        try {

            String[] requestLine = dataList.get(0).split("\\ ");

            if (requestLine.length == 3) {

                RequestMethod requestMethod = RequestMethod.findByName(requestLine[0]);

                String uri = requestLine[1];

                ServerEndPoint endPoint = serverEnv.findByUrl(uri);

                Map<String,String> requestMap = parseHeaderMap(dataList);

                switch (requestMethod) {

                    case GET:
                        processGet();
                    break;
                    case HEAD:
                        processHead();
                    break;
                    case POST:
                        processPost();
                    break;
                    case PUT:
                        processPut();
                    break;
                    case DELETE:
                        processDelete();
                    break;
                    case CONNECT:
                        processConnect();
                    break;
                    case OPTIONS:
                        processOptions();
                    break;
                    case TRACE:
                        processTrace();
                    break;
                    default:
                        out.print("HTTP/1.1 "+BAD_REQUEST_400.getId()+" "+BAD_REQUEST_400.getName()+"\r\n");
                        out.print("Connection: close\r\n");
                    break;
                }

            } else {

                logger.log(Level.WARNING, "Client not containing correct Request Line, data: "+dataList.get(0));

                out.print("HTTP/1.1 "+BAD_REQUEST_400.getId()+" "+BAD_REQUEST_400.getName()+"\r\n");
                out.print("Connection: close\r\n");
            }

        } catch (IndexOutOfBoundsException e) {

            logger.log(Level.WARNING, "Bad Client Request, data list length: "+dataList.size());

            out.print("HTTP/1.1 "+BAD_REQUEST_400.getId()+" "+BAD_REQUEST_400.getName()+"\r\n");
            out.print("Connection: close\r\n");

        } catch (Exception e) {

            logger.log(Level.SEVERE, "Internal server error: "+e.getMessage());

            out.print("HTTP/1.1 "+INTERNAL_SERVER_ERROR_500.getId()+" "+INTERNAL_SERVER_ERROR_500.getName()+"\r\n");
            out.print("Connection: close\r\n");
        }
    }*/

    /*private void processGet() {

        out.print("HTTP/1.1 "+ NOT_IMPLEMENTED_501.getId()+" "+NOT_IMPLEMENTED_501.getName()+"\r\n");
        out.print("Date: "+dateFormat.format(DateUtil.stepBack(new Date(), 3600000))+" GMT\r\n");
        out.print("Server: "+serverEnv.getServerName()+"\r\n");
        out.print("Connection: close\r\n");
    }

    private void processHead() {

        out.print("HTTP/1.1 "+ NOT_IMPLEMENTED_501.getId()+" "+NOT_IMPLEMENTED_501.getName()+"\r\n");
        out.print("Date: "+dateFormat.format(DateUtil.stepBack(new Date(), 3600000))+" GMT\r\n");
        out.print("Server: "+serverEnv.getServerName()+"\r\n");
        out.print("Connection: close\r\n");
    }

    private void processPost() {

        out.print("HTTP/1.1 "+ NOT_IMPLEMENTED_501.getId()+" "+NOT_IMPLEMENTED_501.getName()+"\r\n");
        out.print("Date: "+dateFormat.format(DateUtil.stepBack(new Date(), 3600000))+" GMT\r\n");
        out.print("Server: "+serverEnv.getServerName()+"\r\n");
        out.print("Connection: close\r\n");
    }

    private void processPut() {

        out.print("HTTP/1.1 "+ NOT_IMPLEMENTED_501.getId()+" "+NOT_IMPLEMENTED_501.getName()+"\r\n");
        out.print("Date: "+dateFormat.format(DateUtil.stepBack(new Date(), 3600000))+" GMT\r\n");
        out.print("Server: "+serverEnv.getServerName()+"\r\n");
        out.print("Connection: close\r\n");
    }

    private void processDelete() {

        out.print("HTTP/1.1 "+ NOT_IMPLEMENTED_501.getId()+" "+NOT_IMPLEMENTED_501.getName()+"\r\n");
        out.print("Date: "+dateFormat.format(DateUtil.stepBack(new Date(), 3600000))+" GMT\r\n");
        out.print("Server: "+serverEnv.getServerName()+"\r\n");
        out.print("Connection: close\r\n");
    }

    private void processConnect() {

        out.print("HTTP/1.1 "+ NOT_IMPLEMENTED_501.getId()+" "+NOT_IMPLEMENTED_501.getName()+"\r\n");
        out.print("Date: "+dateFormat.format(DateUtil.stepBack(new Date(), 3600000))+" GMT\r\n");
        out.print("Server: "+serverEnv.getServerName()+"\r\n");
        out.print("Connection: close\r\n");
    }

    private void processOptions() {

        out.print("HTTP/1.1 "+ NOT_IMPLEMENTED_501.getId()+" "+NOT_IMPLEMENTED_501.getName()+"\r\n");
        out.print("Date: "+dateFormat.format(DateUtil.stepBack(new Date(), 3600000))+" GMT\r\n");
        out.print("Server: "+serverEnv.getServerName()+"\r\n");
        out.print("Connection: close\r\n");
    }

    private void processTrace() {

        out.print("HTTP/1.1 "+ NOT_IMPLEMENTED_501.getId()+" "+NOT_IMPLEMENTED_501.getName()+"\r\n");
        out.print("Date: "+dateFormat.format(DateUtil.stepBack(new Date(), 3600000))+" GMT\r\n");
        out.print("Server: "+serverEnv.getServerName()+"\r\n");
        out.print("Connection: close\r\n");
    }*/

}
