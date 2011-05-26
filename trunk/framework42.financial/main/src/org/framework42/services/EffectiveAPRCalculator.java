package org.framework42.services;

import java.math.BigDecimal;

public interface EffectiveAPRCalculator {

    public float calculate(BigDecimal loanAmount, BigDecimal nominalInterest, int monthsOfPayback, BigDecimal startFee, BigDecimal monthFee);


}
