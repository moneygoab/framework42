package org.framework42.creditcheck.model;

import org.framework42.address.model.ContactMethod;
import org.framework42.address.model.InformationProvider;

public interface ApplicantContactMethod {

    public int getId();

    public ContactMethod getContactMethod();

    public InformationProvider getInformationProvider();

    public String getAddress();

}
