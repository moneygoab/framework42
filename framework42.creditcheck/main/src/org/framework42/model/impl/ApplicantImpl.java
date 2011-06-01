package org.framework42.model.impl;

import org.framework42.model.Applicant;
import org.framework42.model.*;
import org.framework42.model.ApplicantContactMethod;
import org.framework42.model.ApplicantNames;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.framework42.utils.NotNegativeChecker.notNegative;
import static org.framework42.utils.NullChecker.notNull;

public class ApplicantImpl implements Applicant {

    private final int id;

    private final String governmentId;

    private final BigDecimal riskLevel;

    private final long birthDate;

    private final ApplicantNames applicantNames;

    private final TrustedAddress applicantAddress;

    private List<ApplicantContactMethod> contactMethods;

    private final int annualIncome;

    public ApplicantImpl(int id, String governmentId, BigDecimal riskLevel, Date birthDate, ApplicantNames applicantNames, TrustedAddress applicantAddress, List<ApplicantContactMethod> contactMethods, int annualIncome) {
        this.id = notNegative(id, "Id can't be of negative value!");
        this.governmentId = notNull(governmentId, "Government id can't be null!");
        this.riskLevel = notNull(riskLevel, "Risk level can't be null!");
        this.birthDate = notNull(birthDate, "Birth date can't be null!").getTime();
        this.applicantNames= notNull(applicantNames, "Applicant names can't be null!");

        this.applicantAddress = notNull(applicantAddress, "Applicant address can't be null!");

        this.contactMethods = notNull(contactMethods, "Contact methods can't be null!");

        this.annualIncome = notNegative(annualIncome, "Annual income can't be less then zero!");

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getGovernmentId() {
        return governmentId;
    }

    @Override
    public BigDecimal getRiskLevel() {

        return riskLevel;
    }

    @Override
    public Date getBirthDate() {
        return new Date(birthDate);
    }

    @Override
    public ApplicantNames getApplicantNames() {
        return applicantNames;
    }

    @Override
    public TrustedAddress getAddress() {
        return applicantAddress;
    }

    @Override
    public int getAnnualIncome() {
        return annualIncome;
    }

    @Override
    public List<ApplicantContactMethod> getContactMethods() {
        return contactMethods;
    }

}
