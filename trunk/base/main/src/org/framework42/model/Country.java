package org.framework42.model;

import java.util.Locale;

import static org.framework42.utils.NullChecker.notNull;

public enum Country {

    UNKNOWN("UNKNOWN", "UNKNOWN", "UNKNOWN", 0, ""),

    DENMARK("da", "DK", "DNK", 208, "DKK"),
    NORWAY("no", "NO", "NOR", 578, "NOK"),
    SWEDEN("sv", "SE", "SWE", 752, "SEK"),
    FINLAND("fi","FI", "FIN", 246, "EUR"),
    ICELAND("is","IS", "ISL", 354, "ISK"),

    AUSTRIA("de", "AT", "AUT", 40, "EUR"),
    POLAND("pl", "PL", "POL", 48, "PLN"),
    SERBIA("sr", "RS", "SRB", 891, "RSD"),

    ISRAEL("he", "IL", "ISR", 973, "ILS"),

    GHANA("en", "GH", "GHA", 288, "GHS"),

    PHILIPPINES("fil", "PH", "PHL", 608, "PHP"),
    TURKEY("tr", "TR", "TUR", 792, "TRY"),

    UNITED_KINGDOMS("uk", "GB", "GBR", 826, "GBP"),

    USA("us","US", "USA", 840, "USD"),
    ;

    private final String languageCode;

    //ISO 3166-1 alpha-2
    private final String alpha2Code;

    //ISO 3166-1 alpha-3
    private final String alpha3Code;

    //ISO 3166 numeric
    private final int numericCode;

    //ISO 4217 currency code
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

        } else if("Island".equalsIgnoreCase(country) ||
                "ISL".equalsIgnoreCase(country) ||
                "IS".equalsIgnoreCase(country) ||
                "Ísland".equalsIgnoreCase(country)
                ){

            return ICELAND;

        } else if("Österrike".equalsIgnoreCase(country) ||
                "AT".equalsIgnoreCase(country) ||
                "AUT".equalsIgnoreCase(country) ||
                "Österreich".equalsIgnoreCase(country)
                ) {

            return AUSTRIA;

        } else if("Polen".equalsIgnoreCase(country) ||
                "PL".equalsIgnoreCase(country) ||
                "POL".equalsIgnoreCase(country) ||
                "Polska".equalsIgnoreCase(country)
                ) {

            return POLAND;

        } else if("Filippinerna".equalsIgnoreCase(country) ||
                "PH".equalsIgnoreCase(country) ||
                "PHL".equalsIgnoreCase(country) ||
                "fil".equalsIgnoreCase(country)
                ) {

            return PHILIPPINES;

        } else if("Storbritannien".equalsIgnoreCase(country) ||
                "GBR".equalsIgnoreCase(country) ||
                "GB".equalsIgnoreCase(country)) {

            return UNITED_KINGDOMS;

        } else if("Israel".equalsIgnoreCase(country) ||
                "IL".equalsIgnoreCase(country) ||
                "ISR".equalsIgnoreCase(country) ||
                "he".equalsIgnoreCase(country)
                ) {

            return ISRAEL;

        } else if("USA".equalsIgnoreCase(country) ||
                "US".equalsIgnoreCase(country) ||
                "USA".equalsIgnoreCase(country)
                ) {

            return USA;

        } else if("Turkiet".equalsIgnoreCase(country) ||
                "TR".equalsIgnoreCase(country) ||
                "TUR".equalsIgnoreCase(country)
                ) {

            return TURKEY;

        } else if("Ghana".equalsIgnoreCase(country) ||
                "GH".equalsIgnoreCase(country) ||
                "GHA".equalsIgnoreCase(country)
                ) {

            return GHANA;

        } else if("Serbien".equalsIgnoreCase(country) ||
                "Serbia".equalsIgnoreCase(country) ||
                "RS".equalsIgnoreCase(country) ||
                "SRB".equalsIgnoreCase(country)
                ) {

            return GHANA;

        } else if("Okänt Land".equalsIgnoreCase(country) ||
                "UNKNOWN".equalsIgnoreCase(country)) {

            return UNKNOWN;
        }

        throw new IllegalArgumentException("Could not parse a country from the string " + country);
    }

    public Locale getDefaultLocale() {

        return new Locale(languageCode, alpha2Code);
    }

}
