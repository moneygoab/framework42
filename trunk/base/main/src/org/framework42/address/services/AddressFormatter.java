package org.framework42.address.services;

import org.framework42.address.model.Address;

public interface AddressFormatter {

    public String format(Address address);

    public String formatForWebb(Address address);

}
