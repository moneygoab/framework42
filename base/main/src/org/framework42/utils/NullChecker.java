package org.framework42.utils;

import org.apache.log4j.Logger;

public class NullChecker {

    private final static Logger logger = Logger.getLogger("org.framework42");

    public static <T> T notNull(T variable) {

        return notNull(variable, "Variable can't be null", logger);
    }

    public static <T> T notNull(T variable, String errorMessage) {

        return notNull(variable, errorMessage, logger);
    }

    public static <T> T notNull(T variable, String errorMessage, String loggerId) {

        Logger logger = Logger.getLogger(loggerId);

        return notNull(variable, errorMessage, logger);
    }

    public static <T> T notNull(T variable, String errorMessage, Logger logger) {

        if(variable == null) {
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        return variable;
    }

}
