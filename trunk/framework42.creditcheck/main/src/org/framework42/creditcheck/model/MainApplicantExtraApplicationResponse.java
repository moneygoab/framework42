package org.framework42.creditcheck.model;

public interface MainApplicantExtraApplicationResponse {

    public int getApplicationId();

    public Applicant getApplicant();

    public String getHtmlResponse();

    public CreditBureauApplicationResponse getCreditBureauApplicationResponse();

}
