package org.framework42.creditcheck.model;

public enum ApplicationType {

    NONE(0), NEW(1), EXTENDING_OLD(2);

    private final int id;

    ApplicationType(int id) {

        this.id = id;
    }

    public int getId() {

        return id;
    }

    public static ApplicationType getById(int id) {

        for(ApplicationType type: ApplicationType.values()) {

            if(id == type.getId()) {

                return type;
            }
        }

        throw new IllegalArgumentException("No application type with id "+id+" exists!");
    }

}
