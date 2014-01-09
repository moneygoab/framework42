package org.framework42.web.pagemodel;

public enum RESTErrorCode {

    MISSING_CONSUMER_KEY("1001", "Consumer key not set!"),
    INVALID_CONSUMER_KEY("1002", "Consumer key wrong or invalid!"),
    INVALID_CONSUMER_KEY_PARAMETER_TYPE("1003", "You have sent your Consumer key as a query/url parameter, this is invalid! You should send it as a Http Header/Request Property.")
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
