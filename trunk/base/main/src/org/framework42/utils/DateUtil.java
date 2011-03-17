package org.framework42.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.framework42.utils.NullChecker.notNull;

public class DateUtil {

    public static Date toDayInMonth(Date dateToTransform, int day) {

        notNull(dateToTransform, "Date to transform can't be null!");

        Calendar fromCal = new GregorianCalendar();
        fromCal.setTime(dateToTransform);
        fromCal.set(Calendar.DAY_OF_MONTH, day);

        return fromCal.getTime();
    }

    public static Date toFirstInMonth(Date dateToTransform) {

        notNull(dateToTransform, "Date to transform can't be null!");

        Calendar fromCal = new GregorianCalendar();
        fromCal.setTime(dateToTransform);
        fromCal.set(Calendar.DAY_OF_MONTH, 1);

        return fromCal.getTime();
    }

    public static Date toLastInMonth(Date dateToTransform) {

        notNull(dateToTransform, "Date to transform can't be null!");
        
        Calendar toCal = new GregorianCalendar();
        toCal.setTime(dateToTransform);
        toCal.set(Calendar.DAY_OF_MONTH, toCal.getActualMaximum(Calendar.DAY_OF_MONTH));

        return toCal.getTime();
    }

    public static Date stepBackMonths(Date currentMonth, int monthsToStepBack) {

        notNull(currentMonth, "Current month can't be null!");

        Calendar cal = new GregorianCalendar();
        cal.setTime(currentMonth);
        cal.add(Calendar.MONTH, monthsToStepBack*-1);

        return cal.getTime();
    }

}
