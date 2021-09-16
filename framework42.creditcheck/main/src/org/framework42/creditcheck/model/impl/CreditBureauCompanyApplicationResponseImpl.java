package org.framework42.creditcheck.model.impl;

import org.framework42.address.model.Address;
import org.framework42.address.model.impl.SimpleSecureAddressImpl;
import org.framework42.creditcheck.model.CreditBureauCompanyApplicationResponse;
import org.framework42.creditcheck.model.CreditDecision;

import static org.framework42.utils.NullChecker.notNull;

public class CreditBureauCompanyApplicationResponseImpl implements CreditBureauCompanyApplicationResponse {

    private final String governmentId;

    private final String name;

    private final String creditCheckAsHtml;

    private final CreditDecision creditDecision;

    private final Address address;

    public CreditBureauCompanyApplicationResponseImpl(String governmentId, String creditCheckAsHtml, CreditDecision creditDecision) {

        this.governmentId = notNull(governmentId);
        this.name = "";
        this.creditCheckAsHtml = notNull(creditCheckAsHtml);
        this.creditDecision = notNull(creditDecision);
        this.address = new SimpleSecureAddressImpl();
    }

    public CreditBureauCompanyApplicationResponseImpl(String governmentId, String name, String creditCheckAsHtml, CreditDecision creditDecision, Address address) {

        this.governmentId = notNull(governmentId);
        this.name = notNull(name);
        this.creditCheckAsHtml = notNull(creditCheckAsHtml);
        this.creditDecision = notNull(creditDecision);
        this.address = notNull(address);
    }

    @Override
    public String getGovernmentId() {
        return governmentId;
    }

    @Override
    public String getName() {
        return name;
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
