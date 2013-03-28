package org.framework42.model;

public enum APIResponseType {

    HELP(0), CSV(1), XML(2), JSON(3), HTML(4);

    private final int id;

    private APIResponseType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static APIResponseType getByName(String name) {

        for(APIResponseType type: APIResponseType.values()) {

            if(type.name().equalsIgnoreCase(name)) {

                return type;
            }
        }

        throw new IllegalArgumentException("No api response type with name "+name+" exists!");
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
