package org.framework42.model;

public enum InformationProvider {

    NO_PROVIDER(0, DegreeOfTrust.NO_TRUST),
    CUSTOMER(2, DegreeOfTrust.NO_TRUST), POPULATION_REGISTERS(1, DegreeOfTrust.HIGH_TRUST);

    private final int id;

    private final DegreeOfTrust trustLevel;

    InformationProvider(int id, DegreeOfTrust trustLevel) {
        this.id = id;
        this.trustLevel = trustLevel;
    }

    public int getId() {
        return id;
    }

    public DegreeOfTrust getTrustLevel() {
        return trustLevel;
    }

    public static InformationProvider getById(int id) {

        for(InformationProvider provider: InformationProvider.values()) {

            if(id == provider.getId()) {

                return provider;

            }

        }

        throw new IllegalArgumentException("No information provider with getLevel "+id+" exists!");

    }
    
}
