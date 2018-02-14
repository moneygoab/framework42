package org.framework42.web.utils.rest;

import org.framework42.model.APIResponseType;
import org.framework42.utils.services.Modulus10Calculator;
import org.framework42.utils.services.impl.Modulus10CalculatorImpl;

import javax.servlet.http.HttpServletResponse;

public enum GovernmentIdParser {

    INSTANCE;

    private final static Modulus10Calculator modulus10Calculator = new Modulus10CalculatorImpl();

    private GovernmentIdParser() {
    }

    public String parse(HttpServletResponse resp, APIResponseType responseType, String parameterName, String governmentId) throws IllegalArgumentException {

        if(governmentId==null) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ErrorMakerOld.INSTANCE.addError(resp, "40004", "Required parameter "+parameterName+" missing!", "General technical problem.", responseType);
            throw new IllegalArgumentException();
        }

        try {
            Long.parseLong(governmentId);

        } catch (NumberFormatException e) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ErrorMakerOld.INSTANCE.addError(resp, "40005", "Parameter "+parameterName+" should be 10 digits long in the format YYMMDDNNNN.", "Government id is wrong, please try again.", responseType);
            throw new IllegalArgumentException("Parameter "+parameterName+" should be 10 digits long in the format YYMMDDNNNN.");
        }

        if(governmentId.length()!=10) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ErrorMakerOld.INSTANCE.addError(resp, "40005", "Parameter "+parameterName+" should be 10 digits long in the format YYMMDDNNNN.", "Government id is wrong, please try again.", responseType);
            throw new IllegalArgumentException("Parameter "+parameterName+" should be 10 digits long in the format YYMMDDNNNN.");
        }

        if(modulus10Calculator.generateChecksum(governmentId.substring(0,9)) != Integer.parseInt(governmentId.substring(9))) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ErrorMakerOld.INSTANCE.addError(resp, "40006", "Parameter "+parameterName+" is invalid the control digits doesn't sum up.", "Government id is wrong, please try again.", responseType);
            throw new IllegalArgumentException();
        }

        return governmentId;
    }

}
