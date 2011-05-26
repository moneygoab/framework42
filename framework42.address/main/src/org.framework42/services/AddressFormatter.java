package org.framework42.services;

import org.framework42.model.Address;
import org.framework42.model.Country;

public interface AddressFormatter {

    public String format(Address address);

    public String formatForWebb(Address address);

}
