package org.framework42.http.server.html;

public enum Target {

    BLANK("_blank"), PARENT("_parent"), SELF("_self"), TOP("_top");

    private String value;

    Target(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
