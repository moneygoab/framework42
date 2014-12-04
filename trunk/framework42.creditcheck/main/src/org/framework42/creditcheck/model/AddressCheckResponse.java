package org.framework42.creditcheck.model;

import org.framework42.model.TrustedAddress;

public interface AddressCheckResponse {

    public AddressStatus getStatus();

    public TrustedAddress getAddress();

}
