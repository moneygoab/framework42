package org.framework42.model;

public enum APIResponseType {

    NONE(0, ""), CSV(1, "text/csv"), XML(2, "application/xml"), JSON(3, "application/json"), HTML(4, "");

    private final int id;

    private final String mimeType;

    private APIResponseType(int id, String mimeType) {
        this.id = id;
        this.mimeType = mimeType;
    }

    public int getId() {
        return id;
    }

    public String getMimeType() {
        return mimeType;
    }

    public static APIResponseType getByName(String name, boolean forceDataType) {

        for(APIResponseType type: APIResponseType.values()) {

            if(type.getMimeType().equalsIgnoreCase(name)) {

                return type;
            }
        }

        if(forceDataType) {

            throw new IllegalArgumentException("No api response type with name " + name + " exists!");

        } else {

            return APIResponseType.NONE;
        }
    }

    public static APIResponseType getById(int id) {

        for(APIResponseType type: APIResponseType.values()) {

            if(id == type.getId()) {

                return type;
            }
        }

        throw new IllegalArgumentException("No api response type with id "+id+" exists!");
    }

}