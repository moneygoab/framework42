package org.framework42.utils.services.impl;

import org.framework42.utils.services.Modulus11Calculator;

public class Modulus11CalculatorImpl implements Modulus11Calculator {

    @Override
    public int generateChecksum(String toCalculate) {

        int[] weights = generateWeights(toCalculate.length());
        int sum = 0;

        for(int i=0; i<toCalculate.length(); i++) {

            String s = toCalculate.substring(i, i+1);

            sum += weights[i] * Integer.parseInt(s);

        }

        int rest = sum % 11;

        if(rest != 0) {

            rest = 11-rest;
        }

        return rest;
    }

    private int[] generateWeights(int length) {

        int weightValue = 2;

        int[] weights = new int[length];

        for(int i = length-1; i >= 0; i--) {

            weights[i] = weightValue;

            weightValue++;

            if(weightValue>10) {
                weightValue = 1;
            }
        }

        return weights;
    }

}
