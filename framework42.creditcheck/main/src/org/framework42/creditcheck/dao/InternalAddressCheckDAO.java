package org.framework42.creditcheck.dao;

import org.framework42.model.TrustedAddress;

public interface InternalAddressCheckDAO {

    public TrustedAddress getAddress(String governmentId);

}
