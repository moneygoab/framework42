package org.framework42.model;

public enum APIRequestType {

    GET(1), POST(2), PUT(3), DELETE(4);

    private final int id;

    private APIRequestType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static APIRequestType getById(int id) {

        for(APIRequestType type: APIRequestType.values()) {

            if(id == type.getId()) {

                return type;
            }
        }

        throw new IllegalArgumentException("No api request type with id "+id+" exists!");
    }

}
