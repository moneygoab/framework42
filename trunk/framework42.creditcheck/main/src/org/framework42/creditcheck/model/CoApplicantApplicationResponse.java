package org.framework42.creditcheck.model;

public interface CoApplicantApplicationResponse {

    public int getApplicationId();

    public Applicant getCoApplicant();

    public String getHtmlResponse();

}
