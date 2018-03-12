package org.framework42.creditcheck.model;

public interface DebtCollectionInfo {

    int getNumberOfACollections();

    int getSumOfACollections();

    int getNumberOfECollections();

    int getSumOfECollections();

    int getSumOfCollections();

    boolean isDebtCollected();

}
