package org.framework42.creditcheck.model;

public interface CoApplicantApplicationResponse {

    int getApplicationId();

    Applicant getCoApplicant();

    String getHtmlResponse();

}
