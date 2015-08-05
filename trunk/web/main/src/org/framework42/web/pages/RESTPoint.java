package org.framework42.web.pages;

import org.apache.log4j.Logger;
import org.framework42.model.APIRequestType;
import org.framework42.model.APIResponseType;
import org.framework42.web.authorization.RESTConsumer;
import org.framework42.web.exceptions.StopServletExecutionException;
import org.framework42.web.pagemodel.RESTErrorCode;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.framework42.web.pagemodel.RESTErrorCode.INVALID_CONSUMER_KEY;
import static org.framework42.web.pagemodel.RESTErrorCode.INVALID_CONSUMER_KEY_PARAMETER_TYPE;
import static org.framework42.web.pagemodel.RESTErrorCode.MISSING_CONSUMER_KEY;

public abstract class RESTPoint extends HttpServlet {

    protected final static Logger logger = Logger.getLogger("org.framework42.web");

    protected String consumerKeyParameterName = "X-Consumer-Key";

    protected APIResponseType defaultResponseType = APIResponseType.JSON;

    protected boolean forceDataType = false;

    protected boolean forceContentTypeInput = false;

    protected boolean test = true;

    protected List<APIResponseType> validResponseTypesList = Arrays.asList(APIResponseType.JSON, APIResponseType.XML);

    protected List<String> validPostContentTypes = Arrays.asList("application/x-www-form-urlencoded");

    protected List<String> validPutContentTypes = Arrays.asList("application/x-www-form-urlencoded");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logInData(req);

        try {

            APIResponseType responseType = getResponseType(req.getHeader("Content-Type"), resp);
            resp.setHeader("Access-Control-Allow-Origin", "*");

            RESTConsumer consumer = processCall(req, resp, responseType);

            if (consumer!=null && consumer.isAuthenticated()) {

                doGetSpecific(req, resp, responseType, consumer);
            }

        } catch (StopServletExecutionException e) {

            logger.info(e.getMessage());

        } catch(Exception e) {

            //resp.getOutputStream().println("Unhandled internal error, can't give proper error feedback.");
            logger.fatal(e.getMessage());
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unhandled internal error, can't give proper error feedback.");
        }
    }

    protected abstract void doGetSpecific(HttpServletRequest req, HttpServletResponse resp, APIResponseType responseType, RESTConsumer consumer) throws IOException, StopServletExecutionException;

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logInData(req);

        try {

            APIResponseType responseType = getResponseType(req.getHeader("Content-Type"), resp);
            resp.setHeader("Access-Control-Allow-Origin", "*");
            resp.setHeader("Access-Control-Max-Age", "86400");
            resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");

            //consumerId = processCall(req, resp, responseType);

            //if (consumerId > 0) {

            doOptionsSpecific(req, resp, responseType);
            //}

        } catch(Exception e) {

            resp.getOutputStream().println("Unhandled internal error, can't give proper error feedback.");
            logger.fatal(e.getMessage());
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unhandled internal error, can't give proper error feedback.");
        }
    }

    protected abstract void doOptionsSpecific(HttpServletRequest req, HttpServletResponse resp, APIResponseType responseType) throws IOException, StopServletExecutionException;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logInData(req);

        try {

            APIResponseType responseType = getResponseType(req.getHeader("Content-Type"), resp);

            if(validateContentType(resp, responseType, validPostContentTypes, req.getContentType())) {

                RESTConsumer consumer = processCall(req, resp, responseType);

                if (consumer!=null && consumer.isAuthenticated()) {

                    doPostSpecific(req, resp, responseType, consumer);
                }
            }

        } catch (StopServletExecutionException e) {

            logger.info(e.getMessage());

        } catch(Exception e) {

            resp.getOutputStream().println("Unhandled internal error, can't give proper error feedback.");
            logger.fatal(e.getMessage());
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unhandled internal error, can't give proper error feedback.");
        }
    }

    protected abstract void doPostSpecific(HttpServletRequest req, HttpServletResponse resp, APIResponseType responseType, RESTConsumer consumer) throws IOException, StopServletExecutionException;

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logInData(req);

        try {

            APIResponseType responseType = getResponseType(req.getHeader("Content-Type"), resp);

            RESTConsumer consumer = processCall(req, resp, responseType);

            if (consumer.isAuthenticated()) {

                doPutSpecific(req, resp, responseType, consumer);
            }

        } catch (StopServletExecutionException e) {

            logger.info(e.getMessage());

        } catch(Exception e) {

            resp.getOutputStream().println("Unhandled internal error, can't give proper error feedback.");
            logger.fatal(e.getMessage());
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unhandled internal error, can't give proper error feedback.");
        }
    }

    protected abstract void doPutSpecific(HttpServletRequest req, HttpServletResponse resp, APIResponseType responseType, RESTConsumer consumer) throws IOException, StopServletExecutionException;

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logInData(req);

        try {

            APIResponseType responseType = getResponseType(req.getHeader("Content-Type"), resp);

            RESTConsumer consumer = processCall(req, resp, responseType);

            if (consumer.isAuthenticated()) {

                doDeleteSpecific(req, resp, responseType, consumer);
            }

        } catch (StopServletExecutionException e) {

            logger.info(e.getMessage());

        } catch(Exception e) {

            resp.getOutputStream().println("Unhandled internal error, can't give proper error feedback.");
            logger.fatal(e.getMessage());
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unhandled internal error, can't give proper error feedback.");
        }
    }

    protected abstract void doDeleteSpecific(HttpServletRequest req, HttpServletResponse resp, APIResponseType responseType, RESTConsumer consumer) throws IOException, StopServletExecutionException;

    protected RESTConsumer processCall(HttpServletRequest req, HttpServletResponse resp, APIResponseType responseType) throws StopServletExecutionException {

        RESTConsumer consumer = null;

        try {

            if (req.getParameter(consumerKeyParameterName) != null && req.getHeader(consumerKeyParameterName) == null) {

                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, addError(INVALID_CONSUMER_KEY_PARAMETER_TYPE, responseType));
                throw new StopServletExecutionException();

            } else if (req.getHeader(consumerKeyParameterName) == null) {

                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, addError(MISSING_CONSUMER_KEY, responseType));
                throw new StopServletExecutionException();

            } else {

                String cid = req.getHeader(consumerKeyParameterName);

                if (cid == null) {

                    cid = req.getParameter(consumerKeyParameterName);
                }

                consumer = getConsumer(test, req, cid, APIRequestType.GET);

                if (!consumer.isAuthenticated()) {

                    resp.sendError(HttpServletResponse.SC_FORBIDDEN, addError(INVALID_CONSUMER_KEY, responseType));
                    throw new StopServletExecutionException();
                }
            }

        } catch(IOException e) {

            logger.fatal(e.getMessage());
        }

        return consumer;
    }

    protected abstract RESTConsumer getConsumer(boolean test, HttpServletRequest req, String consumerKey, APIRequestType requestType);

    protected APIResponseType getResponseType(String responseParameter, HttpServletResponse resp) throws IOException, StopServletExecutionException {

        APIResponseType responseType = APIResponseType.NONE;

        try {

            if(responseParameter!=null) {

                responseType = APIResponseType.getByName(responseParameter, forceDataType);
            }

            if(responseType==APIResponseType.NONE && forceDataType) {

                resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, addError(RESTErrorCode.INVALID_CONTENT_TYPE_FORCED, defaultResponseType));

                throw new StopServletExecutionException();

            } else if(responseType==APIResponseType.NONE) {

                responseType = defaultResponseType;
            }

            boolean notValidResponseType = true;
            for(APIResponseType valid: validResponseTypesList) {

                if(valid==responseType) {

                    notValidResponseType = false;
                }
            }
            if(notValidResponseType) {

                String validString = "";
                for(APIResponseType v: validResponseTypesList) {
                    validString += v.getMimeType()+",";
                }
                validString = validString.substring(0, validString.length()-1);

                resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, addError("41501", "Content-Type with value "+responseParameter+" isn't supported. Please use one of the valid types ["+validString+"].", "General technical problem.", defaultResponseType));

                throw new StopServletExecutionException();
            }

        } catch(Exception e) {

            if(e instanceof StopServletExecutionException) {

                logger.debug(e.getMessage());

            } else {

                logger.debug("Requested response type " + responseParameter + " don't exist!");
                resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, addError(RESTErrorCode.INVALID_CONTENT_TYPE, defaultResponseType));
            }

            throw new StopServletExecutionException();
        }

        return responseType;
    }

    protected  boolean validateContentType(HttpServletResponse resp, APIResponseType responseType, List<String> validList, String contentType) throws IOException {

        if(!forceContentTypeInput) {

            return true;
        }

        for(String validPost: validList) {

            if(validPost.equalsIgnoreCase(contentType)) {

                return true;
            }
        }

        String validTypes = "";
        for(String validPost: validList) {
            validTypes += validPost+", ";
        }
        validTypes = validTypes.substring(0, validTypes.length()-2);

        resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, addError("41501", "Content-Type with value "+contentType+" isn't supported. Please use one of the valid types ("+validTypes+").", "General technical problem.", responseType));

        return false;
    }

    protected String addError(RESTErrorCode errorCode, APIResponseType responseType) {

        return addError(errorCode.getId(), errorCode.getErrorMessage(), errorCode.getEndUserErrorMessage(), responseType);
    }

    protected String addError(String errorCode, String errorMessage, String endUserErrorMessage, APIResponseType responseType) {

        if(responseType==APIResponseType.JSON) {

            Map<String,String> errorMap = new HashMap<String, String>();

            errorMap.put("error_code", errorCode);
            errorMap.put("error_message", errorMessage);
            errorMap.put("end_user_error_message", endUserErrorMessage);

            JSONObject errorObj = new JSONObject(errorMap);

            //resp.sendError(returnCode, errorObj.toString(2));
            logger.debug(errorObj.toString(2));

            return errorObj.toString(2);

        } else if(responseType==APIResponseType.XML) {

            String error = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n";
            error += "<error xmlns=\"http://www.moneypal.se\">\n";
            error += "\t<error_code>"+errorCode+"</error_code>\n";
            error += "\t<error_message>"+errorMessage+"</error_message>\n";
            error += "\t<end_user_error_message>"+endUserErrorMessage+"</end_user_error_message>\n";
            error += "</error>\n";

            //resp.getWriter().print(error);
            logger.debug(error);

            return error;
        }

        logger.debug(errorCode+" "+errorMessage);

        return errorCode + " - " +errorMessage;
    }

    private void logInData(HttpServletRequest req) {

        logger.debug("***********************************************");
        logger.debug("Call from: "+req.getRemoteAddr()+":"+req.getRemoteHost()+":"+req.getRemotePort());
        logger.debug("Call to: "+req.getRequestURI());
        logger.debug("Call method: "+req.getMethod());

        logger.debug("Headers");
        logger.debug("-------------------");

        Enumeration headerNames = req.getHeaderNames();
        if(headerNames!=null) {
            while (headerNames.hasMoreElements()) {

                String head = headerNames.nextElement().toString();
                logger.debug("'" + head + "':'" + req.getHeader(head) + "'");
            }
        }

        logger.debug("Query parameters");
        logger.debug("-------------------");
        if(req.getQueryString()==null||req.getQueryString().length()==0) {
            logger.debug("No query parameters present.");
        } else {
            logger.debug(req.getQueryString());
        }

        logger.debug("***********************************************");
        logger.debug(" ");
    }

}
