package org.framework42.creditcheck.model;

import org.framework42.model.Country;

import java.time.LocalDate;

public interface InternalCreditCheck {

    String getGovernmentId();

    LocalDate getBirthDate();

    String getFirstName();

    String getLastName();

    String getFullName();

    String getAddressee();

    String getCareOf();

    String getAddress();

    String getPostalCode();

    String getCity();

    Country getCountry();

    int getScore();

    boolean isDebtCollected();

    DebtCollectionInfo getDebtCollectionInfo();

    int getDeclaredIncome();

    int getNegativeCapital();

    int getTaxationYear();

}
