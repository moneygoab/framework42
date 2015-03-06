package org.framework42.creditcheck.model.impl;

import org.framework42.creditcheck.model.AddressCheckResponse;
import org.framework42.creditcheck.model.AddressStatus;
import org.framework42.model.TrustedAddress;

public class AddressCheckResponseImpl implements AddressCheckResponse {

    private final AddressStatus status;

    private final String firstName;

    private final String surname;

    private final String fullName;

    private final TrustedAddress address;

    public AddressCheckResponseImpl(AddressStatus status, String firstName, String surname, String fullName, TrustedAddress address) {
        this.status = status;
        this.firstName = firstName;
        this.surname = surname;
        this.fullName = fullName;
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

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return "AddressCheckResponseImpl{" +
                "status=" + status +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address=" + address.toString() +
                '}';
    }
}
