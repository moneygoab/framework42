package org.framework42.creditcheck.model;

import org.framework42.creditcheck.model.impl.ApplicantImpl;
import org.framework42.creditcheck.model.impl.ApplicantNamesImpl;
import org.framework42.model.Country;
import org.framework42.address.model.InformationProvider;
import org.framework42.address.model.PostalCodeFormat;
import org.framework42.address.model.impl.PostalCodeImpl;
import org.framework42.address.model.impl.SimpleSecureAddressImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TestApplicant {

    @Test
    public void testInstantiateApplicant() {

        new ApplicantImpl(
                0,
                "5105091218",
                new BigDecimal(0.2f),
                new GregorianCalendar(1951, 4, 9).getTime(),
                new ApplicantNamesImpl("Test", "Testsson", "Test Testsson"),
                new SimpleSecureAddressImpl(0, "Test Testsson", "", "Testgatan 10", new PostalCodeImpl(PostalCodeFormat.NUMERIC_NNNNN, "12345"), "Teststaden", Country.SWEDEN, InformationProvider.POPULATION_REGISTERS),
                new ArrayList<ApplicantContactMethod>(),
                350000
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateNegativeId() {

        new ApplicantImpl(
                -1,
                "5105091218",
                new BigDecimal(0.2f),
                new GregorianCalendar(1951, 4, 9).getTime(),
                new ApplicantNamesImpl("Test", "Testsson", "Test Testsson"),
                new SimpleSecureAddressImpl(0, "Test Testsson", "", "Testgatan 10", new PostalCodeImpl(PostalCodeFormat.NUMERIC_NNNNN, "12345"), "Teststaden", Country.SWEDEN, InformationProvider.POPULATION_REGISTERS),
                new ArrayList<ApplicantContactMethod>(),
                350000
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateApplicantNullGovernmentId() {

        new ApplicantImpl(
                0,
                null,
                new BigDecimal(0.2f),
                new GregorianCalendar(1951, 4, 9).getTime(),
                new ApplicantNamesImpl("Test", "Testsson", "Test Testsson"),
                new SimpleSecureAddressImpl(0, "Test Testsson", "", "Testgatan 10", new PostalCodeImpl(PostalCodeFormat.NUMERIC_NNNNN, "12345"), "Teststaden", Country.SWEDEN, InformationProvider.POPULATION_REGISTERS),
                new ArrayList<ApplicantContactMethod>(),
                350000
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateApplicantNullRiskLevel() {

        new ApplicantImpl(
                0,
                "5105091218",
                null,
                new GregorianCalendar(1951, 4, 9).getTime(),
                new ApplicantNamesImpl("Test", "Testsson", "Test Testsson"),
                new SimpleSecureAddressImpl(0, "Test Testsson", "", "Testgatan 10", new PostalCodeImpl(PostalCodeFormat.NUMERIC_NNNNN, "12345"), "Teststaden", Country.SWEDEN, InformationProvider.POPULATION_REGISTERS),
                new ArrayList<ApplicantContactMethod>(),
                350000
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateApplicantNullBirthDate() {

        new ApplicantImpl(
                0,
                "5105091218",
                new BigDecimal(0.2f),
                null,
                new ApplicantNamesImpl("Test", "Testsson", "Test Testsson"),
                new SimpleSecureAddressImpl(0, "Test Testsson", "", "Testgatan 10", new PostalCodeImpl(PostalCodeFormat.NUMERIC_NNNNN, "12345"), "Teststaden", Country.SWEDEN, InformationProvider.POPULATION_REGISTERS),
                new ArrayList<ApplicantContactMethod>(),
                350000
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateApplicantNullNames() {

        new ApplicantImpl(
                0,
                "5105091218",
                new BigDecimal(0.2f),
                new GregorianCalendar(1951, 4, 9).getTime(),
                null,
                new SimpleSecureAddressImpl(0, "Test Testsson", "", "Testgatan 10", new PostalCodeImpl(PostalCodeFormat.NUMERIC_NNNNN, "12345"), "Teststaden", Country.SWEDEN, InformationProvider.POPULATION_REGISTERS),
                new ArrayList<ApplicantContactMethod>(),
                350000
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateApplicantNullAddress() {

        new ApplicantImpl(
                0,
                "5105091218",
                new BigDecimal(0.2f),
                new GregorianCalendar(1951, 4, 9).getTime(),
                new ApplicantNamesImpl("Test", "Testsson", "Test Testsson"),
                null,
                new ArrayList<ApplicantContactMethod>(),
                350000
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateApplicantNullContactMethods() {

        new ApplicantImpl(
                0,
                "5105091218",
                new BigDecimal(0.2f),
                new GregorianCalendar(1951, 4, 9).getTime(),
                new ApplicantNamesImpl("Test", "Testsson", "Test Testsson"),
                new SimpleSecureAddressImpl(0, "Test Testsson", "", "Testgatan 10", new PostalCodeImpl(PostalCodeFormat.NUMERIC_NNNNN, "12345"), "Teststaden", Country.SWEDEN, InformationProvider.POPULATION_REGISTERS),
                null,
                350000
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateApplicantNegativeIncome() {

        new ApplicantImpl(
                0,
                "5105091218",
                new BigDecimal(0.2f),
                new GregorianCalendar(1951, 4, 9).getTime(),
                new ApplicantNamesImpl("Test", "Testsson", "Test Testsson"),
                new SimpleSecureAddressImpl(0, "Test Testsson", "", "Testgatan 10", new PostalCodeImpl(PostalCodeFormat.NUMERIC_NNNNN, "12345"), "Teststaden", Country.SWEDEN, InformationProvider.POPULATION_REGISTERS),
                new ArrayList<ApplicantContactMethod>(),
                -1
        );
    }

}
