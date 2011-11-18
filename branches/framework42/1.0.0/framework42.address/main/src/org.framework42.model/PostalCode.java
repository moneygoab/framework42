package org.framework42.model;

public interface PostalCode {

    public PostalCodeFormat getFormat();

    public String getFormattedValue(Country country);

}
