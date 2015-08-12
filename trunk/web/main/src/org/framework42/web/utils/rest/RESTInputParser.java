package org.framework42.web.utils.rest;

import org.apache.log4j.Logger;
import org.framework42.model.APIResponseType;
import org.framework42.web.exceptions.StopServletExecutionException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public enum RESTInputParser {

    INSTANCE;

    private final static Logger logger = Logger.getLogger("org.framework42.web");

    public JSONObject parseInData(HttpServletRequest req, HttpServletResponse resp, APIResponseType responseType) throws IOException, StopServletExecutionException {

        try {

            StringBuilder buffer = new StringBuilder();
            req.setCharacterEncoding("UTF-8");
            BufferedReader reader = req.getReader();

            String inLine;
            while ((inLine = reader.readLine()) != null) {
                buffer.append(inLine);
            }
            String data = buffer.toString();

            logger.debug("Input data: "+data);

            if(data.length()==0 || !data.startsWith("{")) {

                logger.error("A JSONObject text must begin with '{' at 1 [character 2 line 1]");
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, RESTErrorMaker.INSTANCE.addError("40027", "Badly formatted input data. Not valid of chosen type "+responseType.getMimeType(), "General technical problem.", responseType));
                throw new StopServletExecutionException();
            }

            return new JSONObject(data);

        } catch(UnsupportedEncodingException e) {

            logger.error(e.getClass().toString() + ": " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, RESTErrorMaker.INSTANCE.addError("40003", "Bad encoding of input data it should be in UTF-8!", "General technical problem.", responseType));
            throw new StopServletExecutionException();

        } catch(IOException e) {

            logger.error(e.getClass().toString() + ": " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, RESTErrorMaker.INSTANCE.addError("50009", "Internal server error, couldn't complete call.", "General technical problem.", responseType));
            throw new StopServletExecutionException();

        } catch(JSONException e) {

            logger.error(e.getClass().toString() + ": " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, RESTErrorMaker.INSTANCE.addError("40027", "Badly formatted input data. Not valid of chosen type " + responseType.getMimeType(), "General technical problem.", responseType));
            throw new StopServletExecutionException();
        }
    }

    public JSONArray parseInDataArray(HttpServletRequest req, HttpServletResponse resp, APIResponseType responseType) throws StopServletExecutionException, IOException {

        try {

            StringBuilder buffer = new StringBuilder();
            req.setCharacterEncoding("UTF-8");
            BufferedReader reader = req.getReader();

            String inLine;
            while ((inLine = reader.readLine()) != null) {
                buffer.append(inLine);
            }
            String data = buffer.toString();

            logger.debug(data);

            if(data.length()==0 || !data.startsWith("[")) {

                logger.error("A JSONArray data object must begin with '[' at 1 [character 2 line 1]");
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, RESTErrorMaker.INSTANCE.addError("40027", "Badly formatted input data. Not valid of chosen type "+responseType.getMimeType(), "General technical problem.", responseType));
                throw new StopServletExecutionException();
            }

            return new JSONArray(data);

        } catch(UnsupportedEncodingException e) {

            logger.error(e.getClass().toString() + ": " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, RESTErrorMaker.INSTANCE.addError("40003", "Bad encoding of input data it should be in UTF-8!", "General technical problem.", responseType));
            throw new StopServletExecutionException();

        } catch(IOException e) {

            logger.error(e.getClass().toString() + ": " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, RESTErrorMaker.INSTANCE.addError("50001", "Internal communication error, application can't be completed!", "General technical problem.", responseType));
            throw new StopServletExecutionException();

        } catch(JSONException e) {

            logger.error(e.getClass().toString() + ": " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, RESTErrorMaker.INSTANCE.addError("40027", "Badly formatted input data. Not valid of chosen type "+responseType.getMimeType(), "General technical problem.", responseType));
            throw new StopServletExecutionException();
        }
    }

}
