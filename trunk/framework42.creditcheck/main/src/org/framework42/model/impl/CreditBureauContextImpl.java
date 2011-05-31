package org.framework42.model.impl;

import org.framework42.model.CreditBureau;
import org.framework42.model.CreditBureauContext;

import static org.framework42.utils.NullChecker.notNull;

public class CreditBureauContextImpl implements CreditBureauContext {

    private final CreditBureau creditBureau;

    private final String userId;

    private final String password;

    private final String name;

    public CreditBureauContextImpl(CreditBureau creditBureau, String userId, String password, String name) {

        this.creditBureau = notNull(creditBureau, "Credit bureau can't be null!");
        this.userId = notNull(userId, "User id can't be null!");
        this.password = notNull(password, "Password can't be null!");
        this.name = notNull(name, "Name can't be null!");
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

}
