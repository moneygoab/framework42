package org.framework42.web.utils.rest;

import org.apache.log4j.Logger;
import org.framework42.model.APIResponseType;
import org.framework42.web.pagemodel.RESTErrorCode;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public enum ErrorMaker {

    INSTANCE;

    private final static Logger logger = Logger.getLogger("com.moneypal.banksystem.parsers");

    public void addError(HttpServletResponse resp, RESTErrorCode errorCode, APIResponseType responseType) {

        addError(resp, errorCode.getId(), errorCode.getErrorMessage(), errorCode.getEndUserErrorMessage(), responseType);
    }

    public void addError(HttpServletResponse resp, String errorCode, String errorMessage, String endUserErrorMessage, APIResponseType responseType) {

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
