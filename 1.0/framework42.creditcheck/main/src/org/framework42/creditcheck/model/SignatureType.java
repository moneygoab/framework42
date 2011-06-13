package org.framework42.creditcheck.model;

public enum SignatureType {

    ELECTRONIC_SIGNATURE(1), PAPER_SIGNATURE(2), UNSIGNED(3);

    private final int id;

    SignatureType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public SignatureType getById(int id) {

        for(SignatureType signatureType : SignatureType.values()) {

            if(id == signatureType.getId()) {

                return signatureType;

            }

        }

        throw new IllegalArgumentException("No signature type with getId "+id+" exists!");
        
    }

}
