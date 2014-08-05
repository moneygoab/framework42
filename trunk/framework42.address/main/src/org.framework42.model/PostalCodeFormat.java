package org.framework42.model;

import org.apache.log4j.Logger;
import org.framework42.services.PostalCodeViewFormatter;
import org.framework42.services.impl.PostalCodeStringValidatorImpl;
import org.framework42.services.PostalCodeStringValidator;
import org.framework42.services.impl.PostalCodeViewFormatterNoFormat;
import org.framework42.services.impl.PostalCodeViewFormatterSwedish;

import java.util.HashMap;
import java.util.Map;

public enum PostalCodeFormat {

    UNKNOWN(0, new PostalCodeStringValidatorImpl("" ,new String[]{" ", "-"})),
    NUMERIC_NNNNN(1, new PostalCodeStringValidatorImpl("\\d{5}" ,new String[]{" ", "-"})),
    NUMERIC_NNNN(2, new PostalCodeStringValidatorImpl("\\d{4}" ,new String[]{" ", "-"})),
    NUMERIC_NNN(4, new PostalCodeStringValidatorImpl("\\d{3}" ,new String[]{" ", "-"})),
    ALPHANUMERIC(3, new PostalCodeStringValidatorImpl("" ,new String[]{" "}));

    private final Logger logger = Logger.getLogger("org.framework42.addresses");

    private final int id;

    private final PostalCodeStringValidator stringValidator;

    private final Map<Country, PostalCodeViewFormatter> viewFormatter;

    PostalCodeFormat(int id, PostalCodeStringValidator stringValidator) {

        this.id = id;
        this.stringValidator = stringValidator;

        this.viewFormatter = new HashMap<Country, PostalCodeViewFormatter>();
        this.viewFormatter.put(Country.SWEDEN, new PostalCodeViewFormatterSwedish());
        this.viewFormatter.put(Country.ICELAND, new PostalCodeViewFormatterNoFormat());
        this.viewFormatter.put(Country.AUSTRIA, new PostalCodeViewFormatterNoFormat());
        this.viewFormatter.put(Country.UNITED_KINGDOMS, new PostalCodeViewFormatterNoFormat());
    }

    public int getId() {

        return id;
    }

    public PostalCodeStringValidator getStringValidator() {

        return stringValidator;
    }

    public String viewFormat(Country country, String value) {

        if(viewFormatter.containsKey(country)) {

            return viewFormatter.get(country).format(value);

        } else {

            logger.info("No view formatter for country " + country.name() + " exists!");

            return value;
        }
    }

    public static PostalCodeFormat getById(int id) {

        for(PostalCodeFormat format: PostalCodeFormat.values()) {

            if(format.getId() == id) {

                return format;
            }
        }
        throw new IllegalArgumentException("No postal code format with id "+id+" exists!");
    }

    public static PostalCodeFormat getByCountry(Country country) {

        if(country == Country.DENMARK) {
            return NUMERIC_NNNN;
        } else if(country == Country.FINLAND) {
            return NUMERIC_NNNNN;
        } else if(country == Country.NORWAY) {
            return NUMERIC_NNNN;
        } else if(country == Country.SWEDEN) {
            return NUMERIC_NNNNN;
        } else if(country == Country.ICELAND) {
            return NUMERIC_NNN;
        } else if(country == Country.AUSTRIA) {
            return ALPHANUMERIC;
        } else if(country == Country.UNITED_KINGDOMS) {
            return ALPHANUMERIC;
        }

        throw new IllegalArgumentException("No postal code format for the country "+country.name()+" exists!");
    }

}
