package org.framework42.creditcheck.model;

import java.time.LocalDateTime;

public interface RepresentativeProductCreditBureauContext {

    int getId();

    CreditBureauContext getCreditBureauContext();

    int getPriority();

    CreditBureauStatus getStatus();

    int getMinimumAmount();

    int getMaximumAmount();

    LocalDateTime getValidFrom();

    LocalDateTime getValidTo();

}
