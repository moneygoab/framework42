package org.framework42.web.pages;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.framework42.model.APIRequestType;
import org.framework42.model.APIResponseType;
import org.framework42.web.authorization.RESTConsumer;
import org.framework42.web.exceptions.StopServletExecutionException;
import org.framework42.web.pagemodel.RESTErrorCode;
import org.framework42.web.utils.rest.RESTErrorMaker;

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

        resp.setCharacterEncoding("UTF-8");

        logInData(req);

        try {

            APIResponseType responseType = getResponseType(req.getHeader("Content-Type"), resp);
            resp.setHeader("Access-Control-Allow-Origin", "*");

            RESTConsumer consumer = processCall(req, resp, responseType);

            if (consumer!=null && consumer.isAuthenticated()) {

                doGetSpecific(req, resp, responseType, consumer);
            }

        } catch (StopServletExecutionException e) {

            logger.info("RESTPoint.doGet - "+req.getRequestURI()+" - "+e.getClass().getName()+" - "+e.getMessage());

        } catch(Exception e) {

            //resp.getOutputStream().println("Unhandled internal error, can't give proper error feedback.");
            logger.fatal("RESTPoint.doGet - "+req.getRequestURI()+" - "+e.getClass().getName()+" - "+e.getMessage());
            logger.error(e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unhandled internal error, can't give proper error feedback.");
        }
    }

    protected abstract void doGetSpecific(HttpServletRequest req, HttpServletResponse resp, APIResponseType responseType, RESTConsumer consumer) throws IOException, StopServletExecutionException;

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.debug("Receiving options call to: "+req.getRequestURI());

        resp.setCharacterEncoding("UTF-8");

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
            logger.fatal("RESTPoint.doOptions - "+req.getRequestURI()+" - "+e.getClass().getName()+" - "+e.getMessage());
            logger.error(e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unhandled internal error, can't give proper error feedback.");
        }
    }

    protected abstract void doOptionsSpecific(HttpServletRequest req, HttpServletResponse resp, APIResponseType responseType) throws IOException, StopServletExecutionException;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.debug("Receiving post call to: "+req.getRequestURI());

        resp.setCharacterEncoding("UTF-8");

        logInData(req);

        try {

            APIResponseType responseType = getResponseType(req.getHeader("Content-Type"), resp);
            if(req.getHeader("Content-Type")==null) {
                responseType = getResponseType(req.getContentType(), resp);
            }

            if(validateContentType(resp, responseType, validPostContentTypes, responseType.getMimeType())) {

                RESTConsumer consumer = processCall(req, resp, responseType);

                if (consumer!=null && consumer.isAuthenticated()) {

                    doPostSpecific(req, resp, responseType, consumer);
                }
            }

        } catch (StopServletExecutionException e) {

            logger.info(e.getMessage());

        } catch(Exception e) {

            resp.getOutputStream().println("Unhandled internal error, can't give proper error feedback.");
            logger.fatal("RESTPoint.doPost - "+req.getRequestURI()+" - "+e.getClass().getName()+" - "+e.getMessage());
            logger.error(e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unhandled internal error, can't give proper error feedback.");
        }
    }

    protected abstract void doPostSpecific(HttpServletRequest req, HttpServletResponse resp, APIResponseType responseType, RESTConsumer consumer) throws IOException, StopServletExecutionException;

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.debug("Receiving put call to: "+req.getRequestURI());

        resp.setCharacterEncoding("UTF-8");

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
            logger.fatal("RESTPoint.doPut - "+req.getRequestURI()+" - "+e.getClass().getName()+" - "+e.getMessage());
            logger.error(e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unhandled internal error, can't give proper error feedback.");
        }
    }

    protected abstract void doPutSpecific(HttpServletRequest req, HttpServletResponse resp, APIResponseType responseType, RESTConsumer consumer) throws IOException, StopServletExecutionException;

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.debug("Receiving delete call to: "+req.getRequestURI());

        resp.setCharacterEncoding("UTF-8");

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
            logger.fatal("RESTPoint.doDelete - "+req.getRequestURI()+" - "+e.getClass().getName()+" - "+e.getMessage());
            logger.error(e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unhandled internal error, can't give proper error feedback.");
        }
    }

    protected abstract void doDeleteSpecific(HttpServletRequest req, HttpServletResponse resp, APIResponseType responseType, RESTConsumer consumer) throws IOException, StopServletExecutionException;

    protected RESTConsumer processCall(HttpServletRequest req, HttpServletResponse resp, APIResponseType responseType) throws StopServletExecutionException {

        RESTConsumer consumer = null;

        try {

            if (req.getParameter(consumerKeyParameterName) != null && req.getHeader(consumerKeyParameterName) == null) {

                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, RESTErrorMaker.INSTANCE.addError(INVALID_CONSUMER_KEY_PARAMETER_TYPE, responseType));
                logger.debug("Error consumer key parameter missing "+consumerKeyParameterName);
                throw new StopServletExecutionException();

            } else if (req.getHeader(consumerKeyParameterName) == null) {

                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, RESTErrorMaker.INSTANCE.addError(MISSING_CONSUMER_KEY, responseType));
                logger.debug("Error consumer key parameter missing "+consumerKeyParameterName);
                throw new StopServletExecutionException();

            } else {

                String cid = req.getHeader(consumerKeyParameterName);

                if (cid == null) {

                    cid = req.getParameter(consumerKeyParameterName);
                }
                logger.debug("Consumer key:"+cid);

                consumer = getConsumer(test, req, cid, APIRequestType.GET);

                if (!consumer.isAuthenticated()) {

                    resp.sendError(HttpServletResponse.SC_FORBIDDEN, RESTErrorMaker.INSTANCE.addError(INVALID_CONSUMER_KEY, responseType));
                    throw new StopServletExecutionException();
                }
            }

        } catch(IOException e) {

            logger.fatal("RESTPoint.processCall - "+req.getRequestURI()+" - "+e.getClass().getName()+" - "+e.getMessage());
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

                resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, RESTErrorMaker.INSTANCE.addError(RESTErrorCode.INVALID_CONTENT_TYPE_FORCED, defaultResponseType));

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

                resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, RESTErrorMaker.INSTANCE.addError("41501", "Content-Type with value "+responseParameter+" isn't supported. Please use one of the valid types ["+validString+"].", "General technical problem.", defaultResponseType));

                throw new StopServletExecutionException();
            }

        } catch(Exception e) {

            if(e instanceof StopServletExecutionException) {

                logger.debug(e.getMessage());

            } else {

                logger.debug("RESTPoint.getResponseType - Requested response type " + responseParameter + " don't exist!");
                resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, RESTErrorMaker.INSTANCE.addError(RESTErrorCode.INVALID_CONTENT_TYPE, defaultResponseType));
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

        logger.debug("Content-Type with value " + contentType + " isn't supported. Please use one of the valid types (" + validTypes + ").");
        resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, RESTErrorMaker.INSTANCE.addError("41501", "Content-Type with value " + contentType + " isn't supported. Please use one of the valid types (" + validTypes + ").", "General technical problem.", responseType));

        return false;
    }

    private void logInData(HttpServletRequest req) {

        try {

            if (logger.getLevel() == Level.DEBUG) {

                logger.debug("***********************************************");
                logger.debug("Call from: " + req.getRemoteAddr() + ":" + req.getRemoteHost() + ":" + req.getRemotePort());
                logger.debug("Call to: " + req.getRequestURI());
                logger.debug("Call method: " + req.getMethod());

                logger.debug("Headers");
                logger.debug("-------------------");

                Enumeration headerNames = req.getHeaderNames();
                if (headerNames != null) {
                    while (headerNames.hasMoreElements()) {

                        String head = headerNames.nextElement().toString();
                        logger.debug("'" + head + "':'" + req.getHeader(head) + "'");
                    }
                }

                logger.debug("Query parameters");
                logger.debug("-------------------");
                if (req.getQueryString() == null || req.getQueryString().length() == 0) {
                    logger.debug("No query parameters present.");
                } else {
                    logger.debug(req.getQueryString());
                }

                logger.debug("***********************************************");
                logger.debug(" ");
            }

        } catch (Exception e) {

            logger.debug("Problem with logging in data");
            logger.debug(e);
        }
    }

}
