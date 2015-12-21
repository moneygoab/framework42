package org.framework42.http.server.html;

public enum TextDirection {

    LEFT_TO_RIGHT("ltr"),
    RIGHT_TO_LEFT("rtl"),
    AUTO("auto")
    ;

    private final String value;

    TextDirection(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
