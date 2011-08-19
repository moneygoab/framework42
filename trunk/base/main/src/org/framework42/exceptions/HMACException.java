package org.framework42.exceptions;

public class HMACException extends Exception {

    public HMACException() {

    }

    public HMACException(String message) {

        super(message);
    }

    public HMACException(String message, Throwable cause) {

        super(message, cause);
    }

    public HMACException(Throwable cause) {

        super(cause);
    }
}
