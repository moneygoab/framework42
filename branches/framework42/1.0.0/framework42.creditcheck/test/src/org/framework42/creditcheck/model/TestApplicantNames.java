package org.framework42.creditcheck.model;

import org.framework42.creditcheck.model.impl.ApplicantNamesImpl;
import org.junit.Test;

public class TestApplicantNames {

    @Test
    public void testApplicantNames() {

        new ApplicantNamesImpl(
                "Test",
                "Testsson",
                "Test Testsson"
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testApplicantNamesNullFirstName() {

        new ApplicantNamesImpl(
                null,
                "Testsson",
                "Test Testsson"
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testApplicantNamesNullSurname() {

        new ApplicantNamesImpl(
                "Test",
                null,
                "Test Testsson"
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testApplicantNamesNullFullName() {

        new ApplicantNamesImpl(
                "Test",
                "Testsson",
                null
        );
    }

}
