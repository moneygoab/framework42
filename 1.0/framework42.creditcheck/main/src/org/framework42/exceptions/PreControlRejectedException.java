package org.framework42.exceptions;

public class PreControlRejectedException extends Exception {

    public PreControlRejectedException() {

    }

    public PreControlRejectedException(String message) {

        super(message);
    }

    public PreControlRejectedException(String message, Throwable cause) {

        super(message, cause);
    }
}
