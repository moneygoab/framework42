package org.framework42.address.services.impl;

import org.framework42.address.services.PostalCodeViewFormatter;

public class PostalCodeViewFormatterSwedish implements PostalCodeViewFormatter {

    @Override
    public String format(String value) {

        return value.substring(0, 3)+" "+value.substring(3);
    }

}
