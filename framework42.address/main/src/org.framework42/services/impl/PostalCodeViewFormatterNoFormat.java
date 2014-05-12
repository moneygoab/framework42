package org.framework42.services.impl;

import org.framework42.services.PostalCodeViewFormatter;

public class PostalCodeViewFormatterNoFormat implements PostalCodeViewFormatter {

        @Override
        public String format(String value) {

            return value;
        }

}
