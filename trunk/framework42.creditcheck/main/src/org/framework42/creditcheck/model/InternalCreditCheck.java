package org.framework42.creditcheck.model;

import org.framework42.model.Country;
import org.framework42.model.TrustedAddress;

import java.util.Date;

public interface InternalCreditCheck {

    public String getGovernmentId();

    public Date getBirthDate();

    public String getFirstName();

    public String getLastName();

    public String getFullName();

    public String getAddressee();

    public String getCareOf();

    public String getAddress();

    public String getPostalCode();

    public String getCity();

    public Country getCountry();

    public int getScore();

    public boolean isDebtCollected();

    public DebtCollectionInfo getDebtCollectionInfo();

    public int getDeclaredIncome();

    public int getNegativeCapital();

    public int getTaxationYear();

}
