package org.framework42.model;

public enum CreditBureau {

    UC(1, Country.SWEDEN);

    private final int id;

    private final Country country;

    CreditBureau(int id, Country country) {

        this.id = id;
        this.country = country;

    }

    public int getId() {
        return id;
    }

    public Country getCountry() {
        return country;
    }

    public static CreditBureau getById(int id) {

        for(CreditBureau creditBureau: CreditBureau.values()) {

            if(creditBureau.getId() == id) {

                return creditBureau;
            }
        }

        throw new IllegalArgumentException("No credit bureau with id "+id+" exists!");
    }

}
