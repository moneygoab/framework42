package org.framework42.model;

import java.util.Locale;

import static org.framework42.utils.NullChecker.notNull;

public enum Country {

    DENMARK("da", "DK", "DNK", 208, "DKK"),
    NORWAY("no", "NO", "NOR", 578, "NOK"),
    SWEDEN("sv", "SE", "SWE", 752, "SEK"),
    FINLAND("fi","FI", "FIN", 246, "EUR"),
    ICELAND("is","IS", "ISL", 354, "ISK")
    ;

    private final String languageCode;

    private final String alpha2Code;

    private final String alpha3Code;

    private final int numericCode;

    private final String currencyCode;

    private Country(String languageCode, String alpha2Code, String alpha3Code, int numericCode, String currencyCode) {

        this.languageCode = languageCode;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.numericCode = numericCode;
        this.currencyCode = currencyCode;
    }

    public String getLanguageCode() {
        return languageCode;
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

        } else if("Norge".equalsIgnoreCase(country) ||
                "NOR".equalsIgnoreCase(country) ||
                "NO".equalsIgnoreCase(country) ||
                "Norway".equalsIgnoreCase(country)
                ){

            return NORWAY;

        } else if("Danmark".equalsIgnoreCase(country) ||
                "DNK".equalsIgnoreCase(country) ||
                "DK".equalsIgnoreCase(country) ||
                "Denmark".equalsIgnoreCase(country)
                ){

            return DENMARK;

        } else if("Finland".equalsIgnoreCase(country) ||
                "FIN".equalsIgnoreCase(country) ||
                "FI".equalsIgnoreCase(country) ||
                "Suomi".equalsIgnoreCase(country)
                ){

            return FINLAND;

        }

        throw new IllegalArgumentException("Could not parse a country from the string " + country);
    }

    public Locale getDefaultLocale() {

        return new Locale(languageCode, alpha2Code);
    }

}
