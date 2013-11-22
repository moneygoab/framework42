package org.framework42.web.pagemodel;

public enum RESTErrorCode {

    MISSING_CONSUMER_KEY("1001", "Consumer key not set!"),
    INVALID_CONSUMER_KEY("1002", "Consumer key wrong or invalid!")
    ;

    private final String id;

    private final String errorMessage;

    private RESTErrorCode(String id, String errorMessage) {
        this.id = id;
        this.errorMessage = errorMessage;
    }

    public String getId() {
        return id;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
