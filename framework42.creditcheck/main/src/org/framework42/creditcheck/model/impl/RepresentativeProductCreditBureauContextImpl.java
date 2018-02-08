package org.framework42.creditcheck.model.impl;

import org.framework42.creditcheck.model.CreditBureauContext;
import org.framework42.creditcheck.model.CreditBureauStatus;
import org.framework42.creditcheck.model.RepresentativeProductCreditBureauContext;

import java.time.LocalDateTime;

import static org.framework42.utils.NotNegativeChecker.notNegative;
import static org.framework42.utils.NullChecker.notNull;

public class RepresentativeProductCreditBureauContextImpl implements RepresentativeProductCreditBureauContext {

    private final int id;

    private final CreditBureauContext creditBureauContext;

    private final int priority;

    private final CreditBureauStatus status;

    private final int minimumAmount;

    private final int maximumAmount;

    private final LocalDateTime fromDate;

    private final LocalDateTime toDate;

    public RepresentativeProductCreditBureauContextImpl(CreditBureauContext creditBureauContext, int priority, CreditBureauStatus status, int minimumAmount, int maximumAmount, LocalDateTime fromDate, LocalDateTime toDate) {

        this.id = 0;
        this.creditBureauContext = notNull(creditBureauContext);
        this.priority = notNegative(priority);
        this.status = notNull(status);
        this.minimumAmount = notNegative(minimumAmount);
        this.maximumAmount = notNegative(maximumAmount);
        this.fromDate = notNull(fromDate);
        this.toDate = notNull(toDate);
    }

    public RepresentativeProductCreditBureauContextImpl(int id, CreditBureauContext creditBureauContext, int priority, CreditBureauStatus status, int minimumAmount, int maximumAmount, LocalDateTime fromDate, LocalDateTime toDate) {

        this.id = notNegative(id);
        this.creditBureauContext = notNull(creditBureauContext);
        this.priority = notNegative(priority);
        this.status = notNull(status);
        this.minimumAmount = notNegative(minimumAmount);
        this.maximumAmount = notNegative(maximumAmount);
        this.fromDate = notNull(fromDate);
        this.toDate = notNull(toDate);
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
    public LocalDateTime getValidFrom() {

        return fromDate;
    }

    @Override
    public LocalDateTime getValidTo() {

        return toDate;
    }
}
