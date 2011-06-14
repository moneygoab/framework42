package org.framework42.services;

import java.math.BigDecimal;

public interface AnnuityCalculator {

    public int getMinimumToPay(BigDecimal initialAmount, BigDecimal monthlyInterest, int monthsToPayBack);

    public double getMinimumToPayNotRounded(BigDecimal initialAmount, BigDecimal interest, int monthsToPayBack);

}
