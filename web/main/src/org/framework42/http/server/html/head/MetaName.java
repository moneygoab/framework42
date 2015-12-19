package org.framework42.http.server.html.head;

public enum MetaName {

    CHARSET("charset"),
    KEYWORDS("keywords"),
    DESCRIPTION("description"),
    AUTHOR("author"),
    GENERATOR("generator")
    ;

    private final String id;

    private MetaName(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
