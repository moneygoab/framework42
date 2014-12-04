package org.framework42.creditcheck.model;

public enum AddressStatus {

    OK(0), EMIGRATED(1), DECEASED(2), LOST_ID(3), OTHER(9), NO_CONNECTION(100);

    private final int id;

    AddressStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static AddressStatus getById(int id) {

        for(AddressStatus status: AddressStatus.values()) {

            if(status.getId()==id) {

                return status;
            }
        }

        throw new IllegalArgumentException("No address status with id "+id+" exists!");
    }

}
