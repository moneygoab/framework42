package org.framework42.utils;

public class NumericChecker {

    public static boolean isNumeric(String toCheckString) {

        try {

            Long.parseLong(toCheckString);

            return true;

        } catch(NumberFormatException e) {

            return false;
        }
    }

}
