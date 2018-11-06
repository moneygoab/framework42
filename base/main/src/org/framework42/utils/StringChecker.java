package org.framework42.utils;

public enum StringChecker {

    INSTANCE;

    public boolean isValidRFC1738(String text) {

        boolean valid = true;

        for (char c: text.toCharArray()) {

            valid = ((c >= 'a') && (c <= 'z')) ||
                    ((c >= 'A') && (c <= 'Z')) ||
                    ((c >= '0') && (c <= '9')) ||
                    c== '$' || c== '-' || c== '_' ||
                    c== '.' || c== '+' || c== '!' ||
                    c== '*' || c== '\'' || c== '(' ||
                    c== ')' || c== ',';

            if (!valid) {
                break;
            }
        }

        return valid;
    }

}
