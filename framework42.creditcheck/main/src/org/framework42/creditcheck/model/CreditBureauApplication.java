package org.framework42.creditcheck.model;

import org.framework42.services.Money;

import java.time.LocalDateTime;

public interface CreditBureauApplication {

    int getId();

    ApplicationType getType();

    ApplicationStatus getStatus();

    LocalDateTime getApplicationDate();

    Money getAppliedAmount();

    Money getPreviousDebt();

    Money getPreviousDebtCoApplicant();

    ApplicationChannel getApplicationChannel();

    Applicant getMainApplicant();

    Applicant getCoApplicant();

    CreditBureauApplicationResponse getCreditBureauResponse();

    int getExtendedApplicationId();

}
