package org.framework42.creditcheck.model;

public interface CreditBureauCompanyApplicationResponse {

    String getGovernmentId();

    String getCreditCheckAsHtml();

    CreditDecision getCreditDecision();

}
