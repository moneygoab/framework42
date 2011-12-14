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
        fromCal.set(Calendar.HOUR_OF_DAY, 0);
        fromCal.set(Calendar.MINUTE, 0);
        fromCal.set(Calendar.SECOND, 0);
        fromCal.set(Calendar.MILLISECOND, 0);

        return fromCal.getTime();
    }

    public static Date toLastInMonth(Date dateToTransform) {

        notNull(dateToTransform, "Date to transform can't be null!");
        
        Calendar toCal = new GregorianCalendar();
        toCal.setTime(dateToTransform);
        toCal.set(Calendar.DAY_OF_MONTH, toCal.getActualMaximum(Calendar.DAY_OF_MONTH));
        toCal.set(Calendar.HOUR_OF_DAY, toCal.getActualMaximum(Calendar.HOUR_OF_DAY));
        toCal.set(Calendar.MINUTE, toCal.getActualMaximum(Calendar.MINUTE));
        toCal.set(Calendar.SECOND, toCal.getActualMaximum(Calendar.SECOND));
        toCal.set(Calendar.MILLISECOND, toCal.getActualMaximum(Calendar.MILLISECOND));

        return toCal.getTime();
    }

    public static Date stepBackMonths(Date currentMonth, int monthsToStepBack) {

        notNull(currentMonth, "Current month can't be null!");

        Calendar cal = new GregorianCalendar();
        cal.setTime(currentMonth);
        cal.add(Calendar.MONTH, monthsToStepBack*-1);

        return cal.getTime();
    }

    public static Date toStartOfDay(Date day) {

        notNull(day, "Current day can't be null!");

        Calendar cal = new GregorianCalendar();
        cal.setTime(day);
        cal.add(Calendar.HOUR_OF_DAY, 0);
        cal.add(Calendar.MINUTE, 0);
        cal.add(Calendar.SECOND, 0);

        return cal.getTime();
    }

    public static Date toEndOfDay(Date day) {

        notNull(day, "Current day can't be null!");

        Calendar cal = new GregorianCalendar();
        cal.setTime(day);
        cal.add(Calendar.HOUR_OF_DAY, 23);
        cal.add(Calendar.MINUTE, 59);
        cal.add(Calendar.SECOND, 59);

        return cal.getTime();
    }

    public static Date stepForward(Date currentDate, int numberOfSteps) {

            notNull(currentDate, "Current date can't be null!");

            Calendar cal = new GregorianCalendar();
            cal.setTime(currentDate);
            cal.add(Calendar.MILLISECOND, numberOfSteps);

            return cal.getTime();
        }


}
