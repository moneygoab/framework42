package org.framework42.creditcheck.model;

import org.framework42.address.model.TrustedAddress;

public interface AddressCheckResponse {

    public AddressStatus getStatus();

    public String getFirstName();

    public String getSurname();

    public String getFullName();

    public TrustedAddress getAddress();

}
