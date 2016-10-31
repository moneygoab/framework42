package org.framework42.utils.services;

public interface EAN8ChecksumCalculator {

    public int generateChecksum(String toCalculate);
}
