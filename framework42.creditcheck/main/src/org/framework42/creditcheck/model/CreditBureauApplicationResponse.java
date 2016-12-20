package org.framework42.creditcheck.model;

import org.framework42.services.Money;

public interface CreditBureauApplicationResponse {

    public Money getRecommendedAmount();

    public CreditDecision getCreditBureauDecision();

    public String getCreditBureauDecisionAsHtml();

    public String getCoApplicantCreditBureauDecisionAsHtml();

    public int getNumberOfCreditChecks();

    public Money getDeclaredIncome();

    public int getNumberOfDebtCollections();

    public Money getSumOfDebtCollections();

    public int getNumberOfPreviousLoans();

    public Money getSumOfPreviousLoans();

    public String getReasonCodes();

}
