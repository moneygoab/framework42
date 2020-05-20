package org.json;

import org.apache.log4j.Logger;
import org.framework42.dao.JSONRetryQueueDataDAO;

import java.io.*;
import java.util.HashMap;

public enum RESTJSONAsyncCaller {

    INSTANCE;

    private final Logger logger = Logger.getLogger("org.json");

    public void makePostCallAndContinue(JSONRetryQueueDataDAO dao, String consumerKey, String targetURL) {

        makePostCallAndContinue(dao, "X-Consumer-Key", consumerKey, targetURL, "", "application/json");
    }

    public void makePostCallAndContinue(JSONRetryQueueDataDAO dao, String consumerKeyParameterName, String consumerKey, String targetURL, ByteArrayOutputStream stream) {

        new Thread(new PostCallRunnable(dao, consumerKeyParameterName, consumerKey, targetURL, "", stream, "application/json", new HashMap<>())).start();
    }

    public void makePostCallAndContinue(JSONRetryQueueDataDAO dao, String consumerKeyParameterName, String consumerKey, String targetURL, String postData, String contentType) {

        makePostCallAndContinue(dao, consumerKeyParameterName, consumerKey, targetURL, postData, contentType, new HashMap<>());
    }

    public void makePostCallAndContinue(JSONRetryQueueDataDAO dao, String consumerKeyParameterName, String consumerKey, String targetURL, String postData, String contentType, HashMap<String, String> headers) {

        new Thread(new PostCallRunnable(dao, consumerKeyParameterName, consumerKey, targetURL, postData, null, contentType, headers)).start();
    }


}
