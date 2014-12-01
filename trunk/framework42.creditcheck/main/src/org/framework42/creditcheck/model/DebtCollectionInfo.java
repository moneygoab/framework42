package org.framework42.creditcheck.model;

public interface DebtCollectionInfo {

    public int getNumberOfACollections();

    public int getSumOfACollections();

    public int getNumberOfECollections();

    public int getSumOfECollections();

    public int getSumOfCollections();

    public boolean isDebtCollected();

}
