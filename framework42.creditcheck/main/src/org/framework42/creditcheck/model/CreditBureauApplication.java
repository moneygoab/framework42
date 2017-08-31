package org.framework42.creditcheck.model;

import org.framework42.services.Money;

import java.util.Date;

public interface CreditBureauApplication {

    int getId();

    ApplicationType getType();

    ApplicationStatus getStatus();

    Date getApplicationDate();

    Money getAppliedAmount();

    Money getPreviousDebt();
    
    ApplicationChannel getApplicationChannel();

    Applicant getMainApplicant();

    Applicant getCoApplicant();

    CreditBureauApplicationResponse getCreditBureauResponse();

    int getExtendedApplicationId();

}
