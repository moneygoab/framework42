package org.framework42.web.pagemodel;

public enum RESTErrorCode {

    MISSING_CONSUMER_KEY("40022", "Consumer key not set!", "General technical problem."),
    INVALID_CONSUMER_KEY("40023", "Consumer key wrong or invalid!", "General technical problem."),
    INVALID_CONSUMER_KEY_PARAMETER_TYPE("40024", "You have sent your Consumer key as a query/url parameter, this is invalid! You should send it as a Http Header.", "General technical problem."),
    INVALID_CONTENT_TYPE_FORCED("40025", "Content-Type Http Header is missing or invalid! You must specify the data format this way.", "General technical problem."),
    INVALID_CONTENT_TYPE("40026", "Provided Content-Type Http Header is invalid!", "General technical problem.")
    ;

    private final String id;

    private final String errorMessage;

    private final String endUserErrorMessage;

    private RESTErrorCode(String id, String errorMessage, String endUserErrorMessage) {
        this.id = id;
        this.errorMessage = errorMessage;
        this.endUserErrorMessage = endUserErrorMessage;
    }

    public String getId() {
        return id;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getEndUserErrorMessage() {
        return endUserErrorMessage;
    }
}
