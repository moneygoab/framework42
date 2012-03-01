package org.framework42.creditcheck.model;

import org.framework42.services.Money;

import java.util.Date;

public interface CreditBureauApplication {

    public int getId();

    public ApplicationType getType();

    public ApplicationStatus getStatus();

    public Date getApplicationDate();

    public Money getAppliedAmount();

    public ApplicationChannel getApplicationChannel();

    public Applicant getMainApplicant();

    public Applicant getCoApplicant();

    public CreditBureauApplicationResponse getCreditBureauResponse();

    public int getExtendedApplicationId();

}
