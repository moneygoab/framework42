package org.framework42.web.utils.rest;

import org.framework42.model.APIResponseType;

import javax.servlet.http.HttpServletResponse;

public enum IntegerParser {

    INSTANCE;

    private IntegerParser() {
    }

    public int parse(HttpServletResponse resp, APIResponseType responseType, String parameterName, String parameter) throws IllegalArgumentException {


        if(parameter==null) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ErrorMaker.INSTANCE.addError(resp, "40028", "Required parameter "+parameterName+" missing!", "General technical problem.", responseType);
            throw new IllegalArgumentException();
        }

        try {

            return Integer.parseInt(parameter);

        } catch (NumberFormatException e) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ErrorMaker.INSTANCE.addError(resp, "40029", "Required parameter "+parameterName+" not a valid number. It should be an integer.", "General technical problem.", responseType);
            throw new IllegalArgumentException();
        }
    }

}
