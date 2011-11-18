package org.framework42.utils.services.impl;

import org.framework42.utils.services.AgeCalculator;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.framework42.utils.NullChecker.notNull;

public enum AgeCalculatorImpl implements AgeCalculator {

    INSTANCE;

    @Override
    public int calculateFromBirthDate(Date birthDate, Calendar now) {

        notNull(birthDate, "Birth date can't be null!");

        Calendar birthCal = new GregorianCalendar();
        birthCal.setTime(birthDate);

        if(birthCal.get(GregorianCalendar.YEAR) == 9999) {
            return 0;
        }

        now.add(GregorianCalendar.YEAR, birthCal.get(GregorianCalendar.YEAR)*-1);
        now.add(GregorianCalendar.MONTH, birthCal.get(GregorianCalendar.MONTH)*-1);
        now.add(GregorianCalendar.DAY_OF_MONTH, birthCal.get(GregorianCalendar.DAY_OF_MONTH)*-1);

        return now.get(GregorianCalendar.YEAR);

    }
    
}
