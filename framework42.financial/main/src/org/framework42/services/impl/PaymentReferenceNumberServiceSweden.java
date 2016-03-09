package org.framework42.services.impl;

import org.apache.log4j.Logger;
import org.framework42.services.PaymentReferenceNumberService;
import org.framework42.utils.services.Modulus10Calculator;
import org.framework42.utils.services.StringManipulator;
import org.framework42.utils.services.impl.Modulus10CalculatorImpl;
import org.framework42.utils.services.impl.StringManipulatorImpl;

public class PaymentReferenceNumberServiceSweden implements PaymentReferenceNumberService {

    private final StringManipulator stringManipulator;

    private final Modulus10Calculator modulus10Calculator;

    public PaymentReferenceNumberServiceSweden() {

        stringManipulator = new StringManipulatorImpl();

        modulus10Calculator = new Modulus10CalculatorImpl();
    }

    public static void main(String[] args) {

        for(int i=0;i<1000000000;i++) {

            //System.out.println(new PaymentReferenceNumberServiceSweden().generate(12));

            String s = new PaymentReferenceNumberServiceSweden().generate(12);

            if(s.length()!=12) {

                System.out.println(s);
            }

            if(i%1000000==0) {

                System.out.println(i);
            }
        }

        //System.out.println(new Modulus10CalculatorImpl().generateChecksum("89999789292"));
        //System.out.println(new Modulus10CalculatorImpl().generateChecksum("999999999999"));
    }

    @Override
    public String generate(int length) {

        boolean goon = true;

        String number = "";

        while(goon) {

                String random = "";

                for(int i=0;i<length-2;i++) {

                    random += ""+(int)(Math.random()*10f);
                }

                random = stringManipulator.fillWithCharacter(random, "0", length-2, true);

                String lengthDigit = (random.length()+2)+"";

                if(lengthDigit.length()>1) {

                    lengthDigit = lengthDigit.substring(lengthDigit.length()-1);
                }

                random += lengthDigit;

                int checksum = modulus10Calculator.generateChecksum(random);

                random = random+checksum;

                number = random;

                if (number.length() == length && checksum>=0) {

                    goon = false;

                } else {

                    Logger.getLogger("org.framework42.services").info("handled error in PaymentReferenceNumberServiceSweden.generate checksum: "+checksum+" ocr: "+number);
                }
        }

        return number;
    }



}
