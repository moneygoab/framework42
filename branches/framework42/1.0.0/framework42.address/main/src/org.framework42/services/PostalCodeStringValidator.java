package org.framework42.services;

public interface PostalCodeStringValidator {

    public String getValidationScheme();

    public boolean isValid(String value);

    public String wash(String value);

}
