package org.framework42.creditcheck.model.impl;

import org.framework42.creditcheck.model.CreditBureauApplicationResponse;
import org.framework42.creditcheck.model.CreditCheckEngagements;
import org.framework42.creditcheck.model.CreditDecision;
import org.framework42.services.Money;
import org.framework42.services.impl.MoneyImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import static org.framework42.utils.NotNegativeChecker.notNegative;
import static org.framework42.utils.NullChecker.notNull;

public class SimpleCreditBureauApplicationResponse implements CreditBureauApplicationResponse {

    protected final CreditDecision creditBureauDecision;

    protected final Money recommendedAmount;

    protected final String creditBureauDecisionAsHtml;

    protected final String coApplicantCreditBureauDecisionAsHtml;

    protected final int numberOfCreditChecks;

    protected final Money declaredIncome;

    protected final int numberOfDebtCollections;

    protected final Money sumOfDebtCollections;

    protected final int numberOfPreviousLoans;

    protected final Money sumOfPreviousLoans;

    protected final String reasonCodes;

    protected final List<CreditCheckEngagements> creditCheckEngagements;

    public SimpleCreditBureauApplicationResponse() {

        this.creditBureauDecision = CreditDecision.NOT_DECIDED_YET;
        this.recommendedAmount = new MoneyImpl(BigDecimal.ZERO, Currency.getInstance("SEK"));
        this.creditBureauDecisionAsHtml = "";
        this.coApplicantCreditBureauDecisionAsHtml = "";
        this.numberOfCreditChecks = 0;
        this.declaredIncome = new MoneyImpl(BigDecimal.ZERO, Currency.getInstance("SEK"));
        this.numberOfDebtCollections = 0;
        this.sumOfDebtCollections = new MoneyImpl(BigDecimal.ZERO, Currency.getInstance("SEK"));
        this.numberOfPreviousLoans = 0;
        this.sumOfPreviousLoans = new MoneyImpl(BigDecimal.ZERO, Currency.getInstance("SEK"));
        this.reasonCodes = "";
        this.creditCheckEngagements = new ArrayList<>();
    }

    public SimpleCreditBureauApplicationResponse(CreditDecision creditBureauDecision, Money recommendedAmount, String creditBureauDecisionAsHtml, String coApplicantCreditBureauDecisionAsHtml,
                                                 int numberOfCreditChecks, Money declaredIncome, int numberOfDebtCollections, Money sumOfDebtCollections, int numberOfPreviousLoans, Money sumOfPreviousLoans, String reasonCodes, List<CreditCheckEngagements> creditCheckEngagements) {

        this.creditBureauDecision = notNull(creditBureauDecision, "Credit bureau decision can't be null!");
        this.recommendedAmount = notNull(recommendedAmount, "Recommended amount can't be null!");
        this.creditBureauDecisionAsHtml = notNull(creditBureauDecisionAsHtml, "Decision as HTML can't be null!");
        this.coApplicantCreditBureauDecisionAsHtml = notNull(coApplicantCreditBureauDecisionAsHtml, "Co-applicant decision as HTML can't be null!");
        this.numberOfCreditChecks = notNegative(numberOfCreditChecks, "Number of credit checks can't be of negative value!");
        this.declaredIncome = notNull(declaredIncome, "Declared income can't be null!");
        this.numberOfDebtCollections = notNegative(numberOfDebtCollections, "Number of debt collections can't be of negative value!");
        this.sumOfDebtCollections = notNull(sumOfDebtCollections, "Sum of debt collections can't be null!");
        this.numberOfPreviousLoans = notNegative(numberOfPreviousLoans, "Number of previous loans can't be of negative value!");
        this.sumOfPreviousLoans = notNull(sumOfPreviousLoans, "Sum of previous loans can't be null!");
        this.reasonCodes = notNull(reasonCodes, "Reason codes can't be null!");
        this.creditCheckEngagements = notNull(creditCheckEngagements, "Credit Check Engagements cant be null!");
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
    public String getCoApplicantCreditBureauDecisionAsHtml() {
        return coApplicantCreditBureauDecisionAsHtml;
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
    public int getNumberOfPreviousLoans() {
        return numberOfPreviousLoans;
    }

    @Override
    public Money getSumOfPreviousLoans() {
        return sumOfPreviousLoans;
    }

    @Override
    public String getReasonCodes() {
        return reasonCodes;
    }

    @Override
    public List<CreditCheckEngagements> getCreditCheckEngagements() {
        return creditCheckEngagements;
    }
}
