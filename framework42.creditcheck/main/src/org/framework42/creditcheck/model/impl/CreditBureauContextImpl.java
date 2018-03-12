package org.framework42.creditcheck.model.impl;

import org.framework42.creditcheck.model.CreditBureau;
import org.framework42.creditcheck.model.CreditBureauContext;

import static org.framework42.utils.NullChecker.notNull;

public class CreditBureauContextImpl implements CreditBureauContext {

    private final int id;

    private final CreditBureau creditBureau;

    private final String description;

    private final String userId;

    private final String password;

    private final String name;

    private final String policyProduct;

    private final String policyRules;

    private final boolean sendNewTotalDebt;

    private final boolean sendNewTotalDebtCoApplicant;

    public CreditBureauContextImpl(int id, CreditBureau creditBureau, String description, String userId, String password, String name, String policyProduct, String policyRules, boolean sendNewTotalDebt, boolean sendNewTotalDebtCoApplicant) {

        this.id = id;
        this.creditBureau = notNull(creditBureau, "Credit bureau can't be null!");
        this.description = notNull(description, "Description can't be null!");
        this.userId = notNull(userId, "User id can't be null!");
        this.password = notNull(password, "Password can't be null!");
        this.name = notNull(name, "Name can't be null!");
        this.policyProduct = notNull(policyProduct, "Policy product can't be null!");
        this.policyRules = notNull(policyRules, "Policy rules can't be null!");
        this.sendNewTotalDebt = notNull(sendNewTotalDebt, "Send new total debt can't be null!");
        this.sendNewTotalDebtCoApplicant = notNull(sendNewTotalDebtCoApplicant, "Send new total debt co applicant can't be null!");
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public CreditBureau getCreditBureau() {
        return creditBureau;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPolicyProduct() {
        return policyProduct;
    }

    @Override
    public String getPolicyRules() {
        return policyRules;
    }

    @Override
    public boolean isSendNewTotalDebt() {
        return sendNewTotalDebt;
    }

    @Override
    public boolean isSendNewTotalDebtCoApplicant() {
        return sendNewTotalDebtCoApplicant;
    }

}
