package org.framework42.creditcheck.model;

public interface CreditBureauCompanyApplicationResponse {

    String getGovernmentId();

    String getName();

    String getCreditCheckAsHtml();

    CreditDecision getCreditDecision();

}
