package org.framework42.http.server;

import org.framework42.http.StatusCode;

public interface ResponseData {

    public StatusCode getResponseCode();

    public void setResponseCode(StatusCode responseCode);

}
