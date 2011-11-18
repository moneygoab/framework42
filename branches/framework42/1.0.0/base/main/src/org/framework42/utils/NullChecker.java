package org.framework42.utils;

import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * This is a utility class that provides a general null checking capability that handles error handling and
 * logging in the null handling process.
 * */
public class NullChecker {

    private final static Logger logger = Logger.getLogger("org.framework42");

    /**
     * Checks if the variable is null or not, using the default logger and a default error message.
     * @param variable                      The variable to do a null check on.
     * @return T                            Returns the variable sent in if it's not null.
     * @throws IllegalArgumentException     Thrown if the tested variable is null.
     * */
    public static <T> T notNull(T variable) {

        return notNull(variable, "Variable can't be null", logger);
    }

    /**
     * Checks if the variable is null or not, using the default logger and a error message that is provided
     * by the caller.
     * @param variable                      The variable to do a null check on.
     * @param errorMessage                  A provided error message, that will make more sense then the default one.
     * @return T                            Returns the variable sent in if it's not null.
     * @throws IllegalArgumentException     Thrown if the tested variable is null.
     * */
    public static <T> T notNull(T variable, String errorMessage) {

        return notNull(variable, errorMessage, logger);
    }

    /**
     * Checks if the variable is null or not, using the default logger and a error message that is provided
     * by the caller.
     * @param variable                      The variable to do a null check on.
     * @param errorMessage                  A provided error message, that will make more sense then the default one.
     * @param loggerId                      The getId of the logger to use instead of the default logger.
     * @return T                            Returns the variable sent in if it's not null.
     * @throws IllegalArgumentException     Thrown if the tested variable is null.
     * */
    public static <T> T notNull(T variable, String errorMessage, String loggerId) {

        Logger logger = Logger.getLogger(loggerId);

        return notNull(variable, errorMessage, logger);
    }

    /**
     * Checks if the variable is null or not, using the default logger and a error message that is provided
     * by the caller.
     * @param variable                      The variable to do a null check on.
     * @param errorMessage                  A provided error message, that will make more sense then the default one.
     * @param logger                        The logger to use instead of the default logger.
     * @return T                            Returns the variable sent in if it's not null.
     * @throws IllegalArgumentException     Thrown if the tested variable is null.
     * */
    public static <T> T notNull(T variable, String errorMessage, Logger logger) {

        if(variable == null) {
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        if(variable instanceof Collection) {
            childrenNotNull((Collection)variable, errorMessage, logger);
        } else if(variable.getClass().isArray()) {
            childrenNotNull((Object[])variable, errorMessage, logger);
        }

        return variable;
    }

    private static void childrenNotNull(Collection variable, String errorMessage, Logger logger) {

        for(Object childObject : variable.toArray()) {

            if(childObject == null) {
                logger.error(errorMessage);
                throw new IllegalArgumentException(errorMessage);
            }

        }

    }

    private static void childrenNotNull(Object[] variable, String errorMessage, Logger logger) {

        for(Object childObject : variable) {

            if(childObject == null) {
                logger.error(errorMessage);
                throw new IllegalArgumentException(errorMessage);
            }

        }

    }

}
