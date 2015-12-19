package org.framework42.http.server.html.head;

public enum Rel {

    ALTERNATE("alternate"),
    AUTHOR("author"),
    HELP("help"),
    ICON("icon"),
    LICENSE("license"),
    NEXT("next"),
    PREFETCH("prefetch"),
    PREV("prev"),
    SEARCH("search"),
    STYLESHEET("stylesheet")
    ;

    private final String value;

    Rel(String value) {

        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
