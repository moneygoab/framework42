package org.framework42.model;

import com.sun.xml.internal.messaging.saaj.util.FinalArrayList;

import static org.framework42.utils.NullChecker.notNull;

public enum Country {

    DENMARK("DK", "DNK", 208, "DKK"),
    NORWAY("NO", "NOR", 578, "NOK"),
    SWEDEN("SE", "SWE", 752, "SEK"),
    FINLAND("FI", "FIN", 246, "EUR");

    private final String alpha2Code;

    private final String alpha3Code;

    private final int numericCode;

    private final String currencyCode;

    private Country(String alpha2Code, String alpha3Code, int numericCode, String currencyCode) {

        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.numericCode = numericCode;
        this.currencyCode = currencyCode;
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

    public String getCurrencyCode() {
        return currencyCode;
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

        notNull(countryCode, "Country code can't be null!");

        for(Country country: Country.values()) {

            if(country.getAlpha2Code().equalsIgnoreCase(countryCode)) {
                return country;
            }

        }

        throw new IllegalArgumentException("No country exists with country code " + countryCode);
    }

    public static Country parseFromString(String country){

        notNull(country, "Country can't be null!");

        if("Sverige".equalsIgnoreCase(country) ||
                "Svergie".equalsIgnoreCase(country) ||
                "Sve".equalsIgnoreCase(country) ||
                "SWE".equalsIgnoreCase(country) ||
                "SE".equalsIgnoreCase(country) ||
                "Sweden".equalsIgnoreCase(country)
                ){

            return SWEDEN;

        }

        throw new IllegalArgumentException("Could not parse a country from the string " + country);
    }

}
