package org.framework42.creditcheck.model.impl;

import org.framework42.address.model.ContactMethod;
import org.framework42.address.model.TrustedAddress;
import org.framework42.creditcheck.model.Applicant;
import org.framework42.creditcheck.model.ApplicantContactMethod;
import org.framework42.creditcheck.model.ApplicantNames;
import org.framework42.address.model.impl.SimpleSecureAddressImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.framework42.utils.NotNegativeChecker.notNegative;
import static org.framework42.utils.NullChecker.notNull;

public class ApplicantImpl implements Applicant {

    private final int id;

    private final String governmentId;

    private final BigDecimal riskLevel;

    private final LocalDate birthDate;

    private final ApplicantNames applicantNames;

    private final TrustedAddress applicantAddress;

    private List<ApplicantContactMethod> contactMethods;

    private final int annualIncome;

    public ApplicantImpl(String governmentId, LocalDate birthDate, List<ApplicantContactMethod> contactMethods) {

        this.id = 0;
        this.governmentId = governmentId;
        this.riskLevel = BigDecimal.ZERO;
        this.birthDate = birthDate;
        this.applicantNames = new ApplicantNamesImpl("", "", "");
        this.applicantAddress = new SimpleSecureAddressImpl();
        this.contactMethods = contactMethods;
        this.annualIncome = 0;
    }

    public ApplicantImpl(int id, String governmentId, BigDecimal riskLevel, LocalDate birthDate, ApplicantNames applicantNames, TrustedAddress applicantAddress, List<ApplicantContactMethod> contactMethods, int annualIncome) {

        this.id = notNegative(id, "Id can't be of negative value!");
        this.governmentId = notNull(governmentId, "Government id can't be null!");
        this.riskLevel = notNull(riskLevel, "Risk level can't be null!");
        this.birthDate = notNull(birthDate, "Birth date can't be null!");
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
    public LocalDate getBirthDate() {
        return birthDate;
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

    @Override
    public ApplicantContactMethod getContactMethodOfType(ContactMethod contactMethod) {

        for(ApplicantContactMethod applicantContactMethod: contactMethods) {

            if(applicantContactMethod.getContactMethod() == contactMethod) {

                return applicantContactMethod;
            }
        }

        return null;
    }
}
