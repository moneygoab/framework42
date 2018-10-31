package org.framework42.utils;

import org.apache.log4j.Logger;

import java.math.BigDecimal;

public class NotNegativeChecker {

    private final static Logger logger = Logger.getLogger("org.framework42");

    public static int notNegative(int variable) {

        return notNegative(variable, "Int can't be negative", logger);
    }

    public static int notNegative(int variable, String errorMessage) {

        return notNegative(variable, errorMessage, logger);
    }

    public static int notNegative(int variable, String errorMessage, String loggerId) {

        Logger logger = Logger.getLogger(loggerId);

        return notNegative(variable, errorMessage, logger);
    }

    public static int notNegative(int variable, String errorMessage, Logger logger) {

        if(variable < 0) {
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        return variable;
    }

    public static long notNegative(long variable) {

        return notNegative(variable, "Long can't be negative", logger);
    }

    public static long notNegative(long variable, String errorMessage) {

        return notNegative(variable, errorMessage, logger);
    }

    public static long notNegative(long variable, String errorMessage, String loggerId) {

        Logger logger = Logger.getLogger(loggerId);

        return notNegative(variable, errorMessage, logger);
    }

    public static long notNegative(long variable, String errorMessage, Logger logger) {

        if(variable < 0) {
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        return variable;
    }

    public static float notNegative(float variable) {

        return notNegative(variable, "Float can't be negative", logger);
    }

    public static float notNegative(float variable, String errorMessage) {

        return notNegative(variable, errorMessage, logger);
    }

    public static float notNegative(float variable, String errorMessage, String loggerId) {

        Logger logger = Logger.getLogger(loggerId);

        return notNegative(variable, errorMessage, logger);
    }

    public static float notNegative(float variable, String errorMessage, Logger logger) {

        if(variable < 0) {
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        return variable;
    }

    public static double notNegative(double variable) {

        return notNegative(variable, "Float can't be negative", logger);
    }

    public static double notNegative(double variable, String errorMessage) {

        return notNegative(variable, errorMessage, logger);
    }

    public static double notNegative(double variable, String errorMessage, String loggerId) {

        Logger logger = Logger.getLogger(loggerId);

        return notNegative(variable, errorMessage, logger);
    }

    public static double notNegative(double variable, String errorMessage, Logger logger) {

        if(variable < 0) {
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        return variable;
    }


    public static BigDecimal notNegative(BigDecimal variable) {

        return notNegative(variable, "BigDecimal can't be negative", logger);
    }

    public static BigDecimal notNegative(BigDecimal variable, String errorMessage) {

        return notNegative(variable, errorMessage, logger);
    }

    public static BigDecimal notNegative(BigDecimal variable, String errorMessage, String loggerId) {

        Logger logger = Logger.getLogger(loggerId);

        return notNegative(variable, errorMessage, logger);
    }

    public static BigDecimal notNegative(BigDecimal variable, String errorMessage, Logger logger) {

        if(variable.compareTo(BigDecimal.ZERO) < 0) {
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        return variable;
    }

}
