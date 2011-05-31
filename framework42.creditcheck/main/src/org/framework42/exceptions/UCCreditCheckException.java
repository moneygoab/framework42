package org.framework42.exceptions;

public class UCCreditCheckException extends Exception {

    private final int errorCode;

    public UCCreditCheckException(int errorCode) {

        this.errorCode = errorCode;
    }

    public UCCreditCheckException(int errorCode, String message) {

        super(message);
        
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
