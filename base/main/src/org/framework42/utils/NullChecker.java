package org.framework42.utils;

import org.apache.log4j.Logger;

public class NullChecker {

    private final static Logger logger = Logger.getLogger("org.framework42");

    public static <T> T notNull(T variable) {

        if(variable == null) {
            logger.error("Variable can't be null");
            throw new IllegalArgumentException();
        }
        return variable;
    }

    public static <T> T notNull(T variable, String errorMessage) {

        if(variable == null) {
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        return variable;
    }

}
