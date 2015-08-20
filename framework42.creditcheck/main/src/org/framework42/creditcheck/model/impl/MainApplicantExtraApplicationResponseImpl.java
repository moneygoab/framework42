package org.framework42.creditcheck.model.impl;

import org.framework42.creditcheck.model.Applicant;
import org.framework42.creditcheck.model.CreditBureauApplicationResponse;
import org.framework42.creditcheck.model.CreditDecision;
import org.framework42.creditcheck.model.MainApplicantExtraApplicationResponse;

import java.math.BigDecimal;

public class MainApplicantExtraApplicationResponseImpl implements MainApplicantExtraApplicationResponse {

    private final int applicationId;

    private final Applicant applicant;

    private final String htmlResponse;

    private final CreditBureauApplicationResponse creditBureauApplicationResponse;

    public MainApplicantExtraApplicationResponseImpl(int applicationId, Applicant applicant, String htmlResponse, CreditBureauApplicationResponse creditBureauApplicationResponse) {

        this.applicationId = applicationId;
        this.applicant = applicant;
        this.htmlResponse = htmlResponse;
        this.creditBureauApplicationResponse = creditBureauApplicationResponse;
    }

    @Override
    public int getApplicationId() {

        return applicationId;
    }

    @Override
    public Applicant getApplicant() {

        return applicant;
    }

    @Override
    public String getHtmlResponse() {

        return htmlResponse;
    }

    public CreditBureauApplicationResponse getCreditBureauApplicationResponse() {
        return creditBureauApplicationResponse;
    }
}
