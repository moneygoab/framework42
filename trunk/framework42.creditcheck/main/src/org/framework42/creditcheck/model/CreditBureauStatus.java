package org.framework42.creditcheck.model;

public enum CreditBureauStatus {

    ACTIVE(1), INACTIVE(2);

    private final int id;

    CreditBureauStatus(int id) {

        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static CreditBureauStatus getById(int id) {

        for(CreditBureauStatus status: CreditBureauStatus.values()) {

            if(status.getId() == id) {

                return status;
            }
        }

        throw new IllegalArgumentException("No Credit Bureau Status with id "+id+" exists!");
    }

}
