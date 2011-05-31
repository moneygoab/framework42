package org.framework42.exceptions;

public class UCCreditCheckException extends Exception {

    private int id;

    public UCCreditCheckException() {

    }

    public UCCreditCheckException(String message) {
        super(message);
    }

    public UCCreditCheckException(int id, String message) {
        super(message);

        this.id = id;
    }

    public UCCreditCheckException(int id, String message, Throwable cause) {
        super(message, cause);

        this.id = id;
    }

    public int getId() {
        return id;
    }

}
