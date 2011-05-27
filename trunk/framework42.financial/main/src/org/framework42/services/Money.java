package org.framework42.services;

import java.math.BigDecimal;
import java.util.Currency;

public interface Money {

    public BigDecimal getAmount();

    public Currency getCurrency();

    public String getFormattedAmount();

}
