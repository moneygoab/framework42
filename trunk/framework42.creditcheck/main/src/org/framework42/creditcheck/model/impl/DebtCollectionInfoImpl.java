package org.framework42.creditcheck.model.impl;

import org.framework42.creditcheck.model.DebtCollectionInfo;

public class DebtCollectionInfoImpl implements DebtCollectionInfo {

    private final int numberOfACollections;

    private final int sumOfACollections;

    private final int numberOfECollections;

    private final int sumOfECollections;

    private final int sumOfCollections;

    public DebtCollectionInfoImpl(int numberOfACollections, int sumOfACollections, int numberOfECollections, int sumOfECollections, int sumOfCollections) {
        this.numberOfACollections = numberOfACollections;
        this.sumOfACollections = sumOfACollections;
        this.numberOfECollections = numberOfECollections;
        this.sumOfECollections = sumOfECollections;
        this.sumOfCollections = sumOfCollections;
    }

    @Override
    public int getNumberOfACollections() {
        return numberOfACollections;
    }

    @Override
    public int getSumOfACollections() {
        return sumOfACollections;
    }

    @Override
    public int getNumberOfECollections() {
        return numberOfECollections;
    }

    @Override
    public int getSumOfECollections() {
        return sumOfECollections;
    }

    @Override
    public int getSumOfCollections() {
        return sumOfCollections;
    }

    @Override
    public boolean isDebtCollected() {

        return (numberOfACollections>0 || sumOfACollections>0 || numberOfECollections>0 || sumOfECollections>0 || sumOfCollections>0);
    }
}
