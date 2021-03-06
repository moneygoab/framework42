package org.framework42.model.impl;

import org.framework42.model.*;
import org.framework42.model.AddressType;
import org.framework42.model.InformationProvider;
import org.framework42.model.PostalCode;
import org.framework42.model.PostalCodeFormat;
import org.framework42.model.TrustedAddress;

import java.util.Date;

import static org.framework42.utils.NullChecker.notNull;

public class SimpleSecureAddressImpl extends org.framework42.model.impl.SimpleAddressImpl implements TrustedAddress {

    private final InformationProvider informationProvider;

    public SimpleSecureAddressImpl() {
        super(0, "", "", "", new org.framework42.model.impl.PostalCodeImpl(PostalCodeFormat.UNKNOWN, ""), "", Country.UNKNOWN);

        this.informationProvider = InformationProvider.NO_PROVIDER;
    }

    public SimpleSecureAddressImpl(int id, String addressee, String careOf, String streetAddress, PostalCode postalCode, String city, Country country, InformationProvider informationProvider) {
        super(id, addressee, careOf, streetAddress, postalCode, city, country);

        this.informationProvider = notNull(informationProvider, "Information provider can't be null!");
    }

    public SimpleSecureAddressImpl(int id, String addressee, String careOf, String streetAddress, PostalCode postalCode, String city, Country country, AddressType type, Date validFrom, Date validTo, InformationProvider informationProvider) {
        super(id, addressee, careOf, streetAddress, postalCode, city, country, type, validFrom, validTo);

        this.informationProvider = notNull(informationProvider, "Information provider can't be null!");
    }

    @Override
    public InformationProvider getProvider() {
        return informationProvider;
    }

    @Override
    public String toString() {

        return "SimpleSecureAddressImpl{" +
               "informationProvider=" + informationProvider +
               "} " + super.toString();
    }
}
