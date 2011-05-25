package org.framework42.model.impl;

import org.framework42.model.Address;
import org.framework42.model.AddressType;
import org.framework42.model.Country;
import org.framework42.model.PostalCode;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.framework42.utils.NullChecker.notNull;

public class SimpleAddressImpl implements Address {

    protected final String name;

    protected final String careOf;

    protected final String streetAddress;

    protected final PostalCode postalCode;

    protected final String city;

    protected final Country country;

    protected final AddressType type;

    protected final long validFrom;

    protected final long validTo;

    public SimpleAddressImpl(String name, String careOf, String streetAddress, PostalCode postalCode, String city, Country country) {

        this.name = notNull(name, "Name can't be null!");
        this.careOf = notNull(careOf, "Care of can't be null!");
        this.streetAddress = notNull(streetAddress, "Street address can't be null!");
        this.postalCode = notNull(postalCode, "Postal code can't be null!");
        this.city = notNull(city, "City can't be null!");
        this.country = notNull(country, "Country can't be null!");
        this.type = AddressType.UNKNOWN;
        this.validFrom = new GregorianCalendar(2000, Calendar.JANUARY, 1).getTime().getTime();
        this.validTo = new GregorianCalendar(9999, Calendar.JANUARY, 1).getTime().getTime();
    }

    public SimpleAddressImpl(String name, String careOf, String streetAddress, PostalCode postalCode, String city,
                             Country country, AddressType type, Date validFrom, Date validTo) {

        this.name = notNull(name, "Name can't be null!");
        this.careOf = notNull(careOf, "Care of can't be null!");
        this.streetAddress = notNull(streetAddress, "Street address can't be null!");
        this.postalCode = notNull(postalCode, "Postal code can't be null!");
        this.city = notNull(city, "City can't be null!");
        this.country = notNull(country, "Country can't be null!");
        this.type = notNull(type, "Type can't be null!");
        this.validFrom = validFrom.getTime();
        this.validTo = validTo.getTime();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCareOf() {
        return careOf;
    }

    @Override
    public String getStreetAddress() {
        return streetAddress;
    }

    @Override
    public PostalCode getPostalCode() {
        return postalCode;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public Country getCountry() {
        return country;
    }

    @Override
    public AddressType getType() {
        return type;
    }

    @Override
    public Date getValidFrom() {
        return new Date(validFrom);
    }

    @Override
    public Date getValidTo() {
        return new Date(validTo);
    }
}
