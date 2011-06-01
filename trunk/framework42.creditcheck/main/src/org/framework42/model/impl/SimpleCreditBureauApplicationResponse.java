package org.framework42.model.impl;

import org.framework42.model.CreditBureauApplicationResponse;
import org.framework42.model.CreditDecision;
import org.framework42.services.Money;

public class SimpleCreditBureauApplicationResponse implements CreditBureauApplicationResponse {

    protected final CreditDecision creditBureauDecision;

    protected final Money recommendedAmount;

    protected final String creditBureauDecisionAsHtml;

    public SimpleCreditBureauApplicationResponse(CreditDecision creditBureauDecision, Money recommendedAmount, String creditBureauDecisionAsHtml) {

        this.creditBureauDecision = creditBureauDecision;
        this.recommendedAmount = recommendedAmount;
        this.creditBureauDecisionAsHtml = creditBureauDecisionAsHtml;
    }

    @Override
    public Money getRecommendedAmount() {
        return recommendedAmount;
    }

    @Override
    public CreditDecision getCreditBureauDecision() {
        return creditBureauDecision;
    }

    @Override
    public String getCreditBureauDecisionAsHtml() {
        return creditBureauDecisionAsHtml;
    }

}
