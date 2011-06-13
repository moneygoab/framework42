package org.framework42.creditcheck.model;

public enum IdentificationType {

    ELECTRONIC_ID(1), PAPER_COPY_OF_LEGITIMATION(2), UNIDENTIFIED(3);

    private final int id;

    IdentificationType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public IdentificationType getById(int id) {

        for(IdentificationType idType : IdentificationType.values()) {

            if(id == idType.getId()) {

                return idType;

            }

        }

        throw new IllegalArgumentException("No identification type with getId "+id+" exists!");
        
    }

}
