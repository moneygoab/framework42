package org.framework42.creditcheck.model;

import org.framework42.model.ContactMethod;
import org.framework42.model.TrustedAddress;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface Applicant {

    public int getId();

    public String getGovernmentId();

    public BigDecimal getRiskLevel();

    public Date getBirthDate();

    public ApplicantNames getApplicantNames();

    public TrustedAddress getAddress();

    public List<ApplicantContactMethod> getContactMethods();

    public ApplicantContactMethod getContactMethodOfType(ContactMethod contactMethod);

    public int getAnnualIncome();

}
