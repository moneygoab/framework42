package org.framework42.creditcheck.model.impl;

import org.framework42.creditcheck.model.CreditBureauCompanyApplicationResponse;
import org.framework42.creditcheck.model.CreditDecision;

import static org.framework42.utils.NullChecker.notNull;

public class CreditBureauCompanyApplicationResponseImpl implements CreditBureauCompanyApplicationResponse {

    private final String governmentId;

    private final String creditCheckAsHtml;

    private final CreditDecision creditDecision;

    public CreditBureauCompanyApplicationResponseImpl(String governmentId, String creditCheckAsHtml, CreditDecision creditDecision) {

        this.governmentId = notNull(governmentId);
        this.creditCheckAsHtml = notNull(creditCheckAsHtml);
        this.creditDecision = notNull(creditDecision);
    }

    @Override
    public String getGovernmentId() {
        return governmentId;
    }

    @Override
    public String getCreditCheckAsHtml() {
        return creditCheckAsHtml;
    }

    @Override
    public CreditDecision getCreditDecision() {
        return creditDecision;
    }
}
