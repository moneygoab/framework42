package org.framework42.creditcheck.model.impl;

import org.framework42.creditcheck.model.Applicant;
import org.framework42.creditcheck.model.MainApplicantExtraApplicationResponse;

public class MainApplicantExtraApplicationResponseImpl implements MainApplicantExtraApplicationResponse {

    private final int applicationId;

    private final Applicant applicant;

    private final String htmlResponse;

    public MainApplicantExtraApplicationResponseImpl(int applicationId, Applicant applicant, String htmlResponse) {

        this.applicationId = applicationId;
        this.applicant = applicant;
        this.htmlResponse = htmlResponse;
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

}
