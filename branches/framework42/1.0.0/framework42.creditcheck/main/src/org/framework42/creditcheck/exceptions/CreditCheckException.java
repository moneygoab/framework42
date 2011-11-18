package org.framework42.creditcheck.exceptions;

public class CreditCheckException extends Exception {

    private int id;

    public CreditCheckException() {

    }

    public CreditCheckException(String message) {
        super(message);
    }

    public CreditCheckException(int errorId, String message) {
        super(message);

        this.id = errorId;
    }

    public CreditCheckException(int errorId, String message, Throwable cause) {
        super(message, cause);

        this.id = errorId;
    }

    public int getId() {
        return id;
    }

}
