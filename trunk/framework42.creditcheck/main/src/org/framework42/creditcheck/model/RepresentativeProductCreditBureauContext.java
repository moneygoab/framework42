package org.framework42.creditcheck.model;

import java.util.Date;

public interface RepresentativeProductCreditBureauContext {

    public int getId();

    public CreditBureauContext getCreditBureauContext();

    public int getPriority();

    public CreditBureauStatus getStatus();

    public int getMinimumAmount();

    public int getMaximumAmount();

    public Date getValidFrom();

    public Date getValidTo();

}
