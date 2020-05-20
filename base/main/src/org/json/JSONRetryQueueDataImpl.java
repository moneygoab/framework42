package org.json;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.framework42.utils.GreaterThenZeroChecker.greaterThenZero;
import static org.framework42.utils.NullChecker.notNull;

public class JSONRetryQueueDataImpl implements JSONRetryQueueData {

    private final int id;

    private final JSONRetryStatus status;

    private final LocalDateTime addedDate;

    private final LocalDateTime lastRetryDate;

    private final String targetURL;

    private final String contentType;

    private final Map<String,String> headers;

    private final String postData;

    public JSONRetryQueueDataImpl(String targetURL, String contentType, Map<String, String> headers, String postData) {
        this.id = 0;
        this.status = JSONRetryStatus.PENDING;
        this.addedDate = LocalDateTime.now();
        this.lastRetryDate = LocalDateTime.now();
        this.targetURL = notNull(targetURL);
        this.contentType = notNull(contentType);
        this.headers = notNull(headers);
        this.postData = notNull(postData);
    }

    public JSONRetryQueueDataImpl(JSONRetryStatus status, LocalDateTime addedDate, LocalDateTime lastRetryDate, String targetURL, String contentType, Map<String, String> headers, String postData) {
        this.id = 0;
        this.status = notNull(status);
        this.addedDate = notNull(addedDate);
        this.lastRetryDate = notNull(lastRetryDate);
        this.targetURL = notNull(targetURL);
        this.contentType = notNull(contentType);
        this.headers = notNull(headers);
        this.postData = notNull(postData);
    }

    public JSONRetryQueueDataImpl(int id, JSONRetryStatus status, LocalDateTime addedDate, LocalDateTime lastRetryDate, String targetURL, String contentType, Map<String, String> headers, String postData) {
        this.id = greaterThenZero(id);
        this.status = notNull(status);
        this.addedDate = notNull(addedDate);
        this.lastRetryDate = notNull(lastRetryDate);
        this.targetURL = notNull(targetURL);
        this.contentType = notNull(contentType);
        this.headers = notNull(headers);
        this.postData = notNull(postData);
    }

    public JSONRetryQueueDataImpl(int id, JSONRetryStatus status, LocalDateTime addedDate, LocalDateTime lastRetryDate, String targetURL, String contentType, String headers, String postData) {

        JSONArray array = new JSONArray(headers);

        Map<String,String> headerMap = new HashMap<>();

        for(int i=0; i<array.length(); i++) {

            JSONObject obj = array.getJSONObject(i);

            headerMap.put(obj.getString("header_name"), obj.getString("header_value"));
        }

        this.id = greaterThenZero(id);
        this.status = notNull(status);
        this.addedDate = notNull(addedDate);
        this.lastRetryDate = notNull(lastRetryDate);
        this.targetURL = notNull(targetURL);
        this.contentType = notNull(contentType);
        this.headers = notNull(headerMap);
        this.postData = notNull(postData);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public JSONRetryStatus getStatus() {
        return status;
    }

    @Override
    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    @Override
    public LocalDateTime getLastRetryDate() {
        return lastRetryDate;
    }

    @Override
    public String getTargetURL() {
        return targetURL;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public JSONArray getHeadersAsJson() {

        JSONArray array = new JSONArray();

        for(String set: headers.keySet()) {

            JSONObject obj = new JSONObject();

            obj.put("header_name", set);
            obj.put("header_value", headers.get(set));

            array.put(obj);
        }

        return array;
    }

    @Override
    public String getPostData() {
        return postData;
    }
}
