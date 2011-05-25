package org.framework42.model;

public enum PostalCodeFormat {

    UNKNOWN(0), NUMERIC(1), ALPHANUMERIC(2);

    private final int id;

    PostalCodeFormat(int id) {

        this.id = id;
    }

    public int getId() {

        return id;
    }

    public PostalCodeFormat getById(int id) {

        for(PostalCodeFormat format: PostalCodeFormat.values()) {

            if(format.getId() == id) {

                return format;
            }
        }
        throw new IllegalArgumentException("No postal code format with id "+id+" exists!");
    }

    public PostalCodeFormat getByCountry(Country country) {

        if(country == Country.DENMARK) {
            return NUMERIC;
        } else if(country == Country.FINLAND) {
            return NUMERIC;
        } else if(country == Country.NORWAY) {
            return NUMERIC;
        } else if(country == Country.SWEDEN) {
            return NUMERIC;
        }

        throw new IllegalArgumentException("No postal code format for the country "+country.name()+" exists!");
    }

}
