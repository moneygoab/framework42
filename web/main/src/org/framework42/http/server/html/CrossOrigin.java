package org.framework42.http.server.html;

public enum CrossOrigin {

    ANONYMOUS("anonymous"),
    USE_CREDENTIALS("use-credentials")
    ;

    private final String value;

    CrossOrigin(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
