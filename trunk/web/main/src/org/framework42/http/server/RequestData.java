package org.framework42.http.server;

import java.util.Map;

public class RequestData {

    private final Map<String,String> headerMap;

    public RequestData(Map<String, String> headerMap) {
        this.headerMap = headerMap;
    }

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }
}
