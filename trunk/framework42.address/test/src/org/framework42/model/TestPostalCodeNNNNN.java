package org.framework42.model;

import org.framework42.address.model.PostalCode;
import org.framework42.address.model.PostalCodeFormat;
import org.framework42.address.model.impl.PostalCodeImpl;
import org.junit.Test;

public class TestPostalCodeNNNNN {

    @Test
    public void testCreatePostalCode() {

        new PostalCodeImpl(
                PostalCodeFormat.getByCountry(Country.SWEDEN),
                "12345"
        );

        new PostalCodeImpl(
                PostalCodeFormat.getByCountry(Country.SWEDEN),
                "123 45"
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreatePostalCodeInvalidCharacters() {

        new PostalCodeImpl(
                PostalCodeFormat.getByCountry(Country.SWEDEN),
                "abcde"
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreatePostalCodeInvalidLength() {

        new PostalCodeImpl(
                PostalCodeFormat.getByCountry(Country.SWEDEN),
                "1234"
        );
    }

    @Test
    public void testPrintPostalCode() {

        PostalCode postalCode = new PostalCodeImpl(
                PostalCodeFormat.getByCountry(Country.SWEDEN),
                "12345"
        );

        String value = postalCode.getFormattedValue(Country.SWEDEN);

        if(!value.equalsIgnoreCase("123 45")) {

            throw new IllegalArgumentException("Value format failed! Returned value: "+value);
        }

    }

}
