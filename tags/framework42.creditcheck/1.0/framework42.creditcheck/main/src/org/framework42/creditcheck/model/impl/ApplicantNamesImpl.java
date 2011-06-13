package org.framework42.creditcheck.model.impl;

import org.framework42.creditcheck.model.ApplicantNames;

import static org.framework42.utils.NullChecker.notNull;

public class ApplicantNamesImpl implements ApplicantNames {

    private final String firstName;

    private final String surname;

    private final String fullName;

    public ApplicantNamesImpl(String firstName, String surname, String fullName) {

        this.firstName = notNull(firstName, "First name can't be null!");
        this.surname = notNull(surname, "Surname can't be null!");
        this.fullName = notNull(fullName, "Full name can't be null!");
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String getFullName() {
        return fullName;
    }
}
