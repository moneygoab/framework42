package org.framework42.creditcheck.model;

import org.framework42.address.model.TrustedAddress;

public interface AddressCheckResponse {

    AddressStatus getStatus();

    String getFirstName();

    String getSurname();

    String getFullName();

    TrustedAddress getAddress();

}
