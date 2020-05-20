package org.framework42.dao;

import org.json.JSONRetryQueueData;
import org.json.JSONRetryStatus;

import java.util.List;

public interface JSONRetryQueueDataDAO {

    JSONRetryQueueData add(JSONRetryQueueData data);

    void updateStatus(int id, JSONRetryStatus newStatus);

    List<JSONRetryQueueData> findAllInStatus(JSONRetryStatus status);

}
