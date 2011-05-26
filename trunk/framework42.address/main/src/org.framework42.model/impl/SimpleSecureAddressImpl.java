package org.framework42.model.impl;

import org.framework42.model.*;

import java.util.Date;

import static org.framework42.utils.NullChecker.notNull;

public class SimpleSecureAddressImpl extends SimpleAddressImpl implements TrustedAddress {

    private final InformationProvider informationProvider;

    public SimpleSecureAddressImpl(String addressee, String careOf, String streetAddress, PostalCode postalCode, String city, Country country, InformationProvider informationProvider) {
        super(addressee, careOf, streetAddress, postalCode, city, country);

        this.informationProvider = notNull(informationProvider, "Information provider can't be null!");
    }

    public SimpleSecureAddressImpl(String addressee, String careOf, String streetAddress, PostalCode postalCode, String city, Country country, AddressType type, Date validFrom, Date validTo, InformationProvider informationProvider) {
        super(addressee, careOf, streetAddress, postalCode, city, country, type, validFrom, validTo);

        this.informationProvider = notNull(informationProvider, "Information provider can't be null!");
    }

    @Override
    public InformationProvider getInformationProvider() {
        return informationProvider;
    }
}
