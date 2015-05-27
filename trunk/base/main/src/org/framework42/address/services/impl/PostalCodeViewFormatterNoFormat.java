package org.framework42.address.services.impl;

import org.framework42.address.services.PostalCodeViewFormatter;

public class PostalCodeViewFormatterNoFormat implements PostalCodeViewFormatter {

    @Override
    public String format(String value) {

        return value;
    }
}
