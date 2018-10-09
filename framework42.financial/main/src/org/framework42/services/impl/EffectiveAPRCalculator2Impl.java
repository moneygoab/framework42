package org.framework42.services.impl;

import org.framework42.services.AnnuityCalculator;
import org.framework42.services.EffectiveAPRCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class EffectiveAPRCalculator2Impl implements EffectiveAPRCalculator {

    private final int monthsInYear = 12;

    private final AnnuityCalculator annuityCalculator;

    /*
    https://www.konsumenternas.se/lana/olika-lan/om-konsumtionslan/lanekalkyl has calculator to compare result with.
    */
    public EffectiveAPRCalculator2Impl() {

        annuityCalculator = new AnnuityCalculatorImpl();
    }

    @Override
    public float calculate(BigDecimal loanAmount, BigDecimal nominalInterest, int monthsOfPayback, BigDecimal startFee, BigDecimal monthFee) {

        double balance = loanAmount.intValue();

        double nominalInterestValue = nominalInterest.doubleValue() / 100;
        double nominalMonthInterestValue = nominalInterestValue / monthsInYear;

        double[] amountPayedInMonth = new double[monthsOfPayback+1];

        double annuityAmount = annuityCalculator.getMinimumToPayNotRounded(loanAmount, nominalInterest, monthsOfPayback);

        double amortisationAmount = loanAmount.intValue() / monthsOfPayback;

        int totalAdminFee = 0;

        double totalInterestAmount = 0;
        for (int i=1;i<=monthsOfPayback;i++){
            totalInterestAmount += nominalMonthInterestValue * balance;
            amountPayedInMonth[i] = annuityAmount + monthFee.setScale(0, RoundingMode.UP).intValue();
            balance = balance - amortisationAmount;
            totalAdminFee += monthFee.intValue();
        }

        //Start fee payed in first month assumption made by the law
        amountPayedInMonth[1] = amountPayedInMonth[1]+startFee.setScale(2, RoundingMode.UP).intValue();

        return new BigDecimal(effectiveAPR(loanAmount, nominalMonthInterestValue, monthsOfPayback, amountPayedInMonth, startFee.intValue(), totalAdminFee, totalInterestAmount)).setScale(2, RoundingMode.UP).floatValue();

    }

    public static void main(String[] args) {

        System.out.println(new EffectiveAPRCalculator2Impl().calculate(new BigDecimal(30000), new BigDecimal(11.7), 72, new BigDecimal(0), new BigDecimal(20)));

    }


    public double effectiveAPR(BigDecimal loanAmount, double nominalMonthInterestValue, int monthsOfPayback, double[] amountPayedInMonth, int startFee, int totalAdminFee, double totalInterestAmount) {

        double effectiveAPRWithout = ( (Math.pow( 1 + nominalMonthInterestValue, monthsInYear) ) -1 );
        double bigPaybackSum = (loanAmount.intValue()) * 10000;
        double sum = 0;
        double maxAPR = 1+effectiveAPRWithout/monthsInYear;
        double minAPR = 1+(effectiveAPRWithout/monthsInYear*100);

        double guess = calculateGuess(loanAmount, monthsOfPayback, startFee, totalAdminFee, totalInterestAmount);
        //System.out.println((Math.round(((Math.pow(guess, monthsInYear)) -1)*10000)) / 100d);

        int tries = 0;

        while( (sum == 0 || (sum-bigPaybackSum > 0.0000005d || sum-bigPaybackSum < -0.0000005d)) && tries < 200+monthsOfPayback*2  ) {
            sum = 0;
            for(int i=1;i<=monthsOfPayback;i++){
                double iVar = Math.pow(guess,i);
                sum += amountPayedInMonth[i]/iVar; /* dela alla pays med ivar */
            }
            sum *=10000;
            if (sum > bigPaybackSum){
                maxAPR = guess;
                guess = (minAPR+guess)/2;
            }
            if (sum < bigPaybackSum){
                minAPR = guess;
                guess = (maxAPR+guess)/2;
            }
            tries++;
        }
        //System.out.println(tries);
        //System.out.println(sum +":"+ bigPaybackSum+":"+(sum-bigPaybackSum));

        return (Math.round(((Math.pow(guess, monthsInYear)) -1)*10000)) / 100d;
    }

    private double calculateGuess(BigDecimal loanAmount, int monthsOfPayback, int startFee, int totalAdminFee, double totalInterestAmount) {

        double guess = totalInterestAmount + startFee + totalAdminFee;
        guess = ((guess*monthsInYear)) / ( (loanAmount.intValue()/2) * monthsOfPayback );
        guess = guess/(monthsInYear-1);
        guess = 1+guess;

        return guess;
    }

}
