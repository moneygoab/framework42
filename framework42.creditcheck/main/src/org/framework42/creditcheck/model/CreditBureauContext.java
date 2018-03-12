package org.framework42.creditcheck.model;

public interface CreditBureauContext {

    int getId();

    CreditBureau getCreditBureau();

    String getDescription();

    String getUserId();

    String getPassword();

    String getName();

    String getPolicyProduct();

    String getPolicyRules();

    boolean isSendNewTotalDebt();

    boolean isSendNewTotalDebtCoApplicant();

}
