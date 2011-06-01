package org.framework42.model;

import org.framework42.services.Money;

public interface CreditBureauApplicationResponse {

    public Money getRecommendedAmount();

    public CreditDecision getCreditBureauDecision();

    public String getCreditBureauDecisionAsHtml();

}
