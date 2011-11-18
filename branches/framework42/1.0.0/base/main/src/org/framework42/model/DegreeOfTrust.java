package org.framework42.model;

public enum DegreeOfTrust {

    NO_TRUST(1), LOW_TRUST(2), HIGH_TRUST(3);

    private final int id;

    DegreeOfTrust(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static DegreeOfTrust getById(int id) {

        for(DegreeOfTrust trust: DegreeOfTrust.values()) {

            if(trust.getId() == id) {

                return trust;
            }
        }

        throw new IllegalArgumentException("No degree of trust with id "+id+" exists!");
    }
    
}
