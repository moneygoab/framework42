package org.framework42.model;

import java.util.Date;

public interface Address {

    public int getId();

    public String getAddressee();

    public String getCareOf();

    public String getStreetAddress();

    public org.framework42.model.PostalCode getPostalCode();

    public String getCity();

    public Country getCountry();

    public org.framework42.model.AddressType getType();

    public Date getValidFrom();

    public Date getValidTo();

}
