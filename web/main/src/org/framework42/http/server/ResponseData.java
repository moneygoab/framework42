package org.framework42.http.server;

import org.framework42.http.StatusCode;

public class ResponseData {

    private StatusCode responseCode;

    public ResponseData() {

        this.responseCode = StatusCode.OK_200;
    }

    public ResponseData(StatusCode responseCode) {

        this.responseCode = responseCode;
    }

    public StatusCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(StatusCode responseCode) {
        this.responseCode = responseCode;
    }
}
