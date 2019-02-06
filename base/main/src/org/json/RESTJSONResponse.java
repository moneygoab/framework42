package org.json;

import java.util.List;
import java.util.Map;

public class RESTJSONResponse {

    private final int status;

    private final Map<String, List<String>> headers;

    private final JSONObject object;

    public RESTJSONResponse(int status,Map<String, List<String>> headers, JSONObject object) {
        this.headers = headers;
        this.status = status;
        this.object = object;
    }

    public int getStatus() {
        return status;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public JSONObject getObject() {
        return object;
    }
}
