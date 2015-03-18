package org.json;

public class RESTJSONArrayResponse {

    private final int status;

    private final JSONArray array;

    public RESTJSONArrayResponse(int status, JSONArray array) {
        this.status = status;
        this.array = array;
    }

    public int getStatus() {
        return status;
    }

    public JSONArray getArray() {
        return array;
    }

}
