package org.framework42.services;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

public interface CurrencyFormatter {

    public String format(BigDecimal bigDecimal);

    public String formatLong(BigDecimal bigDecimal, Currency currency, Locale locale);

}
