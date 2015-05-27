package org.framework42.address.services.impl;

import org.framework42.address.model.Address;
import org.framework42.address.services.AddressFormatter;
import org.framework42.model.Country;

public class AddressFormatterImpl implements AddressFormatter {

    private final String lineSeparator;

    public AddressFormatterImpl() {

        lineSeparator = System.getProperty("line.separator");
    }

    @Override
    public String format(Address address) {

        if(address.getCountry() == Country.SWEDEN) {
            return formatForSweden(address);
        }

        throw new IllegalArgumentException("No implementation available for country "+address.getCountry().name());
    }

    @Override
    public String formatForWebb(Address address) {

        return null;
    }

    private String formatForSweden(Address address) {

        StringBuilder formattedAddress = new StringBuilder();

        formattedAddress.append(address.getAddressee());

        if(address.getCareOf().length()>0) {
            formattedAddress.append(lineSeparator);
            formattedAddress.append(address.getCareOf());
        }

        formattedAddress.append(lineSeparator);
        formattedAddress.append(address.getStreetAddress());

        formattedAddress.append(lineSeparator);
        formattedAddress.append(address.getPostalCode().toString());



        return formattedAddress.toString();

    }

}
