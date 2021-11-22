package org.framework42.creditcheck.model;

import org.framework42.address.model.TrustedAddress;

public interface CreditBureauCompanyApplicationResponse {

    String getGovernmentId();

    String getName();

    String getCreditCheckAsHtml();

    CreditDecision getCreditDecision();

    TrustedAddress getAddress();

}
