package org.framework42.exceptions;

public class ManageableException extends Exception {

    private final String userMessage;

    public ManageableException(String userMessage) {

        this.userMessage = userMessage;
    }

    public ManageableException(String message, String userMessage) {
        super(message);
        this.userMessage = userMessage;
    }

    public String getUserMessage() {

        return userMessage;
    }
}
