package org.framework42.model.impl;

import org.framework42.model.ApplicantContactMethod;
import org.framework42.model.ContactMethod;
import org.framework42.model.InformationProvider;

import static org.framework42.utils.NullChecker.notNull;

public class ApplicantContactMethodImpl implements ApplicantContactMethod {

    private final int id;

    private final ContactMethod contactMethod;

    private final InformationProvider informationProvider;

    private final String address;

    public ApplicantContactMethodImpl(int id, ContactMethod contactMethod, InformationProvider informationProvider, String address) {
        this.id = id;
        this.contactMethod = notNull(contactMethod, "Contact method can't be null!");
        this.informationProvider = notNull(informationProvider, "Information provider can't be null!");
        this.address = notNull(address, "Address value can't be null!");
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public ContactMethod getContactMethod() {
        return contactMethod;
    }

    @Override
    public InformationProvider getInformationProvider() {
        return informationProvider;
    }

    @Override
    public String getAddress() {
        return address;
    }

}
