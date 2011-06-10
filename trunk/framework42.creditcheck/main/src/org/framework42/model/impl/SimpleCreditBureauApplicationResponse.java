package org.framework42.model.impl;

import org.framework42.model.CreditBureauApplicationResponse;
import org.framework42.model.CreditDecision;
import org.framework42.services.Money;

public class SimpleCreditBureauApplicationResponse implements CreditBureauApplicationResponse {

    protected final CreditDecision creditBureauDecision;

    protected final Money recommendedAmount;

    protected final String creditBureauDecisionAsHtml;

    protected final int numberOfCreditChecks;

    protected final Money declaredIncome;

    protected final int numberOfDebtCollections;

    protected final Money sumOfDebtCollections;

    protected final String reasonCodes;

    public SimpleCreditBureauApplicationResponse(CreditDecision creditBureauDecision, Money recommendedAmount, String creditBureauDecisionAsHtml,
                                                 int numberOfCreditChecks, Money declaredIncome, int numberOfDebtCollections, Money sumOfDebtCollections, String reasonCodes) {

        this.creditBureauDecision = creditBureauDecision;
        this.recommendedAmount = recommendedAmount;
        this.creditBureauDecisionAsHtml = creditBureauDecisionAsHtml;
        this.numberOfCreditChecks = numberOfCreditChecks;
        this.declaredIncome = declaredIncome;
        this.numberOfDebtCollections = numberOfDebtCollections;
        this.sumOfDebtCollections = sumOfDebtCollections;
        this.reasonCodes = reasonCodes;
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

    @Override
    public int getNumberOfCreditChecks() {
        return numberOfCreditChecks;
    }

    @Override
    public Money getDeclaredIncome() {
        return declaredIncome;
    }

    @Override
    public int getNumberOfDebtCollections() {
        return numberOfDebtCollections;
    }

    @Override
    public Money getSumOfDebtCollections() {
        return sumOfDebtCollections;
    }

    @Override
    public String getReasonCodes() {
        return reasonCodes;
    }

}
