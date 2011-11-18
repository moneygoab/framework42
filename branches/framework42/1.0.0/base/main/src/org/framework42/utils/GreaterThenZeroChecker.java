package org.framework42.utils;

import org.apache.log4j.Logger;

public class GreaterThenZeroChecker {

    private final static Logger logger = Logger.getLogger("org.framework42");

    public static int greaterThenZero(int variable) {

        return greaterThenZero(variable, "Int can't be zero", logger);
    }

    public static int greaterThenZero(int variable, String errorMessage) {

        return greaterThenZero(variable, errorMessage, logger);
    }

    public static int greaterThenZero(int variable, String errorMessage, String loggerId) {

        Logger logger = Logger.getLogger(loggerId);

        return greaterThenZero(variable, errorMessage, logger);
    }

    public static int greaterThenZero(int variable, String errorMessage, Logger logger) {

        if(variable <= 0) {
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        return variable;
    }

    public static long greaterThenZero(long variable) {

        return greaterThenZero(variable, "Long can't be zero", logger);
    }

    public static long greaterThenZero(long variable, String errorMessage) {

        return greaterThenZero(variable, errorMessage, logger);
    }

    public static long greaterThenZero(long variable, String errorMessage, String loggerId) {

        Logger logger = Logger.getLogger(loggerId);

        return greaterThenZero(variable, errorMessage, logger);
    }

    public static long greaterThenZero(long variable, String errorMessage, Logger logger) {

        if(variable <= 0) {
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        return variable;
    }

    public static float greaterThenZero(float variable) {

        return greaterThenZero(variable, "Float can't be zero", logger);
    }

    public static float greaterThenZero(float variable, String errorMessage) {

        return greaterThenZero(variable, errorMessage, logger);
    }

    public static float greaterThenZero(float variable, String errorMessage, String loggerId) {

        Logger logger = Logger.getLogger(loggerId);

        return greaterThenZero(variable, errorMessage, logger);
    }

    public static float greaterThenZero(float variable, String errorMessage, Logger logger) {

        if(variable <= 0) {
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        return variable;
    }

    public static double greaterThenZero(double variable) {

        return greaterThenZero(variable, "Float can't be zero", logger);
    }

    public static double greaterThenZero(double variable, String errorMessage) {

        return greaterThenZero(variable, errorMessage, logger);
    }

    public static double greaterThenZero(double variable, String errorMessage, String loggerId) {

        Logger logger = Logger.getLogger(loggerId);

        return greaterThenZero(variable, errorMessage, logger);
    }

    public static double greaterThenZero(double variable, String errorMessage, Logger logger) {

        if(variable <= 0) {
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        return variable;
    }

}
