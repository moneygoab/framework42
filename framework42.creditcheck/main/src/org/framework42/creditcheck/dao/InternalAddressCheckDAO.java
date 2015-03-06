package org.framework42.creditcheck.dao;

import org.framework42.creditcheck.model.AddressCheckResponse;
import org.framework42.model.TrustedAddress;

public interface InternalAddressCheckDAO {

    public AddressCheckResponse getAddress(String governmentId);

}
