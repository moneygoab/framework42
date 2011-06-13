package org.framework42.creditcheck.model.impl;

import org.framework42.creditcheck.model.CreditBureauApplicationResponse;
import org.framework42.creditcheck.model.CreditDecision;
import org.framework42.services.Money;

import static org.framework42.utils.NotNegativeChecker.notNegative;
import static org.framework42.utils.NullChecker.notNull;

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

        this.creditBureauDecision = notNull(creditBureauDecision, "Credit bureau decision can't be null!");
        this.recommendedAmount = notNull(recommendedAmount, "Recommended amount can't be null!");
        this.creditBureauDecisionAsHtml = notNull(creditBureauDecisionAsHtml, "Decision as HTML can't be null!");
        this.numberOfCreditChecks = notNegative(numberOfCreditChecks, "Number of credit checks can't be of negative value!");
        this.declaredIncome = notNull(declaredIncome, "Declared income can't be null!");
        this.numberOfDebtCollections = notNegative(numberOfDebtCollections, "Number of debt collections can't be of negative value!");
        this.sumOfDebtCollections = notNull(sumOfDebtCollections, "Sum of debt collections can't be null!");
        this.reasonCodes = notNull(reasonCodes, "Reason codes can't be null!");
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
