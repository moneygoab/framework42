package org.json;

public class RESTJSONResponse {

    private final int status;

    private final JSONObject object;

    public RESTJSONResponse(int status, JSONObject object) {
        this.status = status;
        this.object = object;
    }

    public int getStatus() {
        return status;
    }

    public JSONObject getObject() {
        return object;
    }
}
