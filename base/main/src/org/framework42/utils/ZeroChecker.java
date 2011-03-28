package org.framework42.utils;

import org.apache.log4j.Logger;

public class ZeroChecker {

    private static final Logger logger = Logger.getLogger("org.framework42");

    public static int notZero(int variable) {

        return notZero(variable, "Int can't be zero", logger);
    }

    public static int notZero(int variable, String errorMessage) {

        return notZero(variable, errorMessage, logger);
    }

    public static int notZero(int variable, String errorMessage, String loggerId) {

        Logger logger = Logger.getLogger(loggerId);

        return notZero(variable, errorMessage, logger);
    }

    public static int notZero(int variable, String errorMessage, Logger logger) {

        if(variable == 0) {
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        return variable;
    }

    public static long notZero(long variable) {

        return notZero(variable, "Long can't be zero", logger);
    }

    public static long notZero(long variable, String errorMessage) {

        return notZero(variable, errorMessage, logger);
    }

    public static long notZero(long variable, String errorMessage, String loggerId) {

        Logger logger = Logger.getLogger(loggerId);

        return notZero(variable, errorMessage, logger);
    }

    public static long notZero(long variable, String errorMessage, Logger logger) {

        if(variable == 0) {
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        return variable;
    }

    public static float notZero(float variable) {

        return notZero(variable, "Float can't be zero", logger);
    }

    public static float notZero(float variable, String errorMessage) {

        return notZero(variable, errorMessage, logger);
    }

    public static float notZero(float variable, String errorMessage, String loggerId) {

        Logger logger = Logger.getLogger(loggerId);

        return notZero(variable, errorMessage, logger);
    }

    public static float notZero(float variable, String errorMessage, Logger logger) {

        if(variable == 0) {
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        return variable;
    }

    public static double notZero(double variable) {

        return notZero(variable, "Float can't be zero", logger);
    }

    public static double notZero(double variable, String errorMessage) {

        return notZero(variable, errorMessage, logger);
    }

    public static double notZero(double variable, String errorMessage, String loggerId) {

        Logger logger = Logger.getLogger(loggerId);

        return notZero(variable, errorMessage, logger);
    }

    public static double notZero(double variable, String errorMessage, Logger logger) {

        if(variable == 0) {
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        return variable;
    }

}
