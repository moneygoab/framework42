package org.framework42.utils.services.impl;

import org.framework42.utils.services.EAN8ChecksumCalculator;

public class EAN8ChecksumCalculatorImpl implements EAN8ChecksumCalculator {

    public EAN8ChecksumCalculatorImpl() {
    }

    @Override
    public int generateChecksum(String toCalculate) {

        if(toCalculate.length()!=7) {

            throw new IllegalArgumentException("A EAN-8 barcode must be of 7 digits length before the checksum is added!");
        }

        boolean three = true;

        int sum = 0;

        for(int i=0;i<7;i++) {

            if(three) {

                sum += Integer.parseInt(toCalculate.substring(i, i+1))*3;

            } else {

                sum += Integer.parseInt(toCalculate.substring(i, i+1));
            }

            three = !three;
        }

        int checksum = 10-(sum%10);

        if(checksum==10) {
            checksum = 0;
        }

        return checksum;
    }
}
