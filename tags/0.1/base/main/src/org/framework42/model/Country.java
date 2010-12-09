package org.framework42.model;

public enum Country {

    DENMARK("DK"),
    NORWAY("NO"),
    SWEDEN("SE"),
    FINLAND("FI");

    private final String countryCode;

    private Country(String countryCode) {

        this.countryCode = countryCode;

    }

    public String getCountryCode() {
        return countryCode;
    }

    public static Country findFromCountryCode(String countryCode) {

        if (countryCode.equalsIgnoreCase("SE")) {
            return SWEDEN;
        } else if (countryCode.equalsIgnoreCase("DK")) {
            return DENMARK;
        } else if (countryCode.equalsIgnoreCase("FI")) {
            return FINLAND;
        } else if (countryCode.equalsIgnoreCase("NO")) {
            return NORWAY;
        }

        throw new IllegalArgumentException("No country exists with country code " + countryCode);

    }

}
