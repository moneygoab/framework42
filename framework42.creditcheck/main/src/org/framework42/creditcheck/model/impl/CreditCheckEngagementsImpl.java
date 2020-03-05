package org.framework42.creditcheck.model.impl;

import org.framework42.creditcheck.model.ApplicationProductCategory;
import org.framework42.creditcheck.model.CreditCheckEngagements;

import java.time.LocalDate;

import static org.framework42.utils.GreaterThenZeroChecker.greaterThenZero;
import static org.framework42.utils.NotNegativeChecker.notNegative;
import static org.framework42.utils.NullChecker.notNull;

public class CreditCheckEngagementsImpl implements CreditCheckEngagements {

    private final int id;

    private final int customerId;

    private final ApplicationProductCategory applicationType;

    private final int applicationId;

    private final LocalDate month;

    private final int amountBlanco;

    private final int amountInstallment;

    private final int amountAccountCredit;

    private final int totalUsed;

    private final int totalApproved;

    private final int numberOfEngagements;

    private final int numberOfCreditors;

    private final int amountUsedAtMoneyGo;

    private final int amountApprovedAtMoneyGo;

    public CreditCheckEngagementsImpl(LocalDate month, int amountBlanco, int amountInstallment, int amountAccountCredit, int totalUsed, int totalApproved, int numberOfEngagements, int numberOfCreditors, int amountUsedAtMoneyGo, int amountApprovedAtMoneyGo) {
        this.id = 0;
        this.customerId = 0;
        this.applicationType = ApplicationProductCategory.LOAN;
        this.applicationId = 0;
        this.month = notNull(month);
        this.amountBlanco = notNegative(amountBlanco);
        this.amountInstallment = notNegative(amountInstallment);
        this.amountAccountCredit = notNegative(amountAccountCredit);
        this.totalUsed = notNegative(totalUsed);
        this.totalApproved = notNegative(totalApproved);
        this.numberOfEngagements = notNegative(numberOfEngagements);
        this.numberOfCreditors = notNegative(numberOfCreditors);
        this.amountUsedAtMoneyGo = notNegative(amountUsedAtMoneyGo);
        this.amountApprovedAtMoneyGo = notNegative(amountApprovedAtMoneyGo);
    }

    public CreditCheckEngagementsImpl(int customerId, ApplicationProductCategory applicationType, int applicationId, LocalDate month, int amountBlanco, int amountInstallment, int amountAccountCredit, int totalUsed, int totalApproved, int numberOfEngagements, int numberOfCreditors, int amountUsedAtMoneyGo, int amountApprovedAtMoneyGo) {
        this.id = 0;
        this.customerId = notNegative(customerId);
        this.applicationType = notNull(applicationType);
        this.applicationId = greaterThenZero(applicationId);
        this.month = notNull(month);
        this.amountBlanco = notNegative(amountBlanco);
        this.amountInstallment = notNegative(amountInstallment);
        this.amountAccountCredit = notNegative(amountAccountCredit);
        this.totalUsed = notNegative(totalUsed);
        this.totalApproved = notNegative(totalApproved);
        this.numberOfEngagements = notNegative(numberOfEngagements);
        this.numberOfCreditors = notNegative(numberOfCreditors);
        this.amountUsedAtMoneyGo = notNegative(amountUsedAtMoneyGo);
        this.amountApprovedAtMoneyGo = notNegative(amountApprovedAtMoneyGo);
    }

    public CreditCheckEngagementsImpl(int id, int customerId, ApplicationProductCategory applicationType, int applicationId, LocalDate month, int amountBlanco, int amountInstallment, int amountAccountCredit, int totalUsed, int totalApproved, int numberOfEngagements, int numberOfCreditors, int amountUsedAtMoneyGo, int amountApprovedAtMoneyGo) {
        this.id = greaterThenZero(id);
        this.customerId = notNegative(customerId);
        this.applicationType = notNull(applicationType);
        this.applicationId = greaterThenZero(applicationId);
        this.month = notNull(month);
        this.amountBlanco = notNegative(amountBlanco);
        this.amountInstallment = notNegative(amountInstallment);
        this.amountAccountCredit = notNegative(amountAccountCredit);
        this.totalUsed = notNegative(totalUsed);
        this.totalApproved = notNegative(totalApproved);
        this.numberOfEngagements = notNegative(numberOfEngagements);
        this.numberOfCreditors = notNegative(numberOfCreditors);
        this.amountUsedAtMoneyGo = notNegative(amountUsedAtMoneyGo);
        this.amountApprovedAtMoneyGo = notNegative(amountApprovedAtMoneyGo);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getCustomerId() {
        return customerId;
    }

    @Override
    public ApplicationProductCategory getApplicationType() {
        return applicationType;
    }

    @Override
    public int getApplicationId() {
        return applicationId;
    }

    @Override
    public LocalDate getMonth() {
        return month;
    }

    @Override
    public int getAmountBlanco() {
        return amountBlanco;
    }

    @Override
    public int getAmountInstallment() {
        return amountInstallment;
    }

    @Override
    public int getAmountAccountCredit() {
        return amountAccountCredit;
    }

    @Override
    public int getTotalUsed() {
        return totalUsed;
    }

    @Override
    public int getTotalApproved() {
        return totalApproved;
    }

    @Override
    public int getNumberOfEngagements() {
        return numberOfEngagements;
    }

    @Override
    public int getNumberOfCreditors() {
        return numberOfCreditors;
    }

    @Override
    public int getAmountUsedAtMoneyGo() {
        return amountUsedAtMoneyGo;
    }

    @Override
    public int getAmountApprovedAtMoneyGo() {
        return amountApprovedAtMoneyGo;
    }

}
