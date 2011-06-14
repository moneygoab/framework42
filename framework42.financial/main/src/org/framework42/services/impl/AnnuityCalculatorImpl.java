package org.framework42.services.impl;

import org.framework42.services.AnnuityCalculator;
import org.framework42.services.ProxyService;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AnnuityCalculatorImpl extends ProxyService<AnnuityCalculatorImpl> implements AnnuityCalculator {

    public AnnuityCalculatorImpl() {
        super("org.framework42.financial");
    }

    /*public int getMinimumToPayOld(BigDecimal initialAmount, BigDecimal monthlyInterest, int monthsToPayBack) {

        BigDecimal topCalc = monthlyInterest.multiply( monthlyInterest.add(new BigDecimal(1)).pow(monthsToPayBack));

        BigDecimal bottomCalc = monthlyInterest.add(new BigDecimal(1)).pow(monthsToPayBack).subtract(new BigDecimal(1));

        //BigDecimal stuff = topCalc.divide(bottomCalc);
        double stuff = topCalc.doubleValue()/bottomCalc.doubleValue();

        //return initialAmount.multiply( stuff ).intValue();
        return (int) (initialAmount.doubleValue()* stuff);

    } */

    @Override
    public int getMinimumToPay(BigDecimal initialAmount, BigDecimal interest, int monthsToPayBack) {

        BigDecimal monthlyInterest = interest.divide(new BigDecimal(1200), 10, RoundingMode.UP);

        BigDecimal topCalc = monthlyInterest.multiply( monthlyInterest.add(new BigDecimal(1)).pow(monthsToPayBack));

        BigDecimal bottomCalc = monthlyInterest.add(new BigDecimal(1)).pow(monthsToPayBack).subtract(new BigDecimal(1));

        //BigDecimal stuff = topCalc.divide(bottomCalc);
        double stuff = topCalc.doubleValue()/bottomCalc.doubleValue();

        //return initialAmount.multiply( stuff ).intValue();
        return (int) (initialAmount.doubleValue()* stuff);
    }

    @Override
    public double getMinimumToPayNotRounded(BigDecimal initialAmount, BigDecimal interest, int monthsToPayBack) {

        BigDecimal monthlyInterest = interest.divide(new BigDecimal(1200), 10, RoundingMode.UP);

        BigDecimal topCalc = monthlyInterest.multiply( monthlyInterest.add(new BigDecimal(1)).pow(monthsToPayBack));

        BigDecimal bottomCalc = monthlyInterest.add(new BigDecimal(1)).pow(monthsToPayBack).subtract(new BigDecimal(1));

        //BigDecimal stuff = topCalc.divide(bottomCalc);
        double stuff = topCalc.doubleValue()/bottomCalc.doubleValue();

        //return initialAmount.multiply( stuff ).intValue();
        return initialAmount.doubleValue()* stuff;
    }

}
