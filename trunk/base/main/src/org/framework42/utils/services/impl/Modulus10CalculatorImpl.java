package org.framework42.utils.services.impl;

import org.framework42.utils.services.Modulus10Calculator;

public class Modulus10CalculatorImpl implements Modulus10Calculator {

    public Modulus10CalculatorImpl() {
    }

    @Override
    public int generateChecksum(String toCalculate) {

        int[] weights = generateWeights(toCalculate.length());
        int sum = 0;

        for(int i=0; i<toCalculate.length(); i++) {

            String s = toCalculate.substring(i, i+1);

            sum += calculateProduct(weights[i] * Integer.parseInt(s));
        }

        int checksum = 0;

        if(sum<10) {

            checksum = 10 % sum;

        } else {

            checksum = 10 - Integer.parseInt((sum + "").substring(1));

            if(checksum>9) {
                checksum = 0;
            }
        }

        return checksum;
    }

    private int[] generateWeights(int length) {

        int weightValue = 2;

        int[] weights = new int[length];

        for(int i = length-1; i >= 0; i--) {

            weights[i] = weightValue;

            if(weightValue==2) {
                weightValue = 1;
            } else {
                weightValue= 2;
            }
        }

        return weights;
    }

    private int calculateProduct(int product) {

        if(product>9) {

            return 1 + (product-20+10);

        } else {

            return product;
        }
    }

}
