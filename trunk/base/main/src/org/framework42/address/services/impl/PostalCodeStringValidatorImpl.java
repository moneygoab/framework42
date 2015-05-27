package org.framework42.address.services.impl;

import org.framework42.address.services.PostalCodeStringValidator;

public class PostalCodeStringValidatorImpl implements PostalCodeStringValidator {

    private final String validationScheme;

    private final String[] washCharList;

    public PostalCodeStringValidatorImpl(String validationScheme, String[] washCharList) {

        this.validationScheme = validationScheme;
        this.washCharList = washCharList;
    }

    @Override
    public String getValidationScheme() {

        return validationScheme;
    }

    @Override
    public boolean isValid(String value) {

        if(validationScheme == null || validationScheme.length() == 0) {
            return true;
        }

        return value.matches(validationScheme);
    }

    @Override
    public String wash(String value) {

        for(String washString: washCharList) {

            value = value.replaceAll(washString, "");
        }

        return value;
    }

}
