package org.framework42.creditcheck.model.impl;

import org.framework42.creditcheck.model.CreditBureauContext;
import org.framework42.creditcheck.model.CreditBureauStatus;
import org.framework42.creditcheck.model.ProductCreditBureauContext;

import java.util.Date;

public class ProductCreditBureauContextImpl implements ProductCreditBureauContext {

    private final int id;

    private final CreditBureauContext creditBureauContext;

    private final int priority;

    private final CreditBureauStatus status;

    private final int minimumAmount;

    private final int maximumAmount;

    private final long fromDate;

    private final long toDate;

    public ProductCreditBureauContextImpl(int id, CreditBureauContext creditBureauContext, int priority, CreditBureauStatus status, int minimumAmount, int maximumAmount, Date fromDate, Date toDate) {

        this.id = id;
        this.creditBureauContext = creditBureauContext;
        this.priority = priority;
        this.status = status;
        this.minimumAmount = minimumAmount;
        this.maximumAmount = maximumAmount;
        this.fromDate = fromDate.getTime();
        this.toDate = toDate.getTime();
    }

    @Override
    public int getId() {

        return id;
    }

    @Override
    public CreditBureauContext getCreditBureauContext() {

        return creditBureauContext;
    }

    @Override
    public int getPriority() {

        return priority;
    }

    @Override
    public CreditBureauStatus getStatus() {

        return status;
    }

    @Override
    public int getMinimumAmount() {

        return minimumAmount;
    }

    @Override
    public int getMaximumAmount() {

        return maximumAmount;
    }

    @Override
    public Date getValidFrom() {

        return new Date(fromDate);
    }

    @Override
    public Date getValidTo() {

        return new Date(toDate);
    }
}
