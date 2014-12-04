package org.framework42.creditcheck.model.impl;

import org.framework42.creditcheck.model.AddressCheckResponse;
import org.framework42.creditcheck.model.AddressStatus;
import org.framework42.model.TrustedAddress;

public class AddressCheckResponseImpl implements AddressCheckResponse {

    private final AddressStatus status;

    private final TrustedAddress address;

    public AddressCheckResponseImpl(AddressStatus status, TrustedAddress address) {
        this.status = status;
        this.address = address;
    }

    @Override
    public AddressStatus getStatus() {
        return status;
    }

    @Override
    public TrustedAddress getAddress() {
        return address;
    }
}
