package org.framework42.model.impl;

import org.framework42.model.CreditBureau;
import org.framework42.model.CreditBureauContext;

import static org.framework42.utils.NullChecker.notNull;

public class CreditBureauContextImpl implements CreditBureauContext {

    private final CreditBureau creditBureau;

    private final String userId;

    private final String password;

    private final String name;

    private final String policyProduct;

    private final String policyRules;

    public CreditBureauContextImpl(CreditBureau creditBureau, String userId, String password, String name, String policyProduct, String policyRules) {

        this.creditBureau = notNull(creditBureau, "Credit bureau can't be null!");
        this.userId = notNull(userId, "User id can't be null!");
        this.password = notNull(password, "Password can't be null!");
        this.name = notNull(name, "Name can't be null!");
        this.policyProduct = notNull(policyProduct, "Policy product can't be null!");
        this.policyRules = notNull(policyRules, "Policy rules can't be null!");
    }

    @Override
    public CreditBureau getCreditBureau() {
        return creditBureau;
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

}
