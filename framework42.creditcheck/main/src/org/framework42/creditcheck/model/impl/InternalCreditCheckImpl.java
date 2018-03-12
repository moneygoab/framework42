package org.framework42.creditcheck.model.impl;

import org.framework42.creditcheck.model.DebtCollectionInfo;
import org.framework42.creditcheck.model.InternalCreditCheck;
import org.framework42.model.Country;

import java.time.LocalDate;

public class InternalCreditCheckImpl implements InternalCreditCheck {

    private final String governmentId;

    private final LocalDate birthDate;

    private final String firstName;

    private final String lastName;

    private final String fullName;

    private final String addressee;

    private final String careOf;

    private final String address;

    private final String postalCode;

    private final String city;

    private final Country country;

    private final int score;

    private final DebtCollectionInfo debtCollectionInfo;

    private final int declaredIncome;

    private final int negativeCapital;

    private final int taxationYear;

    public InternalCreditCheckImpl(String governmentId, LocalDate birthDate, String firstName, String lastName, String fullName, String addressee, String careOf, String address, String postalCode, String city, Country country, int score, DebtCollectionInfo debtCollectionInfo, int declaredIncome, int negativeCapital, int taxationYear) {

        this.governmentId = governmentId;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.addressee = addressee;
        this.careOf = careOf;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.score = score;
        this.debtCollectionInfo = debtCollectionInfo;
        this.declaredIncome = declaredIncome;
        this.negativeCapital = negativeCapital;
        this.taxationYear = taxationYear;
    }

    @Override
    public String getGovernmentId() {
        return governmentId;
    }

    @Override
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public String getAddressee() {
        return addressee;
    }

    @Override
    public String getCareOf() {
        return careOf;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public Country getCountry() {
        return country;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public boolean isDebtCollected() {
        return debtCollectionInfo.isDebtCollected();
    }

    @Override
    public DebtCollectionInfo getDebtCollectionInfo() {
        return debtCollectionInfo;
    }

    @Override
    public int getDeclaredIncome() {
        return declaredIncome;
    }

    @Override
    public int getNegativeCapital() {
        return negativeCapital;
    }

    @Override
    public int getTaxationYear() {
        return taxationYear;
    }
}
