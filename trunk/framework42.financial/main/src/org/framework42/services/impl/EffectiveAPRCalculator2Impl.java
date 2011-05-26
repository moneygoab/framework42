package org.framework42.services.impl;

import org.framework42.services.EffectiveAPRCalculator;

import java.math.BigDecimal;

public class EffectiveAPRCalculator2Impl implements EffectiveAPRCalculator {

    @Override
    public float calculate(BigDecimal loanAmount, BigDecimal nominalInterest, int monthsOfPayback, BigDecimal startFee, BigDecimal monthFee) {

        System.out.println(kalklan2());

        return (float)kalklan2();
    }

    public static void main(String[] args) {

        new EffectiveAPRCalculator2Impl().calculate(new BigDecimal(10000), new BigDecimal(10), 12, new BigDecimal(100), new BigDecimal(30));
    }


    public double e_ranta(double in_belopp, double rant, double ant_manad, double[] payment, double avgift, double totrkost) {
        // Effektiva r&auml;ntan utan avgift--------
        double period = 1;
        double eranta_utan = ((Math.pow(1+rant/(12/period),(12/period))) -1);
        // Effektiva r&auml;ntan ---------------------------------
        double belopp = in_belopp;
        double kronor = belopp;
        double sum = 0;
        double maxr = 1+eranta_utan/25;
        double minr = 1+(eranta_utan/(12/period)*100);
        double egiss = totrkost + avgift;
        egiss = (egiss*(12/period)) / ((belopp/2) * ant_manad);

        double[] ivar = new double[120];

        egiss = egiss/(12/period -1);
        double gissning = 1+egiss;
        kronor *=1000;
        sum *=1000;
        //while (sum != kronor){
        //System.out.println((sum== 0) +":" + (sum-kronor > 0.00000001d) +":" + (sum-kronor < -0.00000001d));
        System.out.println(sum +":"+ kronor+":"+(sum-kronor));
        while(sum== 0 || (sum-kronor > 0.00000005d || sum-kronor < -0.00000005d)) {
            //System.out.println((sum== 0) +":" + (sum-kronor > 0.00000001d) +":" + (sum-kronor < -0.00000001d));
            System.out.println(sum +":"+ kronor+":"+(sum-kronor));
            sum = 0;
            for(int i=1;i<=ant_manad;i++){
                ivar[i] = Math.pow(gissning,i);
                sum += payment[i]/ivar[i]; /* dela alla pays med ivar */
            }
            sum *=1000;
            if (sum > kronor){
                maxr = gissning;
                gissning = (minr+gissning)/2;
            }
            if (sum < kronor){
                minr = gissning;
                gissning = (gissning+maxr)/2;
            }
        } /*while */
        //System.out.println((sum== 0) +":" + (sum-kronor > 0.0000000001d) +":" + (sum-kronor < -0.00000001d));
        System.out.println(sum +":"+ kronor+":"+(sum-kronor));

        double erantaut = Math.round(((Math.pow(gissning,(12/period))) -1)*10000);
        erantaut /= 100;
        return erantaut;
    }


    public double kalklan2(){
    //public void kalklan2(in_ar){
        //annuitet
        //var ar=in_ar;
        int period = 1;
        /*if (document.forms[0].bet.value == "m")
            period = 1;
          */
        double belopp = 5000d;

        double ranta = 27d;

        double aviavgift = 0;
        double avgift = 0;

        int numberOfYears = 6;
        int ant_manad = (12 * numberOfYears);

        double sum = 0;
        double totranta2 = 0;//summan för Totala räntan


        double kronor = belopp;
        double r = 0;
        double inbyte = 0;
        double hamort = 0;
//Andrat for kvartal
        double rant=(ranta/100d);
        double firstranta =rant*1/(12/1)*kronor;

        double amor = 0;
        double totrkost = 0;
        double[] payment = new double[120];
//        ivar = new Array(120)
        int sladd = (ant_manad % 12); /* antal månader utöver hela år */
        int hela = (ant_manad - sladd)/12; /* antal hela år*/
///Annuiteten
        double N = numberOfYears*(12/period);
        double I = rant/(12/period);
//I=ranta/100/12
        double PV = belopp;
        double eranta = 0;
        double annuitet = PV*((I*Math.pow(1+I,N)) / ((Math.pow(1+I,N))-1));

// Amorteringsbeloppet -----------
        amor = (belopp / ant_manad);
// Totala räntekostnaden ------------------
        kronor = belopp;
        r = 0;

        for (int i=1;i<=ant_manad;i++){
            r += rant*1/12/period*kronor;
            payment[i] = (annuitet)+aviavgift;
            kronor = kronor - amor;
        }

        payment[1] += avgift; /*avgiften betalas på första*/
        totrkost = r; /* totala räntekostnaden */
// Effektiva räntan utan avgift--------
        double eranta_utan = ((Math.pow(1+rant/12,12)) -1);
// Effektiva räntan ---------------------------------
        sum = 0;
        kronor = belopp;
        double maxr = 1+eranta_utan/25;
        double minr = 1+(eranta_utan/12*100);
        double egiss = totrkost + avgift;
        egiss = (egiss*12) / ((belopp/2) * ant_manad);
        egiss = egiss/11;
        double gissning = 1+egiss;
        kronor *=1000;
        sum *=1000;
        //document.forms[0].utannuitet.value=nb_format(Math.round(annuitet));

        double rantasiff=rant/(12/period);
        kronor = kronor/1000;
        double restskuld1=(((kronor)-(payment[2])*(1-Math.pow((1+(rantasiff)),-1))/(rantasiff))/Math.pow((1+(rantasiff)),-1));
        amor=kronor- restskuld1;
        double ferrari_f40=(payment[2]-aviavgift)-amor;
        totranta2 += ferrari_f40;
        //formetterings grejer?
        /*if (annuitet > 1000 && man>9)
            mellan1=" ";
        else if (annuitet > 1000)
            mellan1=" ";
        else if (man >9)
            mellan1=" ";
        else
            mellan1=" ";
        mellan2=" ";*/
        double amort1 = (amor) + (hamort);
        double totalforsta = amort1 + firstranta; //+ avgift;
        //document.forms[0].sumtab.value +="1:1" +mellan1+ nb_format(Math.round(amort1)) + mellan2 + nb_format(Math.round(firstranta)) + mellan2 + nb_format(Math.round(totalforsta)) +mellan2+nb_format(Math.round(restskuld1, true))+ " SEK" + nyrad;

//Manad start////
        if (period == 1) {

            double man=2;
            double ar=1;

            for(int i=2;i<10;i++) {
                double restskuld2=(((kronor)-(payment[i]-aviavgift)*(1-Math.pow((1+(rantasiff)),-i))/(rantasiff))/Math.pow((1+(rantasiff)),-i));
                amor=restskuld1- restskuld2;
                restskuld1=restskuld2;
                ferrari_f40 = (payment[i]-aviavgift)- amor;
                totranta2 += ferrari_f40;
                if ((i%10)==0)man=12;
                if ((i%12)!=0)man=i%12;
                if ((i%12)==0){
//ar=i/12+1;
                    man=12;
                }
                if ((i%12)==1){
                    ar=(i-1)/12+1;
                    man=1;
                }
//mellan1=" ";
                //Formatterings kraffs
                /*mellan2=" ";
                if (man>9) mellan1=" ";
                if (annuitet > 1000 && man>9)
                    mellan1=" ";
                else if (annuitet > 1000)
                    mellan1=" ";
                else if (man >9)
                    mellan1=" ";
                else
                    mellan1=" ";
                document.forms[0].sumtab.value +=ar+":"+man + mellan1 + nb_format(Math.round(amor)) + mellan2 + nb_format(Math.round(ferrari_f40)) + mellan2 + nb_format(Math.round(payment[i]-aviavgift)) +mellan2+nb_format(Math.round(restskuld1), true) + " SEK" + nyrad;*/
            }
            for(int i=10;i<=ant_manad;i++){
                if ((i%10)==0)man=12;
                if ((i%12)!=0)man=i%12;
                if ((i%12)==0){
//ar=i/12+1;
                    man=12;
                }
                if ((i%12)==1){
                    ar=(i-1)/12+1;
                    man=1;
                    //document.forms[0].sumtab.value +=nyrad;
                }
                double restskuld2=(((kronor)-(payment[i]-aviavgift)*(1-Math.pow((1+(rantasiff)),-i))/(rantasiff))/Math.pow((1+(rantasiff)),-i));
                amor=restskuld1- restskuld2;
                restskuld1=restskuld2;
                ferrari_f40 = (payment[i]-0)- amor;
                totranta2 += ferrari_f40;

            }
        }

        return e_ranta(belopp, rant, ant_manad, payment, avgift, totrkost);
    }

}
