package org.framework42.web.pages;

import org.apache.log4j.Logger;
import org.framework42.model.APIRequestType;
import org.framework42.model.APIResponseType;
import org.framework42.web.pagemodel.RESTErrorCode;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.framework42.web.pagemodel.RESTErrorCode.*;

public abstract class RESTPoint extends HttpServlet {

    protected final static Logger logger = Logger.getLogger("org.framework42.web");

    protected int consumerId;

    protected String consumerKeyParameterName = "X-Consumer-Key";

    protected APIResponseType defaultResponseType = APIResponseType.JSON;

    protected boolean forceDataType = false;

    protected boolean forceContentTypeInput = false;

    protected boolean test = true;

    protected List<APIResponseType> validResponseTypesList = Arrays.asList(APIResponseType.JSON);

    protected List<String> validPostContentTypes = Arrays.asList("application/x-www-form-urlencoded");

    protected List<String> validPutContentTypes = Arrays.asList("application/x-www-form-urlencoded");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            APIResponseType responseType = getResponseType(req.getHeader("Content-Type"), resp);

            consumerId = processCall(req, resp, responseType);

            if (consumerId > 0) {

                doGetSpecific(req, resp, responseType, consumerId);
            }

        } catch(Exception e) {

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getOutputStream().println("Unhandled internal error, can't give proper error feedback.");
            logger.fatal(e.getMessage());
        }
    }

    protected abstract void doGetSpecific(HttpServletRequest req, HttpServletResponse resp, APIResponseType responseType, int consumerId);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            APIResponseType responseType = getResponseType(req.getHeader("Content-Type"), resp);

            if(validateContentType(resp, responseType, validPostContentTypes, req.getContentType())) {

                consumerId = processCall(req, resp, responseType);

                if (consumerId > 0) {

                    doPostSpecific(req, resp, responseType, consumerId);
                }
            }

        } catch(Exception e) {

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getOutputStream().println("Unhandled internal error, can't give proper error feedback.");
            logger.fatal(e.getMessage());
        }
    }

    protected abstract void doPostSpecific(HttpServletRequest req, HttpServletResponse resp, APIResponseType responseType, int consumerId);

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            APIResponseType responseType = getResponseType(req.getHeader("Content-Type"), resp);

            consumerId = processCall(req, resp, responseType);

            if (consumerId > 0) {

                doPutSpecific(req, resp, responseType, consumerId);
            }

        } catch(Exception e) {

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getOutputStream().println("Unhandled internal error, can't give proper error feedback.");
            logger.fatal(e.getMessage());
        }
    }

    protected abstract void doPutSpecific(HttpServletRequest req, HttpServletResponse resp, APIResponseType responseType, int consumerId);

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            APIResponseType responseType = getResponseType(req.getHeader("Content-Type"), resp);

            consumerId = processCall(req, resp, responseType);

            if (consumerId > 0) {

                doDeleteSpecific(req, resp, responseType, consumerId);
            }

        } catch(Exception e) {

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getOutputStream().println("Unhandled internal error, can't give proper error feedback.");
            logger.fatal(e.getMessage());
        }
    }

    protected abstract void doDeleteSpecific(HttpServletRequest req, HttpServletResponse resp, APIResponseType responseType, int consumerId);

    protected int processCall(HttpServletRequest req, HttpServletResponse resp, APIResponseType responseType) {

        consumerId = 0;

        if(req.getParameter(consumerKeyParameterName)!=null && req.getHeader(consumerKeyParameterName)==null) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            addError(resp, INVALID_CONSUMER_KEY_PARAMETER_TYPE, responseType);

        } else if(req.getHeader(consumerKeyParameterName)==null) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            addError(resp, MISSING_CONSUMER_KEY, responseType);

        } else {

            String cid = req.getHeader(consumerKeyParameterName);

            if(cid==null) {

                cid = req.getParameter(consumerKeyParameterName);
            }

            consumerId = getConsumerId(test, cid, APIRequestType.GET);

            if(consumerId==0) {

                resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                addError(resp, INVALID_CONSUMER_KEY, responseType);
            }
        }

        return consumerId;
    }

    protected abstract int getConsumerId(boolean test, String consumerKey, APIRequestType requestType);

    protected APIResponseType getResponseType(String responseParameter, HttpServletResponse resp) {

        APIResponseType responseType = APIResponseType.NONE;
        try {

            if(responseParameter!=null) {

                responseType = APIResponseType.getByName(responseParameter, forceDataType);
            }

            if(responseType==APIResponseType.NONE && forceDataType) {

                resp.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
                addError(resp, RESTErrorCode.INVALID_CONTENT_TYPE_FORCED, defaultResponseType);

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

                resp.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
                addError(resp, "41501", "Content-Type with value "+responseParameter+" isn't supported. Please use one of the valid types ["+validString+"].", "General technical problem.", defaultResponseType);
            }

        } catch(Exception e) {

            resp.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            addError(resp, RESTErrorCode.INVALID_CONTENT_TYPE, defaultResponseType);
            logger.debug("Requested response type "+responseParameter+" don't exist!");
        }

        return responseType;
    }

    protected  boolean validateContentType(HttpServletResponse resp, APIResponseType responseType, List<String> validList, String contentType) {

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

        addError(resp, "41501", "Content-Type with value "+contentType+" isn't supported. Please use one of the valid types ("+validTypes+").", "General technical problem.", responseType);

        return false;
    }

    protected void addError(HttpServletResponse resp, RESTErrorCode errorCode, APIResponseType responseType) {

        addError(resp, errorCode.getId(), errorCode.getErrorMessage(), errorCode.getEndUserErrorMessage(), responseType);
    }

    protected void addError(HttpServletResponse resp, String errorCode, String errorMessage, String endUserErrorMessage, APIResponseType responseType) {

        try {

            if(responseType==APIResponseType.JSON) {

                Map<String,String> errorMap = new HashMap<String, String>();

                errorMap.put("error_code", errorCode);
                errorMap.put("error_message", errorMessage);
                errorMap.put("end_user_error_message", endUserErrorMessage);

                JSONObject errorObj = new JSONObject(errorMap);

                resp.getWriter().println(errorObj.toString(2));

            }

        } catch(IOException e) {

            logger.fatal(e.getMessage());
        }
    }


}
