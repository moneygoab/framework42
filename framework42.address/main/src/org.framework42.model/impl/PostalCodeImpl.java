package org.framework42.model.impl;

import org.framework42.model.PostalCode;
import org.framework42.model.PostalCodeFormat;

import static org.framework42.utils.NullChecker.notNull;

public class PostalCodeImpl implements PostalCode {

    private final PostalCodeFormat format;

    private final String value;

    public PostalCodeImpl(PostalCodeFormat format, String value) {
        this.format = notNull(format, "Postal code format can't be null!");
        this.value = notNull(value, "value can't be null!");
    }

    @Override
    public PostalCodeFormat getFormat() {
        return format;
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
