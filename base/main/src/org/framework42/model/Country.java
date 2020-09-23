package org.framework42.model;

import java.util.Locale;

import static org.framework42.utils.NullChecker.notNull;

public enum Country {

    UNKNOWN("UNKNOWN", "UNKNOWN", "UNKNOWN", 0, ""),

    //NORDICS
    DENMARK("da", "DK", "DNK", 208, "DKK"),
    NORWAY("no", "NO", "NOR", 578, "NOK"),
    SWEDEN("sv", "SE", "SWE", 752, "SEK"),
    FINLAND("fi","FI", "FIN", 246, "EUR"),
    ICELAND("is","IS", "ISL", 354, "ISK"),

    //EUROPE
    ALBANIA("sq", "AL", "ALB", 8, "ALL"),
    ANDORRA("ca", "AD", "AND", 20, "EUR"),
    ARMENIA("hy", "AR", "ARM", 51, "AMD"),
    AUSTRIA("de", "AT", "AUT", 40, "EUR"),
    AZERBAIJAN("az", "AZ", "AZE", 31, "AZN"),
    BELARUS("be", "BY", "BLR", 112, "BYN"),
    BELGIUM("nl", "BE", "BEL", 56, "EUR"),
    BOSNIA_AND_HERZEGOVINA("bs", "BA", "BIH", 70, "BAM"),
    BULGARIA("bg", "BG", "BGR", 100, "BGN"),
    CROATIA("hr", "HR", "HRV", 191, "HRK"),
    CYPRUS("el", "CY", "CYP", 196, "EUR"),
    CZECH_REPUBLIC("cs", "CZ", "CZE", 203, "CZK"),
    ESTONIA("et", "EE", "EST", 233, "EUR"),
    FRANCE("fr", "FR", "FRA", 250,"EUR"),
    GEORGIA("ka", "GE", "GEO", 268, "GEL"),
    GERMANY("de", "DE", "DEU", 276, "EUR"),
    GREECE("el", "GR", "GRC", 300, "EUR"),
    HUNGARY("hu", "HU", "HUN", 348, "HUF"),
    IRELAND("en", "IE", "IRL", 372, "EUR"),
    ITALY("it", "IT", "ITA", 380, "EUR"),
    KAZAKHSTAN("ru", "KA", "KAZ", 398, "KZT"),
    LATVIA("lv", "LV", "LVA", 428, "EUR"),
    LIECHTENSTEIN("de", "LI", "LIE", 438, "CHF"),
    LITHUANIA("lt", "LT", "LTU", 440, "EUR"),
    LUXEMBOURG("lb", "LU", "LUX", 442, "EUR"),
    MACEDONIA("mk", "MK", "MKD", 807, "MKD"),
    MALTA("mt", "MT", "MLT", 470, "EUR"),
    MOLDOVA("ro", "MD", "MDA", 498, "MDL"),
    MONACO("fr", "MC", "MCO", 492, "EUR"),
    MONTENEGRO("sr", "ME", "MNE", 499, "EUR"),
    NETHERLANDS("nl", "NL", "NLD", 528, "EUR"),
    POLAND("pl", "PL", "POL", 48, "PLN"),
    PORTUGAL("pt", "PT", "PRT", 620, "EUR"),
    ROMANIA("ro", "RO", "ROU", 642, "RON"),
    RUSSIA("ru", "RU", "RUS", 643, "RUB"),
    SAN_MARINO("it", "SM", "SMR", 674, "EUR"),
    SERBIA("sr", "RS", "SRB", 891, "RSD"),
    SLOVAKIA("sk", "SK", "SVK", 703, "EUR"),
    SLOVENIA("sl", "SI", "SVN", 705, "EUR"),
    SPAIN("es", "ES", "ESP", 724, "EUR"),
    SWITZERLAND("de", "CH", "CHE", 756, "CHF"),
    TURKEY("tr", "TR", "TUR", 792, "TRY"),
    UKRAINE("uk", "UA", "UKR", 804, "UAH"),
    UNITED_KINGDOMS("en", "GB", "GBR", 826, "GBP"),
    VATICAN_CITY("it", "VA", "VAT", 336, "EUR"),

    //MIDDLE EAST
    ISRAEL("he", "IL", "ISR", 973, "ILS"),
    IRAQ("ar", "IQ", "IRQ", 368, "IQD"),
    SYRIA("ar", "SY", "SYR", 760, "SYP"),
    IRAN("fa", "IR", "IRN", 364, "IRR"),

    //AFRICA
    GHANA("en", "GH", "GHA", 288, "GHS"),

    //ASIA
    CHINA("zh", "CN", "CHN", 156, "CNY"),
    JAPAN("ja", "JP", "JPN", 392 , "JPY"),
    INDIA("en", "IN", "IND", 356, "INR"),
    PHILIPPINES("fil", "PH", "PHL", 608, "PHP"),

    //AMERICA
    BRAZIL("pt", "BR", "BRA", 76, "BRL"),
    CANADA("en", "CA", "CAN", 124 , "CAD"),
    MEXICO("es", "MX", "MEX", 484, "MXN"),
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

        throw new IllegalArgumentException("No country with getLevel "+id+" exists!");
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

        for(Country c: Country.values()) {

            if(c.getAlpha2Code().equalsIgnoreCase(country)) {

                return c;

            } else if(c.getAlpha3Code().equalsIgnoreCase(country)) {

                return c;
            }
        }

        if("Sverige".equalsIgnoreCase(country) ||
                "Svergie".equalsIgnoreCase(country) ||
                "Sve".equalsIgnoreCase(country) ||
                "Sweden".equalsIgnoreCase(country)
                ){

            return SWEDEN;

        } else if("Norge".equalsIgnoreCase(country) ||
                "Norway".equalsIgnoreCase(country)
                ){

            return NORWAY;

        } else if("Danmark".equalsIgnoreCase(country) ||
                "Denmark".equalsIgnoreCase(country)
                ){

            return DENMARK;

        } else if("Finland".equalsIgnoreCase(country) ||
                "Suomi".equalsIgnoreCase(country)
                ){

            return FINLAND;

        } else if("Island".equalsIgnoreCase(country) ||
                "Ísland".equalsIgnoreCase(country)
                ){

            return ICELAND;

        } else if("Estland".equalsIgnoreCase(country)) {

            return ESTONIA;

        } else if("Lettland".equalsIgnoreCase(country)) {

            return LATVIA;

        }else if("Litauen".equalsIgnoreCase(country)) {

            return LITHUANIA;

        } else if("Tyskland".equalsIgnoreCase(country)) {

            return GERMANY;

        } else if("Österrike".equalsIgnoreCase(country) ||
                "Österreich".equalsIgnoreCase(country)
                ) {

            return AUSTRIA;

        } else if("Polen".equalsIgnoreCase(country) ||
                "Polska".equalsIgnoreCase(country)
                ) {

            return POLAND;

        } else if("Portugal".equalsIgnoreCase(country) ||
                "Portuguesa".equalsIgnoreCase(country)
                ) {

            return PORTUGAL;

        } else if("Ryssland".equalsIgnoreCase(country) ||
                "Russia".equalsIgnoreCase(country)
                ) {

            return RUSSIA;

        } else if("Irak".equalsIgnoreCase(country) ||
                "Iraq".equalsIgnoreCase(country)
                ) {

            return IRAQ;

        } else if("Iran".equalsIgnoreCase(country) ||
                "Iran".equalsIgnoreCase(country)
                ) {

            return IRAN;

        } else if("Syrien".equalsIgnoreCase(country) ||
                "Syria".equalsIgnoreCase(country)
                ) {

            return SYRIA;

        } else if("Filippinerna".equalsIgnoreCase(country) ||
                "fil".equalsIgnoreCase(country)
                ) {

            return PHILIPPINES;

        } else if("Storbritannien".equalsIgnoreCase(country) ||
                "Great Brittan".equalsIgnoreCase(country)
                ) {

            return UNITED_KINGDOMS;

        } else if("Israel".equalsIgnoreCase(country) ||
                "he".equalsIgnoreCase(country)
                ) {

            return ISRAEL;

        } else if("USA".equalsIgnoreCase(country) ||
                "Amerika".equalsIgnoreCase(country)
                ) {

            return USA;

        } else if("Turkiet".equalsIgnoreCase(country)
                ) {

            return TURKEY;

        } else if("Ghana".equalsIgnoreCase(country)
                ) {

            return GHANA;

        } else if("Serbien".equalsIgnoreCase(country) ||
                "Serbia".equalsIgnoreCase(country)
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
