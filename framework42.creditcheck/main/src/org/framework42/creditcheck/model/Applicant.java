package org.framework42.creditcheck.model;

import org.framework42.address.model.ContactMethod;
import org.framework42.address.model.TrustedAddress;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface Applicant {

    int getId();

    String getGovernmentId();

    BigDecimal getRiskLevel();

    LocalDate getBirthDate();

    ApplicantNames getApplicantNames();

    TrustedAddress getAddress();

    List<ApplicantContactMethod> getContactMethods();

    ApplicantContactMethod getContactMethodOfType(ContactMethod contactMethod);

    int getAnnualIncome();

}
