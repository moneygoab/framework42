package org.framework42.creditcheck.model.impl;

import org.framework42.creditcheck.model.*;
import org.framework42.services.Money;

import java.time.LocalDateTime;
import java.util.Date;

import static org.framework42.utils.NotNegativeChecker.notNegative;
import static org.framework42.utils.NullChecker.notNull;

public class CreditBureauApplicationImpl implements CreditBureauApplication {

    private final int id;

    private final ApplicationType type;

    private final ApplicationStatus status;

    private final LocalDateTime applicationDate;

    private final Money appliedAmount;

    protected final Money previousDebt;

    protected final Money previousDebtCoApplicant;

    private final ApplicationChannel applicationChannel;

    private final Applicant mainApplicant;

    private final Applicant coApplicant;

    private final CreditBureauApplicationResponse creditBureauResponse;

    private final int extendedApplicationId;

    public CreditBureauApplicationImpl(int id, ApplicationType applicationType, ApplicationStatus status, LocalDateTime applicationDate, Money appliedAmount, Money previousDebt, Money previousDebtCoApplicant, ApplicationChannel applicationChannel, Applicant mainApplicant, Applicant coApplicant, int extendedApplicationId) {

        this.id = notNegative(id, "Id can't be of negative value!");
        this.type = notNull(applicationType, "Application type can't be null!");
        this.status = notNull(status, "Status can't be null!");
        this.applicationDate = notNull(applicationDate, "Application date can't be null!");
        this.appliedAmount = notNull(appliedAmount, "Applied amount can't be null!");
        this.previousDebt = notNull(previousDebt, "Previous debt can't be null!");
        this.previousDebtCoApplicant = notNull(previousDebtCoApplicant, "Previous debt co applicant can't be null!");
        this.applicationChannel = notNull(applicationChannel, "Application channel can't be null!");
        this.mainApplicant = notNull(mainApplicant, "Main applicant can't be null!");
        this.coApplicant = coApplicant;
        this.extendedApplicationId = extendedApplicationId;

        this.creditBureauResponse = null;
    }

    public CreditBureauApplicationImpl(int id, ApplicationType applicationType, ApplicationStatus status, LocalDateTime applicationDate, Money appliedAmount, Money previousDebt, Money previousDebtCoApplicant, ApplicationChannel applicationChannel, Applicant mainApplicant, Applicant coApplicant, CreditBureauApplicationResponse creditBureauResponse, int extendedApplicationId) {

        this.id = notNegative(id, "Id can't be of negative value!");
        this.type = notNull(applicationType, "Application type can't be null!");
        this.status = notNull(status, "Status can't be null!");
        this.applicationDate = notNull(applicationDate, "Application date can't be null!");
        this.appliedAmount = notNull(appliedAmount, "Applied amount can't be null!");
        this.previousDebt = notNull(previousDebt, "Previous debt can't be null!");
        this.previousDebtCoApplicant = notNull(previousDebtCoApplicant, "Previous debt co applicant can't be null!");
        this.applicationChannel = notNull(applicationChannel, "Application channel can't be null!");
        this.mainApplicant = notNull(mainApplicant, "Main applicant can't be null!");
        this.coApplicant = coApplicant;
        this.creditBureauResponse = notNull(creditBureauResponse, "Credit bureau response can't be null! Use constructor without this parameter instead.");
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
        return status;
    }

    @Override
    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    @Override
    public Money getAppliedAmount() {
        return appliedAmount;
    }

    @Override
    public Money getPreviousDebt() {
        return previousDebt;
    }

    @Override
    public Money getPreviousDebtCoApplicant() {
        return previousDebtCoApplicant;
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
