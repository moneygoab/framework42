package org.framework42.address.model.impl;

import org.apache.log4j.Logger;
import org.framework42.model.Country;
import org.framework42.address.model.PostalCode;
import org.framework42.address.model.PostalCodeFormat;

import static org.framework42.utils.NullChecker.notNull;

public class PostalCodeImpl implements PostalCode {

    private final Logger logger = Logger.getLogger("org.framework42.address");

    private final PostalCodeFormat format;

    private final String value;

    public PostalCodeImpl(PostalCodeFormat format, String value) {

        this.format = notNull(format, "Postal code format can't be null!");
        this.value = washValue(value);
    }

    private String washValue(String value) {

        value = format.getStringValidator().wash(notNull(value, "value can't be null!"));

        if(format.getStringValidator().isValid(value)) {

            return value;

        } else {

            String errorMess = "The postal code with value "+value+" isn't matching the pattern "+format.getStringValidator().getValidationScheme();
            logger.error(errorMess);
            throw new IllegalArgumentException(errorMess);
        }
    }

    @Override
    public PostalCodeFormat getFormat() {
        return format;
    }

    @Override
    public String getFormattedValue(Country country) {
        return format.viewFormat(country, value);
    }

    @Override
    public String toString() {

        return value;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PostalCodeImpl that = (PostalCodeImpl) o;

        if (format != that.format) {
            return false;
        }
        if (!value.equals(that.value)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

        int result = format.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}
