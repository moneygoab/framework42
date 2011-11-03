package org.framework42.services.impl;

import org.framework42.services.AnnuityCalculator;
import org.framework42.services.EffectiveAPRCalculator;

import java.math.BigDecimal;

public class EffectiveAPRCalculator2Impl implements EffectiveAPRCalculator {

    private final int monthsInYear = 12;

    private final AnnuityCalculator annuityCalculator;

    /*
    www.sevenday.se and www.nordea.se has calculators to compare result with.
    **/
    public EffectiveAPRCalculator2Impl() {

        annuityCalculator = new AnnuityCalculatorImpl();
    }

    @Override
    public float calculate(BigDecimal loanAmount, BigDecimal nominalInterest, int monthsOfPayback, BigDecimal startFee, BigDecimal monthFee) {

        double kronor = loanAmount.intValue();

        double nominalInterestValue = nominalInterest.doubleValue() / 100;
        double nominalMonthInterestValue = nominalInterestValue / monthsInYear;

        double[] amountPayedInMonth = new double[120];

        double annuityAmount = annuityCalculator.getMinimumToPayNotRounded(loanAmount, nominalInterest, monthsOfPayback);

        double amortisationAmount = loanAmount.intValue() / monthsOfPayback;

        double totalInterestAmount = 0;
        for (int i=1;i<=monthsOfPayback;i++){
            totalInterestAmount += nominalMonthInterestValue * kronor;
            amountPayedInMonth[i] = annuityAmount+monthFee.intValue();
            kronor = kronor - amortisationAmount;
        }

        //Start fee payed in first month
        amountPayedInMonth[1] += startFee.intValue();

        return (float) effectiveAPR(loanAmount, nominalMonthInterestValue, monthsOfPayback, amountPayedInMonth, startFee.intValue(), totalInterestAmount);

    }

    public static void main(String[] args) {

        System.out.println(new EffectiveAPRCalculator2Impl().calculate(new BigDecimal(15000), new BigDecimal(18.3), 72, new BigDecimal(0), new BigDecimal(0)));
        //System.out.println(new EffectiveAPRCalculator2Impl().calculate(new BigDecimal(10000), new BigDecimal(27), 6 * 12, new BigDecimal(700), BigDecimal.ZERO));
    }


    public double effectiveAPR(BigDecimal loanAmount, double nominalMonthInterestValue, int monthsOfPayback, double[] amountPayedInMonth, int startFee, double totalInterestAmount) {

        double effectiveAPRWithout = ( (Math.pow( 1 + nominalMonthInterestValue, monthsInYear) ) -1 );
        double bigPaybackSum = loanAmount.intValue() * 1000;
        double sum = 0;
        double maxAPR = 1+effectiveAPRWithout/25;
        double minAPR = 1+(effectiveAPRWithout/monthsInYear*100);

        double guess = calculateGuess(loanAmount, monthsOfPayback, startFee, totalInterestAmount);

        double[] ivar = new double[120];

        //System.out.println(sum +":"+ bigPaybackSum+":"+(sum-bigPaybackSum));
        while(sum == 0 || (sum-bigPaybackSum > 0.0000005d || sum-bigPaybackSum < -0.0000005d) ) {
            //System.out.println(sum +":"+ bigPaybackSum+":"+(sum-bigPaybackSum));
            sum = 0;
            for(int i=1;i<=monthsOfPayback;i++){
                ivar[i] = Math.pow(guess,i);
                sum += amountPayedInMonth[i]/ivar[i]; /* dela alla pays med ivar */
            }
            sum *=1000;
            if (sum > bigPaybackSum){
                maxAPR = guess;
                guess = (minAPR+guess)/2;
            }
            if (sum < bigPaybackSum){
                minAPR = guess;
                guess = (guess+maxAPR)/2;
            }
        }
        //System.out.println(sum +":"+ bigPaybackSum+":"+(sum-bigPaybackSum));

        return (Math.round(((Math.pow(guess, monthsInYear)) -1)*10000)) / 100d;
    }

    private double calculateGuess(BigDecimal loanAmount, int monthsOfPayback, int startFee, double totalInterestAmount) {

        double guess = totalInterestAmount + startFee;
        guess = (guess*monthsInYear) / ( (loanAmount.intValue()/2) * monthsOfPayback );
        guess = guess/(monthsInYear-1);
        guess = 1+guess;

        return guess;
    }

}
