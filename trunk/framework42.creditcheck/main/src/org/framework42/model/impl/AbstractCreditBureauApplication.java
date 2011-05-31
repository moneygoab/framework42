package org.framework42.model.impl;

import org.framework42.model.Applicant;
import org.framework42.model.ApplicationChannel;
import org.framework42.model.ApplicationStatus;
import org.framework42.model.CreditBureauApplication;
import org.framework42.services.Money;

import java.util.Date;

import static org.framework42.utils.NotNegativeChecker.notNegative;
import static org.framework42.utils.NullChecker.notNull;

public class AbstractCreditBureauApplication implements CreditBureauApplication {

    protected final int id;

    protected final ApplicationStatus applicationStatus;

    protected final long applicationDate;

    protected final Money appliedAmount;

    protected final ApplicationChannel applicationChannel;

    protected final Applicant mainApplicant;

    protected final Applicant coApplicant;

    public AbstractCreditBureauApplication(int id, ApplicationStatus applicationStatus, Date applicationDate, Money appliedAmount, ApplicationChannel applicationChannel, Applicant mainApplicant, Applicant coApplicant) {

        this.id = notNegative(id, "Id can't be of negative value!");
        this.applicationStatus = notNull(applicationStatus, "Application status can't be null!");
        this.applicationDate = notNull(applicationDate, "Application date can't be null!").getTime();
        this.appliedAmount = notNull(appliedAmount, "Applied amount can't be null!");
        this.applicationChannel = notNull(applicationChannel, "Application channel can't be null!");
        this.mainApplicant = notNull(mainApplicant, "Main applicant can't ve null!");
        this.coApplicant = coApplicant;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public ApplicationStatus getStatus() {
        return applicationStatus;
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

}
