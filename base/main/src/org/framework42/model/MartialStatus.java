package org.framework42.model;

public enum MartialStatus {

    SINGLE(1), MARRIED(2), DIVORCED(3), WIDOWED(4),COHABITATION(5), UNKNOWN(0);

    private final int id;

    MartialStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static MartialStatus getById(int id) {

        for(MartialStatus martialStatus: MartialStatus.values()) {

            if(martialStatus.getId() == id) {

                return martialStatus;
            }
        }

        throw new IllegalArgumentException("No martial status with id "+id+" exists!");
    }

}
