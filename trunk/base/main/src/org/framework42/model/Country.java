package org.framework42.model;

public enum Country {

    DENMARK("DK", "DNK", 208),
    NORWAY("NO", "NOR", 578),
    SWEDEN("SE", "SWE", 752),
    FINLAND("FI", "FIN", 246);

    private final String alpha2Code;

    private final String alpha3Code;

    private final int numericCode;

    private Country(String alpha2Code, String alpha3Code, int numericCode) {

        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.numericCode = numericCode;

    }

    public String getCountryCode() {
        return alpha2Code;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public int getNumericCode() {
        return numericCode;
    }

    public static Country getById(int id) {

        for(Country country: Country.values()) {

            if(id == country.getNumericCode()) {
                return country;
            }

        }

        throw new IllegalArgumentException("No country with getId "+id+" exists!");

    }

    public static Country findFromCountryCode(String countryCode) {

        for(Country country: Country.values()) {

            if(country.getAlpha2Code().equalsIgnoreCase(countryCode)) {
                return country;
            }

        }

        throw new IllegalArgumentException("No country exists with country code " + countryCode);

    }

}
