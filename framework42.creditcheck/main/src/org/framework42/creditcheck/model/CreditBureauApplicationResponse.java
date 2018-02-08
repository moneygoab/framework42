package org.framework42.creditcheck.model;

import org.framework42.services.Money;

public interface CreditBureauApplicationResponse {

    Money getRecommendedAmount();

    CreditDecision getCreditBureauDecision();

    String getCreditBureauDecisionAsHtml();

    String getCoApplicantCreditBureauDecisionAsHtml();

    int getNumberOfCreditChecks();

    Money getDeclaredIncome();

    int getNumberOfDebtCollections();

    Money getSumOfDebtCollections();

    int getNumberOfPreviousLoans();

    Money getSumOfPreviousLoans();

    String getReasonCodes();

}
