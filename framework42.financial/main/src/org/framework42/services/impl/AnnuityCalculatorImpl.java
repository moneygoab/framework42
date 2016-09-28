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

        System.out.println(new AnnuityCalculatorImpl().getMinimumToPay(new BigDecimal(1864), new BigDecimal(0), 12));

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

        if(interest.compareTo(BigDecimal.ZERO)<=0) {

            int rounded = initialAmount.intValue()/monthsToPayBack;

            if(initialAmount.intValue()%monthsToPayBack!=0) {

                rounded ++;
            }

            return rounded;

        } else {

            BigDecimal monthlyInterest = interest.divide(new BigDecimal(1200), 10, RoundingMode.UP);

            BigDecimal topCalc = monthlyInterest.multiply(monthlyInterest.add(new BigDecimal(1)).pow(monthsToPayBack));

            BigDecimal bottomCalc = monthlyInterest.add(new BigDecimal(1)).pow(monthsToPayBack).subtract(new BigDecimal(1));

            //BigDecimal stuff = topCalc.divide(bottomCalc);
            double stuff = topCalc.doubleValue() / bottomCalc.doubleValue();

            int rounded = (int) (initialAmount.doubleValue() * stuff);

            if ((initialAmount.doubleValue() * stuff) - rounded > 0) {
                rounded++;
            }

            if(rounded<0) {

                rounded = 0;
            }

            //return initialAmount.multiply( stuff ).intValue();
            return rounded;
        }
    }

    @Override
    public double getMinimumToPayNotRounded(BigDecimal initialAmount, BigDecimal interest, int monthsToPayBack) {

        BigDecimal monthlyInterest = interest.divide(new BigDecimal(1200), 10, RoundingMode.UP);

        BigDecimal topCalc = monthlyInterest.multiply( monthlyInterest.add(new BigDecimal(1)).pow(monthsToPayBack));

        BigDecimal bottomCalc = monthlyInterest.add(new BigDecimal(1)).pow(monthsToPayBack).subtract(new BigDecimal(1));

        //BigDecimal stuff = topCalc.divide(bottomCalc);
        double stuff = topCalc.doubleValue()/bottomCalc.doubleValue();

        double returnValue = initialAmount.doubleValue()* stuff;

        if(returnValue<0) {

            returnValue = 0;
        }

        //return initialAmount.multiply( stuff ).intValue();
        return returnValue;
    }

}
