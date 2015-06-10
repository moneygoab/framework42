package org.framework42.creditcheck.model;

public enum MaritalStatus {

    UNKNOWN(0), SINGLE(1), COHABITATION(2), MARRIED(3), DIVORCED(4), WIDOW_WIDOWER(5);

    private final int id;

    MaritalStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static MaritalStatus getById(int id) {

        for(MaritalStatus maritalStatus: MaritalStatus.values()) {

            if(maritalStatus.getId() == id) {

                return maritalStatus;
            }
        }

        throw new IllegalArgumentException("No marital status with id "+id+" exists!");
    }

}
