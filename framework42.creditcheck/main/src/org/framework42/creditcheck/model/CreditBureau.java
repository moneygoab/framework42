package org.framework42.creditcheck.model;

import org.framework42.model.Country;

import java.util.ArrayList;
import java.util.List;

public enum CreditBureau {

    UC(1, Country.SWEDEN), EMULATOR_SWEDEN(2, Country.SWEDEN), INTERNAL(3, Country.SWEDEN), UC_MICRO(4, Country.SWEDEN), UC_TEST(5, Country.SWEDEN);


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

    public static List<CreditBureau> getAllInCountry(Country country) {

        List<CreditBureau> found = new ArrayList<CreditBureau>();

        for(CreditBureau creditBureau: CreditBureau.values()) {

            if(creditBureau.getCountry() == country) {

                found.add(creditBureau);
            }
        }

        return found;
    }

}
