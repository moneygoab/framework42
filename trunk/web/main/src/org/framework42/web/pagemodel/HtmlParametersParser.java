package org.framework42.web.pagemodel;

import org.apache.log4j.Logger;
import org.framework42.web.exceptions.ParseUnrequiredException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * This service class handles the parsing of html parameters that is sent to a page (both post and get parameters).
 * */
public enum HtmlParametersParser {

    INSTANCE;

    private final Logger logger = Logger.getLogger("org.framework42.web");

    /**
     * Parses an http request for it's parameters.
     * @param req           The http request.
     * @param pageModel     The page model.
     * @return Returns all the http parameters sent to the page.
     * */
    public Map<String,Parameter> parseRequest(HttpServletRequest req, PageModel pageModel) {

        Map<String,Parameter> parsedParameters = new HashMap<String,Parameter>();
        Map<String,Parameter> pageParameters = pageModel.getPageParameters();

        for(Object key : req.getParameterMap().keySet().toArray()) {

            String keyValue = key.toString();

            if(pageParameters.containsKey(keyValue)) {

                Parameter parentParameter = pageParameters.get(keyValue);
                String value = req.getParameter(keyValue);

                try {

                    parsedParameters.put(keyValue, createExistingParameter(parentParameter, value));

                } catch(ParseUnrequiredException e) {

                    logger.info(e);
                }
            } else {

                logger.error("Undefined variable sent with id "+keyValue+" it should be defined in the page logic setupPageParametersSpecific method!");
            }
        }

        isAllRequiredParametersSet(parsedParameters, pageParameters);

        return parsedParameters;

    }

    private void isAllRequiredParametersSet(Map<String,Parameter> parsedParameters, Map<String,Parameter> pageParameters) {

        for(Parameter param : pageParameters.values()) {

            if(param.isRequired()) {
                if(!parsedParameters.containsKey(param.getParameterName())) {

                    String name = param.getParameterName();
                    String type = param.getParameterType().name();
                    
                    String errorMess = "The parameter "+name+" of type "+type+" is required but not set!";
                    logger.error(errorMess);
                    throw new IllegalArgumentException(errorMess);

                }
            }

        }

    }

    private Parameter createExistingParameter(Parameter parentParameter, String value) throws ParseUnrequiredException {

        Parameter parameter;

        String name = parentParameter.getParameterName();
        ParameterType type = parentParameter.getParameterType();
        boolean required = parentParameter.isRequired();

        if(ParameterType.STRING == type) {

            parameter = new ParameterImpl(name, type, required, value);



        } else if(ParameterType.INTEGER == type) {

            parameter = parseInteger(name,type,required,value);

        } else if(ParameterType.DECIMAL == type) {

            parameter = parseDecimal(name,type,required,value);

        } else if(ParameterType.DATE == type) {

            parameter = parseDate(name,type,required,value);

        } else {

            parameter = new ParameterImpl(name, type, required, value);

        }

        return parameter;

    }

    private Parameter parseInteger(String name, ParameterType type, boolean required, String value) throws ParseUnrequiredException {

        try {

            Integer parsedValue = Integer.parseInt(value);

            return new ParameterImpl(name, type, required, parsedValue);

        } catch(NumberFormatException e) {

            if(required) {

                String errorMess = "The parameter "+name+" of type "+type+" contains an illegal value of "+value;
                logger.error(errorMess);
                throw new IllegalArgumentException(errorMess);
            } else {

                throw new ParseUnrequiredException("The unrequired parameter "+name+" of type "+type+" contains an illegal value of "+value);
            }
        }

    }

    private Parameter parseDecimal(String name, ParameterType type, boolean required, String value) throws ParseUnrequiredException {

        try{

            Float parsedValue = Float.parseFloat(value);

            return new ParameterImpl(name, type, required, parsedValue);

        } catch (NumberFormatException e) {

            if(required) {

                String errorMess = "The parameter "+name+" of type "+type+" contains an illegal value of "+value;
                logger.error(errorMess);
                throw new IllegalArgumentException(errorMess);
            } else {

                throw new ParseUnrequiredException("The unrequired parameter "+name+" of type "+type+" contains an illegal value of "+value);
            }

        }

    }

    private Parameter parseDate(String name, ParameterType type, boolean required, String value) throws ParseUnrequiredException {

        try{

            String[] dateValues = value.split("-");

            int year = Integer.parseInt(dateValues[0]);
            int month = Integer.parseInt(dateValues[1]);
            int day = Integer.parseInt(dateValues[2]);

            Date parsedValue = new GregorianCalendar(year, month, day).getTime();

            return new ParameterImpl(name, type, required, parsedValue);

        } catch (NumberFormatException e) {

            if(required) {

                String errorMess = "The parameter "+name+" of type "+type+" contains an illegal value of "+value;
                logger.error(errorMess);
                throw new IllegalArgumentException(errorMess);
            } else {

                throw new ParseUnrequiredException("The unrequired parameter "+name+" of type "+type+" contains an illegal value of "+value);
            }

        }

    }

}
