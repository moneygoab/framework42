package org.framework42.creditcheck.model;

public interface MainApplicantExtraApplicationResponse {

    int getApplicationId();

    Applicant getApplicant();

    String getHtmlResponse();

    CreditBureauApplicationResponse getCreditBureauApplicationResponse();

}
