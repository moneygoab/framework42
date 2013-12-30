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
import java.util.HashMap;
import java.util.Map;

import static org.framework42.web.pagemodel.RESTErrorCode.*;

public abstract class RESTWebPage extends HttpServlet {

    private final static Logger logger = Logger.getLogger("org.framework42.web");

    private int consumerId;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        APIResponseType responseType = getResponseType(req.getParameter("response_type"));

        consumerId = processCall(req, resp, responseType);

        if(consumerId>0) {

            doGetSpecific(req, resp, responseType, consumerId);
        }
    }

    protected abstract void doGetSpecific(HttpServletRequest req, HttpServletResponse resp, APIResponseType responseType, int consumerId);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        APIResponseType responseType = getResponseType(req.getParameter("response_type"));

        consumerId = processCall(req, resp, responseType);

        if(consumerId>0) {

            doPostSpecific(req, resp, responseType, consumerId);
        }
    }

    protected abstract void doPostSpecific(HttpServletRequest req, HttpServletResponse resp, APIResponseType responseType, int consumerId);

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        APIResponseType responseType = getResponseType(req.getParameter("response_type"));

        consumerId = processCall(req, resp, responseType);

        if(consumerId>0) {

            doPutSpecific(req, resp, responseType, consumerId);
        }
    }

    protected abstract void doPutSpecific(HttpServletRequest req, HttpServletResponse resp, APIResponseType responseType, int consumerId);

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
    }



    protected int processCall(HttpServletRequest req, HttpServletResponse resp, APIResponseType responseType) {

        consumerId = 0;

        if(req.getParameter("consumer_key")==null&&req.getHeader("consumer_key")==null) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            addError(resp, MISSING_CONSUMER_KEY, responseType);

        } else {

            String cid = req.getParameter("consumer_key");

            if(cid==null) {

                cid = req.getHeader("consumer_key");
            }

            consumerId = getConsumerId(cid, APIRequestType.GET);

            if(consumerId==0) {

                resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                addError(resp, INVALID_CONSUMER_KEY, responseType);
            }
        }

        return consumerId;
    }

    protected abstract int getConsumerId(String consumerKey, APIRequestType requestType);

    protected APIResponseType getResponseType(String responseParameter) {

        APIResponseType responseType = APIResponseType.JSON;
        try {

            if(responseParameter!=null) {

                responseType = APIResponseType.getByName(responseParameter);
            }

        } catch(Exception e) {

            logger.debug("Requested response type "+responseParameter+" don't exist!");
        }

        return responseType;
    }

    protected void addError(HttpServletResponse resp, RESTErrorCode errorCode, APIResponseType responseType) {

        addError(resp, errorCode.getId(), errorCode.getErrorMessage(), responseType);
    }

    protected void addError(HttpServletResponse resp, String errorCode, String errorMessage, APIResponseType responseType) {

        try {

            if(responseType==APIResponseType.JSON) {

                Map<String,String> errorMap = new HashMap<String, String>();

                errorMap.put("error_code", errorCode);
                errorMap.put("error_message", errorMessage);

                JSONObject errorObj = new JSONObject(errorMap);

                resp.getWriter().println(errorObj.toString(3));

            }

        } catch(IOException e) {

            logger.fatal(e.getMessage());
        }
    }


}
