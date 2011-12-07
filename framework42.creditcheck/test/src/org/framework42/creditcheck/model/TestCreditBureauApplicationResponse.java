package org.framework42.creditcheck.model;

import org.framework42.creditcheck.model.impl.SimpleCreditBureauApplicationResponse;
import org.framework42.model.Country;
import org.framework42.services.impl.MoneyImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

public class TestCreditBureauApplicationResponse {

    @Test
    public void instantiate() {

        new SimpleCreditBureauApplicationResponse(
                CreditDecision.APPROVED,
                new MoneyImpl(BigDecimal.ONE, Currency.getInstance(Country.SWEDEN.getCurrencyCode())),
                "",
                "",
                0,
                new MoneyImpl(BigDecimal.ONE, Currency.getInstance(Country.SWEDEN.getCurrencyCode())),
                0,
                new MoneyImpl(BigDecimal.ZERO, Currency.getInstance(Country.SWEDEN.getCurrencyCode())),
                ""
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void instantiateNullDecision() {

        new SimpleCreditBureauApplicationResponse(
                null,
                new MoneyImpl(BigDecimal.ONE, Currency.getInstance(Country.SWEDEN.getCurrencyCode())),
                "",
                "",
                0,
                new MoneyImpl(BigDecimal.ONE, Currency.getInstance(Country.SWEDEN.getCurrencyCode())),
                0,
                new MoneyImpl(BigDecimal.ZERO, Currency.getInstance(Country.SWEDEN.getCurrencyCode())),
                ""
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void instantiateNullRecommendedAmount() {

        new SimpleCreditBureauApplicationResponse(
                CreditDecision.APPROVED,
                null,
                "",
                "",
                0,
                new MoneyImpl(BigDecimal.ONE, Currency.getInstance(Country.SWEDEN.getCurrencyCode())),
                0,
                new MoneyImpl(BigDecimal.ZERO, Currency.getInstance(Country.SWEDEN.getCurrencyCode())),
                ""
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void instantiateNullResponseAsHtml() {

        new SimpleCreditBureauApplicationResponse(
                CreditDecision.APPROVED,
                new MoneyImpl(BigDecimal.ONE, Currency.getInstance(Country.SWEDEN.getCurrencyCode())),
                null,
                "",
                0,
                new MoneyImpl(BigDecimal.ONE, Currency.getInstance(Country.SWEDEN.getCurrencyCode())),
                0,
                new MoneyImpl(BigDecimal.ZERO, Currency.getInstance(Country.SWEDEN.getCurrencyCode())),
                ""
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void instantiateNegativeNumberOfCreditChecks() {

        new SimpleCreditBureauApplicationResponse(
                CreditDecision.APPROVED,
                new MoneyImpl(BigDecimal.ONE, Currency.getInstance(Country.SWEDEN.getCurrencyCode())),
                "",
                "",
                -1,
                new MoneyImpl(BigDecimal.ONE, Currency.getInstance(Country.SWEDEN.getCurrencyCode())),
                0,
                new MoneyImpl(BigDecimal.ZERO, Currency.getInstance(Country.SWEDEN.getCurrencyCode())),
                ""
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void instantiateNullIncome() {

        new SimpleCreditBureauApplicationResponse(
                CreditDecision.APPROVED,
                new MoneyImpl(BigDecimal.ONE, Currency.getInstance(Country.SWEDEN.getCurrencyCode())),
                "",
                "",
                0,
                null,
                0,
                new MoneyImpl(BigDecimal.ZERO, Currency.getInstance(Country.SWEDEN.getCurrencyCode())),
                ""
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void instantiateNegativeNumberOfDebtCollections() {

        new SimpleCreditBureauApplicationResponse(
                CreditDecision.APPROVED,
                new MoneyImpl(BigDecimal.ONE, Currency.getInstance(Country.SWEDEN.getCurrencyCode())),
                "",
                "",
                0,
                new MoneyImpl(BigDecimal.ONE, Currency.getInstance(Country.SWEDEN.getCurrencyCode())),
                -1,
                new MoneyImpl(BigDecimal.ZERO, Currency.getInstance(Country.SWEDEN.getCurrencyCode())),
                ""
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void instantiateNullDebtCollectionSum() {

        new SimpleCreditBureauApplicationResponse(
                CreditDecision.APPROVED,
                new MoneyImpl(BigDecimal.ONE, Currency.getInstance(Country.SWEDEN.getCurrencyCode())),
                "",
                "",
                0,
                new MoneyImpl(BigDecimal.ONE, Currency.getInstance(Country.SWEDEN.getCurrencyCode())),
                0,
                null,
                ""
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void instantiateNullReasonCodes() {

        new SimpleCreditBureauApplicationResponse(
                CreditDecision.APPROVED,
                new MoneyImpl(BigDecimal.ONE, Currency.getInstance(Country.SWEDEN.getCurrencyCode())),
                "",
                "",
                0,
                new MoneyImpl(BigDecimal.ONE, Currency.getInstance(Country.SWEDEN.getCurrencyCode())),
                0,
                new MoneyImpl(BigDecimal.ZERO, Currency.getInstance(Country.SWEDEN.getCurrencyCode())),
                null
        );
    }

}
