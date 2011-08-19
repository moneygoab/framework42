package org.framework42.services.impl;

import org.framework42.services.CurrencyFormatter;
import org.framework42.services.Money;

import java.math.BigDecimal;
import java.util.Currency;

import static org.framework42.utils.NullChecker.notNull;

public class MoneyImpl implements Money {

    private final BigDecimal amount;

    private final Currency currency;

    private final CurrencyFormatter currencyFormatter;

    public MoneyImpl(BigDecimal amount, Currency currency) {

        this.amount = notNull(amount, "Amount can't be null!");
        this.currency = notNull(currency, "Currency can't be null!");
        currencyFormatter = CurrencyFormatterImpl.getByCurrency(currency);
    }

    @Override
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String getFormattedAmount() {
        return currencyFormatter.format(amount);
    }

    @Override
    public String toString() {

        return currencyFormatter.format(amount)+" "+currency.getCurrencyCode();
    }
}
