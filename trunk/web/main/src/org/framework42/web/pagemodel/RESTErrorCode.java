package org.framework42.web.pagemodel;

public enum RESTErrorCode {

    MISSING_CONSUMER_KEY("1001", "Consumer key not set!"),
    INVALID_CONSUMER_KEY("1002", "Consumer key wrong or invalid!"),
    INVALID_CONSUMER_KEY_PARAMETER_TYPE("1003", "You have sent your Consumer key as a query/url parameter, this is invalid! You should send it as a Http Header."),
    INVALID_CONTENT_TYPE_FORCED("1010", "Content-Type Http Header is missing or invalid! You must specify the data format this way."),
    INVALID_CONTENT_TYPE("1011", "Provided Content-Type Http Header is invalid!")
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
