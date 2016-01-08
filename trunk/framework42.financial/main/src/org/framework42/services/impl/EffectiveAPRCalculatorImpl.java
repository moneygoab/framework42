package org.framework42.services.impl;

import org.framework42.services.EffectiveAPRCalculator;
import org.framework42.services.ProxyService;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class EffectiveAPRCalculatorImpl extends ProxyService<EffectiveAPRCalculatorImpl> implements EffectiveAPRCalculator {

    private final double noOfDaysInYear = 365f;

    private final double noOfDaysInMonth = 30.41666f;

    private final static BigDecimal monthsInYear = new BigDecimal(12);

    private final static BigDecimal two = new BigDecimal(2);

    private final static BigDecimal hundred = new BigDecimal(100);

    private final static BigDecimal thousand = new BigDecimal(1000);

    private final static BigDecimal tenThousand = new BigDecimal(10000);

    private final static BigDecimal maxDiff = new BigDecimal(0.000000005);

    private final static BigDecimal minDiff = new BigDecimal(-0.000000005);

    public EffectiveAPRCalculatorImpl() {

        super("com.moneypal.banksystem.core");
    }

    public static void main(String[] arg) {

        //    new EffectiveAPRCalculatorImpl().calculate();

        System.out.println(new EffectiveAPRCalculatorImpl().calculate(new BigDecimal(15000), new BigDecimal(18.6), 72, new BigDecimal(0), new BigDecimal(0)));
    }

    @Override
    public float calculate(BigDecimal loanAmount, BigDecimal nominalInterest, int monthsOfPayback, BigDecimal startFee, BigDecimal monthFee) {

        BigDecimal kronor = loanAmount;

        BigDecimal r = BigDecimal.ZERO;
        BigDecimal hamort = BigDecimal.ZERO;

//Andrat for kvartal
        BigDecimal rant= nominalInterest.divide(hundred);
        BigDecimal firstranta = rant.multiply(BigDecimal.ONE).divide(monthsInYear, RoundingMode.UP).multiply(kronor);

        BigDecimal amor = BigDecimal.ZERO;
        BigDecimal totrkost = BigDecimal.ZERO;
        BigDecimal[] payment = new BigDecimal[monthsOfPayback+1];
//        ivar = new Array(120)
///Annuiteten
        //int N = monthsOfPayback;
        BigDecimal I = rant.divide(monthsInYear, RoundingMode.UP);
//I=ranta/100/12
        BigDecimal PV = loanAmount;
        BigDecimal eranta = BigDecimal.ZERO;

        BigDecimal monthInterest = BigDecimal.ONE.add(I).pow(monthsOfPayback);
        BigDecimal annuitet = PV.multiply( (I.divide( monthInterest, RoundingMode.UP ).multiply( monthInterest ).divide( monthInterest.subtract(BigDecimal.ONE), RoundingMode.UP ) ) );

// Amorteringsbeloppet -----------
        amor = loanAmount.divide(new BigDecimal(monthsOfPayback), RoundingMode.UP);
// Totala räntekostnaden ------------------
        kronor = loanAmount;
        r = BigDecimal.ZERO;

        for (int i=1; i<=monthsOfPayback; i++){

            r = r.add( rant.multiply(BigDecimal.ONE.divide(monthsInYear, RoundingMode.UP)).multiply(kronor) );
            payment[i] = annuitet.add(monthFee);
            kronor = kronor.subtract(amor);
        }

        payment[1] = payment[1].add(startFee); /*avgiften betalas på första*/
        totrkost = r; /* totala räntekostnaden */
// Effektiva räntan utan avgift--------
        BigDecimal eranta_utan = (BigDecimal.ONE.add(rant.divide(monthsInYear, RoundingMode.UP))).pow(monthsInYear.intValue()).subtract(BigDecimal.ONE);
// Effektiva räntan ---------------------------------
        kronor = loanAmount;
        BigDecimal maxr = BigDecimal.ONE.add( eranta_utan.divide(new BigDecimal(25)) );
        BigDecimal minr = BigDecimal.ONE.add( eranta_utan.divide(monthsInYear).multiply(hundred) );
        BigDecimal egiss = totrkost.add(startFee);
        egiss = (egiss.multiply(monthsInYear)).divide( ((loanAmount.divide(two)).multiply(new BigDecimal(monthsOfPayback))) );
        egiss = egiss.divide(new BigDecimal(11));
        BigDecimal gissning = BigDecimal.ONE.add(egiss);
        kronor = kronor.multiply(thousand);

        BigDecimal rantasiff= rant.divide(monthsInYear, RoundingMode.UP);
        kronor = kronor.divide(thousand);

        //double restskuld1=(((kronor)-(payment[2])*(1-Math.pow((1+(rantasiff)),-1))/(rantasiff))/Math.pow((1+(rantasiff)),-1));
        BigDecimal restskuld1= ( ( kronor.subtract(payment[2]).multiply( BigDecimal.ONE.subtract( BigDecimal.ONE.add(rantasiff).pow(-1) ) ).divide(rantasiff, RoundingMode.UP) ).divide(BigDecimal.ONE.add(rantasiff).pow(-1), RoundingMode.UP ) );
        amor = kronor.subtract(restskuld1);

        BigDecimal amort1 = amor.add(hamort);
        BigDecimal totalforsta = amort1.add(firstranta); //+ avgift;

        BigDecimal man = two;
        BigDecimal ar= BigDecimal.ONE;

        for(int i=2; i<10; i++) {

            //Fel?
            BigDecimal restskuld2= ( ( kronor.subtract(payment[i].subtract(monthFee)).multiply(BigDecimal.ONE.subtract(BigDecimal.ONE.add(rantasiff).pow(-i))).divide(rantasiff) ).divide(BigDecimal.ONE.add(rantasiff).pow(-i)) );
            amor = restskuld1.subtract(restskuld2);
            restskuld1=restskuld2;

            if ((i%10) == 0) { man= monthsInYear;}
            if ((i%12) != 0) {man = new BigDecimal(i%12);}
            if ((i%12) == 0) { man= monthsInYear;}
            if ((i%12) == 1){
                ar = new BigDecimal(i-1).divide(monthsInYear).add(BigDecimal.ONE);
                man = BigDecimal.ONE;
            }
        }
        for(int i=10; i<=monthsOfPayback; i++) {

            if ((i%10) == 0) { man= monthsInYear;}
            if ((i%12) != 0) {man = new BigDecimal(i%12);}
            if ((i%12) == 0) { man= monthsInYear;}
            if ((i%12) == 1){
                ar = new BigDecimal(i-1).divide(monthsInYear).add(BigDecimal.ONE);
                man = BigDecimal.ONE;
            }

            //double restskuld2Old =(((kronor)-(payment[i]-aviavgift)*(1-Math.pow((1+(rantasiff)),-i))/(rantasiff))/Math.pow((1+(rantasiff)),-i));
            BigDecimal restskuld2= ( ( kronor.subtract( payment[i].subtract(monthFee)).multiply( BigDecimal.ONE.subtract(BigDecimal.ONE.add(rantasiff).pow(-i)) ).divide(rantasiff) ).divide(BigDecimal.ONE.add(rantasiff).pow(-i)));
            amor= restskuld1.subtract(restskuld2);
            restskuld1 = restskuld2;
        }

        return calculateEffectiveAPR(loanAmount, nominalInterest, new BigDecimal(monthsOfPayback), payment, startFee, totrkost);
    }

    public float calculateEffectiveAPR(BigDecimal loanAmount, BigDecimal nominalInterest, BigDecimal monthsOfPayback, BigDecimal[] payment, BigDecimal avgift, BigDecimal totrkost) {

        BigDecimal nominalMonthInterest = nominalInterest.divide(monthsInYear);

        BigDecimal effectiveAPRWithoutFees = (BigDecimal.ONE.add( nominalMonthInterest ).pow(monthsInYear.intValue())).subtract(BigDecimal.ONE);
        // Effektiva räntan ---------------------------------
        BigDecimal kronor = new BigDecimal(loanAmount.doubleValue());
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal maxr = BigDecimal.ONE.add( effectiveAPRWithoutFees.divide(new BigDecimal(25)) );
        BigDecimal minr = BigDecimal.ONE.add( effectiveAPRWithoutFees.divide(monthsInYear).multiply(new BigDecimal(100)) );
        BigDecimal egiss = totrkost.add(avgift);
        egiss = (egiss.multiply(monthsInYear)).divide( (loanAmount.divide(two)).multiply(monthsOfPayback) );

        BigDecimal[] ivar = new BigDecimal[monthsOfPayback.intValue()+1];

        egiss = egiss.divide(new BigDecimal(12-1));
        BigDecimal gissning = BigDecimal.ONE.add(egiss);
        kronor = kronor.multiply(thousand);
        sum = sum.multiply(thousand);
        //System.out.println((sum== 0) +":" + (sum-kronor > 0.00000001d) +":" + (sum-kronor < -0.00000001d));
        //System.out.println(sum +":"+ kronor+":"+(sum-kronor));
        while(sum.intValue()== 0 || (sum.subtract(kronor).compareTo(minDiff) < 0 || sum.subtract(kronor).compareTo(maxDiff) > 0)) {
            //System.out.println((sum== 0) +":" + (sum-kronor > 0.00000001d) +":" + (sum-kronor < -0.00000001d));
            //System.out.println(sum +":"+ kronor+":"+(sum-kronor));
            sum = BigDecimal.ZERO;

            for(int i=1; i<=monthsOfPayback.intValue(); i++) {

                ivar[i] = gissning.pow(i);
                sum = sum.add(payment[i].divide(ivar[i])); /* dela alla pays med ivar */
            }
            sum = sum.multiply(thousand);
            if (sum.compareTo(kronor) > 0){
                maxr = gissning;
                gissning = (minr.add(gissning)).divide(two);
            }
            if (sum.compareTo(kronor) < 0){
                minr = gissning;
                gissning = (gissning.add(maxr)).divide(two);
            }
        }
        //System.out.println((sum== 0) +":" + (sum-kronor > 0.0000000001d) +":" + (sum-kronor < -0.00000001d));
        //System.out.println(sum +":"+ kronor+":"+(sum-kronor));

        BigDecimal effectiveAPRCalculated = (gissning.pow(12).subtract(new BigDecimal(-1))).multiply(tenThousand);//Math.round(((Math.pow(gissning,(12))) -1)*10000);
        effectiveAPRCalculated = effectiveAPRCalculated.divide(hundred);
        return effectiveAPRCalculated.floatValue();
    }

}
