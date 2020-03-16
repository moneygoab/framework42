package org.framework42.creditcheck.model;

import java.time.LocalDate;

public interface CreditCheckEngagements {

    int getId();

    int getCustomerId();

    ApplicationProductCategory getApplicationType();

    int getApplicationId();

    LocalDate getMonth();

    int getAmountBlanco();

    int getAmountInstallment();

    int getAmountAccountCredit();

    int getTotalUsed();

    int getTotalApproved();

    int getNumberOfEngagements();

    int getNumberOfCreditors();

    int getAmountUsedAtMoneyGo();

    int getAmountApprovedAtMoneyGo();
}
