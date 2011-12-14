package org.framework42.creditcheck.model.impl;

import org.framework42.creditcheck.model.Applicant;
import org.framework42.creditcheck.model.CoApplicantApplicationResponse;

public class CoApplicantApplicationResponseImpl implements CoApplicantApplicationResponse {

    private final int applicationId;

    private final Applicant applicant;

    private final String htmlResponse;

    public CoApplicantApplicationResponseImpl(int applicationId, Applicant applicant, String htmlResponse) {

        this.applicationId = applicationId;
        this.applicant = applicant;
        this.htmlResponse = htmlResponse;
    }

    @Override
    public int getApplicationId() {

        return applicationId;
    }

    @Override
    public Applicant getCoApplicant() {

        return applicant;
    }

    @Override
    public String getHtmlResponse() {

        return htmlResponse;
    }
}
