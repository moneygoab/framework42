package org.framework42.creditcheck.model;

import org.framework42.address.model.ContactMethod;
import org.framework42.address.model.InformationProvider;

public interface ApplicantContactMethod {

    int getId();

    ContactMethod getContactMethod();

    InformationProvider getInformationProvider();

    String getAddress();

}
