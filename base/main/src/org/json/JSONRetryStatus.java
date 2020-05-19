package org.json;

public enum JSONRetryStatus {

    PENDING(1), COMPLETED(10), CANCELED(99);

    private final int id;

    JSONRetryStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static JSONRetryStatus getById(int id) {

        for(JSONRetryStatus status: JSONRetryStatus.values()) {

            if(status.getId()==id) {

                return status;
            }
        }

        throw new IllegalArgumentException("No JSON retry status exists with id "+id);
    }

}
