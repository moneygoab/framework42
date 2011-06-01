package org.framework42.model.impl;

import org.framework42.model.*;
import org.framework42.services.Money;

import java.util.Date;

import static org.framework42.utils.NotNegativeChecker.notNegative;
import static org.framework42.utils.NullChecker.notNull;

public class CreditBureauApplicationImpl implements CreditBureauApplication {

    private final int id;

    private final ApplicationStatus status;

    private final long applicationDate;

    private final Money appliedAmount;

    private final ApplicationChannel applicationChannel;

    private final Applicant mainApplicant;

    private final Applicant coApplicant;

    private final CreditBureauApplicationResponse creditBureauResponse;

    public CreditBureauApplicationImpl(int id, ApplicationStatus status, Date applicationDate, Money appliedAmount, ApplicationChannel applicationChannel, Applicant mainApplicant, Applicant coApplicant) {

        this.id = notNegative(id, "Id can't be of negative value!");
        this.status = notNull(status, "Status can't be null!");
        this.applicationDate = notNull(applicationDate.getTime(), "Application date can't be null!");
        this.appliedAmount = notNull(appliedAmount, "Applied amount can't be null!");
        this.applicationChannel = notNull(applicationChannel, "Application channel can't be null!");
        this.mainApplicant = notNull(mainApplicant, "Main applicant can't be null!");
        this.coApplicant = coApplicant;

        this.creditBureauResponse = null;
    }

    public CreditBureauApplicationImpl(int id, ApplicationStatus status, Date applicationDate, Money appliedAmount, ApplicationChannel applicationChannel, Applicant mainApplicant, Applicant coApplicant, CreditBureauApplicationResponse creditBureauResponse) {

        this.id = notNegative(id, "Id can't be of negative value!");
        this.status = notNull(status, "Status can't be null!");
        this.applicationDate = notNull(applicationDate.getTime(), "Application date can't be null!");
        this.appliedAmount = notNull(appliedAmount, "Applied amount can't be null!");
        this.applicationChannel = notNull(applicationChannel, "Application channel can't be null!");
        this.mainApplicant = notNull(mainApplicant, "Main applicant can't be null!");
        this.coApplicant = coApplicant;
        this.creditBureauResponse = creditBureauResponse;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public ApplicationStatus getStatus() {
        return status;
    }

    @Override
    public Date getApplicationDate() {
        return new Date(applicationDate);
    }

    @Override
    public Money getAppliedAmount() {
        return appliedAmount;
    }

    @Override
    public ApplicationChannel getApplicationChannel() {
        return applicationChannel;
    }

    @Override
    public Applicant getMainApplicant() {
        return mainApplicant;
    }

    @Override
    public Applicant getCoApplicant() {
        return coApplicant;
    }

    @Override
    public CreditBureauApplicationResponse getCreditBureauResponse() {
        return creditBureauResponse;
    }

}
