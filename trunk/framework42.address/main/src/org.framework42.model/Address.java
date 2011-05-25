package org.framework42.model;

import java.util.Date;

public interface Address {

    public String getName();

    public String getCareOf();

    public String getStreetAddress();

    public PostalCode getPostalCode();

    public String getCity();

    public Country getCountry();

    public AddressType getType();

    public Date getValidFrom();

    public Date getValidTo();

}
