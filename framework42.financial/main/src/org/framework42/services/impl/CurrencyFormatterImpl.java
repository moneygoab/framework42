package org.framework42.services.impl;

import org.framework42.services.CurrencyFormatter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

import static org.framework42.utils.NotNegativeChecker.notNegative;
import static org.framework42.utils.NullChecker.notNull;

public enum CurrencyFormatterImpl implements CurrencyFormatter {

    SWEDISH(Currency.getInstance(new Locale("sv","SE")), ",", 2, NumberFormat.getCurrencyInstance(new Locale("sv", "SE")));

    private final Currency currency;

    private final String dividerSymbol;

    private final int numberOfDecimalDigits;

    private final NumberFormat longNumberFormat;

    CurrencyFormatterImpl(Currency currency, String dividerSymbol, int numberOfDecimalDigits, NumberFormat longNumberFormat) {

        this.currency = notNull(currency, "Currency can't be null!");

        this.dividerSymbol = notNull(dividerSymbol, "Divider symbol can't be null!");

        this.numberOfDecimalDigits = notNegative(numberOfDecimalDigits, "Number of decimal digits can't be null!");

        this.longNumberFormat = notNull(longNumberFormat, "Long number format can't be null!");
    }

    @Override
    public String format(BigDecimal amount) {

        return amount.round(getMathContext(amount)).toPlainString().replaceAll("\\.", dividerSymbol);
    }

    @Override
    public String formatLong(BigDecimal amount, Currency currency, Locale locale) {

        return longNumberFormat.format(amount.round(getMathContext(amount)).floatValue());
    }

    public static CurrencyFormatter getByCurrency(Currency currency) {

        for(CurrencyFormatterImpl formatter: CurrencyFormatterImpl.values()) {

            if(formatter.currency.equals(currency) ) {

                return formatter;
            }
        }

        throw new IllegalArgumentException("No currency formatter for currency "+currency.getCurrencyCode()+" exists!");
    }

    private MathContext getMathContext(BigDecimal amount) {

        int numberOfWholeDigits = (amount.intValue()+"").length();

        return  new MathContext(numberOfDecimalDigits+numberOfWholeDigits, RoundingMode.HALF_DOWN);
    }

}
