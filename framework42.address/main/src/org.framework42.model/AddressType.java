package org.framework42.model;

public enum AddressType {

    TEMPORARY(10), PERMANENT(50), UNKNOWN(0);

    private final int id;

    AddressType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static AddressType getById(int id) {

        for(AddressType type: AddressType.values()) {

            if(id == type.getId()) {

                return type;
            }
        }

        throw new IllegalArgumentException("No address type with id "+id+" exists!");
    }

}
