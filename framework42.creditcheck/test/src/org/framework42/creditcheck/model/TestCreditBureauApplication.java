package org.framework42.creditcheck.model;

import org.framework42.creditcheck.model.impl.ApplicantImpl;
import org.framework42.creditcheck.model.impl.ApplicantNamesImpl;
import org.framework42.creditcheck.model.impl.CreditBureauApplicationImpl;
import org.framework42.model.Country;
import org.framework42.model.InformationProvider;
import org.framework42.model.PostalCodeFormat;
import org.framework42.model.impl.PostalCodeImpl;
import org.framework42.model.impl.SimpleSecureAddressImpl;
import org.framework42.services.impl.MoneyImpl;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

public class TestCreditBureauApplication {

    private Applicant applicant;

    @Before
    public void setup() {

        applicant = new ApplicantImpl(
                0,
                "",
                BigDecimal.ONE,
                new Date(),
                new ApplicantNamesImpl("","",""),
                new SimpleSecureAddressImpl(0, "", "", "", new PostalCodeImpl(PostalCodeFormat.NUMERIC_NNNNN, "12345"),
                "",
                Country.SWEDEN,
                InformationProvider.CUSTOMER),
                new ArrayList<ApplicantContactMethod>(), 0
        );
    }

    @Test
    public void testInstantiate() {

        new CreditBureauApplicationImpl(
                0,
                ApplicationType.NEW,
                ApplicationStatus.INFORMATION_GATHERING_PROCESS,
                new Date(),
                new MoneyImpl(BigDecimal.TEN, Currency.getInstance(new Locale("sv", "SE"))),
                ApplicationChannel.INTERNET,
                applicant,
                applicant,
                0
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateNegativeId() {

        new CreditBureauApplicationImpl(
                -1,
                ApplicationType.NEW,
                ApplicationStatus.INFORMATION_GATHERING_PROCESS,
                new Date(),
                new MoneyImpl(BigDecimal.TEN, Currency.getInstance(new Locale("sv", "SE"))),
                ApplicationChannel.INTERNET,
                applicant,
                applicant,
                0
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateNullApplicationStatus() {

        new CreditBureauApplicationImpl(
                0,
                ApplicationType.NEW,
                null,
                new Date(),
                new MoneyImpl(BigDecimal.TEN, Currency.getInstance(new Locale("sv", "SE"))),
                ApplicationChannel.INTERNET,
                applicant,
                applicant,
                0
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateNullDate() {

        new CreditBureauApplicationImpl(
                0,
                ApplicationType.NEW,
                ApplicationStatus.INFORMATION_GATHERING_PROCESS,
                null,
                new MoneyImpl(BigDecimal.TEN, Currency.getInstance(new Locale("sv", "SE"))),
                ApplicationChannel.INTERNET,
                applicant,
                applicant,
                0
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateNullAppliedAmount() {

        new CreditBureauApplicationImpl(
                0,
                ApplicationType.NEW,
                ApplicationStatus.INFORMATION_GATHERING_PROCESS,
                new Date(),
                null,
                ApplicationChannel.INTERNET,
                applicant,
                applicant,
                0
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateNullApplicationChannel() {

        new CreditBureauApplicationImpl(
                0,
                ApplicationType.NEW,
                ApplicationStatus.INFORMATION_GATHERING_PROCESS,
                new Date(),
                new MoneyImpl(BigDecimal.TEN, Currency.getInstance(new Locale("sv", "SE"))),
                null,
                applicant,
                applicant,
                0
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateNullMainApplicant() {

        new CreditBureauApplicationImpl(
                0,
                ApplicationType.NEW,
                ApplicationStatus.INFORMATION_GATHERING_PROCESS,
                new Date(),
                new MoneyImpl(BigDecimal.TEN, Currency.getInstance(new Locale("sv", "SE"))),
                ApplicationChannel.INTERNET,
                null,
                applicant,
                0
        );
    }

}
