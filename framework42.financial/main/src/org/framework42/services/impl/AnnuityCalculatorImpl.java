package org.framework42.services.impl;

import org.framework42.services.AnnuityCalculator;
import org.framework42.services.ProxyService;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AnnuityCalculatorImpl extends ProxyService<AnnuityCalculatorImpl> implements AnnuityCalculator {

    public AnnuityCalculatorImpl() {
        super("org.framework42.financial");
    }

    public static void main(String[] args) {

        new AnnuityCalculatorImpl().getMinimumToPay(new BigDecimal(10000), new BigDecimal(18.3), 72);

        /*for(int i = 5000; i<31000; i+=1000) {
            System.out.println(
                    i + " : " +
                    new AnnuityCalculatorImpl().getMinimumToPay(new BigDecimal(i), new BigDecimal(15), 12)+" : "+
                    new AnnuityCalculatorImpl().getMinimumToPayNotRounded(new BigDecimal(i), new BigDecimal(15), 12)
            );
        } */
        //System.out.println(new AnnuityCalculatorImpl().getMinimumToPayNotRounded(new BigDecimal(15000), new BigDecimal(18.3), 72));
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

        int rounded = (int) (initialAmount.doubleValue()* stuff);

        if((initialAmount.doubleValue()* stuff) - rounded > 0) {
            rounded ++;
        }
        //return initialAmount.multiply( stuff ).intValue();
        return rounded;
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
