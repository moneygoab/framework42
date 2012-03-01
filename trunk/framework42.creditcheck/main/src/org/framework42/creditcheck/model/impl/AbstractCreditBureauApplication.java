package org.framework42.creditcheck.model.impl;

import org.framework42.creditcheck.model.*;
import org.framework42.services.Money;

import java.util.Date;

import static org.framework42.utils.NotNegativeChecker.notNegative;
import static org.framework42.utils.NullChecker.notNull;

public class AbstractCreditBureauApplication implements CreditBureauApplication {

    protected final int id;

    protected final ApplicationType type;

    protected final ApplicationStatus applicationStatus;

    protected final long applicationDate;

    protected final Money appliedAmount;

    protected final ApplicationChannel applicationChannel;

    protected final Applicant mainApplicant;

    protected final Applicant coApplicant;

    private final CreditBureauApplicationResponse creditBureauResponse;

    private final int extendedApplicationId;

    public AbstractCreditBureauApplication(int id, ApplicationType type, ApplicationStatus applicationStatus, Date applicationDate, Money appliedAmount, ApplicationChannel applicationChannel, Applicant mainApplicant, Applicant coApplicant, int extendedApplicationId) {

        this.id = notNegative(id, "Id can't be of negative value!");
        this.type = notNull(type, "Application type can't be null!");
        this.applicationStatus = notNull(applicationStatus, "Application status can't be null!");
        this.applicationDate = notNull(applicationDate, "Application date can't be null!").getTime();
        this.appliedAmount = notNull(appliedAmount, "Applied amount can't be null!");
        this.applicationChannel = notNull(applicationChannel, "Application channel can't be null!");
        this.mainApplicant = notNull(mainApplicant, "Main applicant can't ve null!");
        this.coApplicant = coApplicant;
        this.extendedApplicationId = extendedApplicationId;

        this.creditBureauResponse = null;
    }

    public AbstractCreditBureauApplication(int id, ApplicationType type, ApplicationStatus status, Date applicationDate, Money appliedAmount, ApplicationChannel applicationChannel, Applicant mainApplicant, Applicant coApplicant, CreditBureauApplicationResponse creditBureauResponse, int extendedApplicationId) {

        this.id = notNegative(id, "Id can't be of negative value!");
        this.type = notNull(type, "Application type can't be null!");
        this.applicationStatus = notNull(status, "Status can't be null!");
        this.applicationDate = notNull(applicationDate.getTime(), "Application date can't be null!");
        this.appliedAmount = notNull(appliedAmount, "Applied amount can't be null!");
        this.applicationChannel = notNull(applicationChannel, "Application channel can't be null!");
        this.mainApplicant = notNull(mainApplicant, "Main applicant can't be null!");
        this.coApplicant = coApplicant;
        this.creditBureauResponse = creditBureauResponse;
        this.extendedApplicationId = extendedApplicationId;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public ApplicationType getType() {

        return type;
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

    @Override
    public CreditBureauApplicationResponse getCreditBureauResponse() {
        return creditBureauResponse;
    }

    @Override
    public int getExtendedApplicationId() {

        return extendedApplicationId;
    }
}
