package org.framework42.web.pagemodel;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.framework42.model.ParameterType;
import org.framework42.web.exceptions.ParseUnrequiredException;
import org.framework42.web.session.UserSession;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;

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
     * @param session       The current session.
     * @return Returns all the http parameters sent to the page.
     * */
    public Map<String,Parameter> parseRequest(HttpServletRequest req, PageModel pageModel, UserSession session) {

        Map<String,Parameter> parsedParameters = new HashMap<String,Parameter>();
        Map<String,Parameter> pageParameters = pageModel.getPageParameters();

        if(ServletFileUpload.isMultipartContent(req)) {

            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");

            try {

                // Parse the request
                @SuppressWarnings("unchecked")
                List<FileItem> itemList = upload.parseRequest(req);

                for(FileItem item: itemList) {

                    if (item.isFormField()) {

                        String keyValue = item.getFieldName();

                        String value = item.getString();

                        try {

                            value = new String(item.getString().getBytes("ISO-8859-1"), "UTF-8");

                        } catch(UnsupportedEncodingException e) {

                            logger.debug(e.getMessage());
                        }

                        parseParameter(req, session, keyValue, value, parsedParameters, pageParameters);

                    } else {

                        pageModel.getFileItemList().add(item);
                    }
                }

            } catch(FileUploadException e) {

                logger.error(e);
            }

        } else {

            for(Object key : req.getParameterMap().keySet().toArray()) {

                String keyValue = key.toString();
                String value = req.getParameter(keyValue);

                value = washValue(value);

                parseParameter(req, session, keyValue, value, parsedParameters, pageParameters);
            }
        }

        isAllRequiredParametersSet(parsedParameters, pageParameters);

        return parsedParameters;

    }

    private String washValue(String value) {

        value = value.replaceAll("<", "&#60;");
        value = value.replaceAll(">", "&#62;");

        return value;
    }

    private void parseParameter(HttpServletRequest req, UserSession session, String keyValue, String value, Map<String,Parameter> parsedParameters, Map<String,Parameter> pageParameters) {

        if(pageParameters.containsKey(keyValue)) {

            Parameter parentParameter = pageParameters.get(keyValue);

            try {

                if(parentParameter.getParameterType() != ParameterType.IGNORE) {
                    parsedParameters.put(keyValue, createExistingParameter(req, parentParameter, value));
                }
            } catch(ParseUnrequiredException e) {

                logger.info(e);
            }

        } else {

            if(session.isAllowUndefinedParameters()) {

                parsedParameters.put(keyValue, new ParameterImpl<String>(keyValue, ParameterType.STRING, value));

            } else {

                logger.error("Undefined variable sent with id "+keyValue+" it should be defined in the page logic setupPageParametersSpecific method!");
            }
        }

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

    private Parameter createExistingParameter(HttpServletRequest req, Parameter parentParameter, String value) throws ParseUnrequiredException {

        Parameter parameter;

        String name = parentParameter.getParameterName();
        ParameterType type = parentParameter.getParameterType();
        boolean required = parentParameter.isRequired();

        if(ParameterType.STRING == type) {

            parameter = new ParameterImpl<String>(name, type, required, value);

        } else if(ParameterType.INTEGER == type) {

            parameter = parseInteger(name,type,required,value);

        } else if(ParameterType.DECIMAL == type) {

            parameter = parseDecimal(name,type,required,value);

        } else if(ParameterType.DATE == type) {

            parameter = parseDate(name,type,required,value);

        } else if(ParameterType.CHECKBOX == type) {

            parameter = parseCheckBox(req, name, type, required, value);

        } else {

            parameter = new ParameterImpl<Object>(name, type, required, value);

        }

        return parameter;

    }

    private Parameter parseInteger(String name, ParameterType type, boolean required, String value) throws ParseUnrequiredException {

        try {

            Integer parsedValue = Integer.parseInt(value);

            return new ParameterImpl<Integer>(name, type, required, parsedValue);

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

            return new ParameterImpl<Float>(name, type, required, parsedValue);

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

            return new ParameterImpl<Date>(name, type, required, parsedValue);

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

    private Parameter parseCheckBox(HttpServletRequest req, String name, ParameterType type, boolean required, String value) throws ParseUnrequiredException {

        if(req.getParameterValues(name)!=null) {

            Boolean checked = "true".equalsIgnoreCase(req.getParameterValues(name)[0]);

            return new ParameterImpl<Boolean>(name, type, required, checked);

        } else {

            return new ParameterImpl<Boolean>(name, type, required, false);
        }

    }

}
