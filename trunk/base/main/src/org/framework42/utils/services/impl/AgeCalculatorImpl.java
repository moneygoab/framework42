package org.framework42.utils.services.impl;

import org.framework42.utils.services.AgeCalculator;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.framework42.utils.NullChecker.notNull;

public enum AgeCalculatorImpl implements AgeCalculator {

    INSTANCE;

    @Override
    public int calculateFromBirthDate(Date birthDate) {

        notNull(birthDate, "Birth date can't be null!");

        long mainBirthTimestamp = birthDate.getTime();
        long nowTimestamp = System.currentTimeMillis();

        GregorianCalendar difference = new GregorianCalendar();
        difference.setTimeInMillis(nowTimestamp-mainBirthTimestamp);

        return difference.get(GregorianCalendar.YEAR);

    }
    
}
