package org.framework42.creditcheck.model;

public enum HousingType {

    HOUSE(1), CONDOMINIUM(2), RENTAL(3), SUBTENANT(4);

    private final int id;

    HousingType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static HousingType getById(int id) {

        for(HousingType housingType: HousingType.values()) {

            if(housingType.getId() == id) {

                return housingType;
            }
        }

        throw new IllegalArgumentException("No housing typ with id "+id+" exists!");
    }

}
