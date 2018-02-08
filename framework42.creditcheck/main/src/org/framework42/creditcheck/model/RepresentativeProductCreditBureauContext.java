package org.framework42.creditcheck.model;

import java.util.Date;

public interface RepresentativeProductCreditBureauContext {

    int getId();

    CreditBureauContext getCreditBureauContext();

    int getPriority();

    CreditBureauStatus getStatus();

    int getMinimumAmount();

    int getMaximumAmount();

    Date getValidFrom();

    Date getValidTo();

}
