package org.framework42.address.model;

import org.framework42.model.Country;

public interface PostalCode {

    public PostalCodeFormat getFormat();

    public String getFormattedValue(Country Country);

}
