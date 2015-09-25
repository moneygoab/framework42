package org.framework42.services.impl;

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

        System.out.println(new PaymentReferenceNumberServiceSweden().generate(24));
    }

    @Override
    public String generate(int length) {

        String random = "";

        for(int i=0;i<length-2;i++) {

            random += ""+(int)(Math.random()*10f);
        }

        //random = Long.toString((long) (Math.random() * Long.parseLong(seed)) + 1 * length - 2);

        random = stringManipulator.fillWithCharacter(random, "0", length-2, true);

        String lengthDigit = (random.length()+2)+"";

        if(lengthDigit.length()>1) {

            lengthDigit = lengthDigit.substring(lengthDigit.length()-1);
        }

        random += lengthDigit;

        random = random+modulus10Calculator.generateChecksum(random);

        return random;
    }

}
