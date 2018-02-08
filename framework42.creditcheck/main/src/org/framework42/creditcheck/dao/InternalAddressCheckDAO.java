package org.framework42.creditcheck.dao;

import org.framework42.creditcheck.model.AddressCheckResponse;

public interface InternalAddressCheckDAO {

    AddressCheckResponse getAddress(String governmentId);

}
