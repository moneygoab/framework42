package org.framework42.model.impl;

import org.framework42.model.CreditBureauApplicationResponse;
import org.framework42.model.CreditDecision;
import org.framework42.services.Money;

public class SimpleCreditBureauApplicationResponse implements CreditBureauApplicationResponse {

    protected final CreditDecision creditBureauDecision;

    protected final Money recommendedAmount;

    protected final String creditBureauDecisionAsHtml;

    private final int numberOfCreditChecks;

    private final Money declaredIncome;

    private final int numberOfDebtCollections;

    private final Money sumOfDebtCollections;

    public SimpleCreditBureauApplicationResponse(CreditDecision creditBureauDecision, Money recommendedAmount, String creditBureauDecisionAsHtml,
                                                 int numberOfCreditChecks, Money declaredIncome, int numberOfDebtCollections, Money sumOfDebtCollections) {

        this.creditBureauDecision = creditBureauDecision;
        this.recommendedAmount = recommendedAmount;
        this.creditBureauDecisionAsHtml = creditBureauDecisionAsHtml;
        this.numberOfCreditChecks = numberOfCreditChecks;
        this.declaredIncome = declaredIncome;
        this.numberOfDebtCollections = numberOfDebtCollections;
        this.sumOfDebtCollections = sumOfDebtCollections;
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

}
