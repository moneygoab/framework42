package org.framework42.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.framework42.utils.NullChecker.notNull;
import static java.util.Calendar.*;

public class DateUtil {

    public static Date toDayInMonth(Date dateToTransform, int day) {

        notNull(dateToTransform, "Date to transform can't be null!");

        Calendar fromCal = new GregorianCalendar();
        fromCal.setTime(dateToTransform);
        fromCal.set(DAY_OF_MONTH, day);

        return fromCal.getTime();
    }

    public static Date toFirstInMonth(Date dateToTransform) {

        notNull(dateToTransform, "Date to transform can't be null!");

        Calendar fromCal = new GregorianCalendar();
        fromCal.setTime(dateToTransform);
        fromCal.set(DAY_OF_MONTH, 1);
        fromCal.set(HOUR_OF_DAY, 0);
        fromCal.set(MINUTE, 0);
        fromCal.set(SECOND, 0);
        fromCal.set(MILLISECOND, 0);

        return fromCal.getTime();
    }

    public static Date toLastInMonth(Date dateToTransform) {

        notNull(dateToTransform, "Date to transform can't be null!");

        Calendar toCal = new GregorianCalendar();
        toCal.setTime(dateToTransform);
        toCal.set(DAY_OF_MONTH, toCal.getActualMaximum(DAY_OF_MONTH));
        toCal.set(HOUR_OF_DAY, toCal.getActualMaximum(HOUR_OF_DAY));
        toCal.set(MINUTE, toCal.getActualMaximum(MINUTE));
        toCal.set(SECOND, toCal.getActualMaximum(SECOND));
        toCal.set(MILLISECOND, toCal.getActualMaximum(MILLISECOND));

        return toCal.getTime();
    }

    public static Date toBeginningOfDay(Date dateToTransform) {

        notNull(dateToTransform, "Date to transform can't be null!");

        Calendar toCal = new GregorianCalendar();
        toCal.setTime(dateToTransform);
        toCal.set(HOUR_OF_DAY, toCal.getActualMinimum(HOUR_OF_DAY));
        toCal.set(MINUTE, toCal.getActualMinimum(MINUTE));
        toCal.set(SECOND, toCal.getActualMinimum(SECOND));
        toCal.set(MILLISECOND, toCal.getActualMinimum(MILLISECOND));

        return toCal.getTime();
    }

    public static Date toEndOfDay(Date dateToTransform) {

        notNull(dateToTransform, "Date to transform can't be null!");

        Calendar toCal = new GregorianCalendar();
        toCal.setTime(dateToTransform);
        toCal.set(HOUR_OF_DAY, toCal.getActualMaximum(HOUR_OF_DAY));
        toCal.set(MINUTE, toCal.getActualMaximum(MINUTE));
        toCal.set(SECOND, toCal.getActualMaximum(SECOND));
        toCal.set(MILLISECOND, toCal.getActualMaximum(MILLISECOND));

        return toCal.getTime();
    }

    public static Date stepBackMonths(Date currentMonth, int monthsToStepBack) {

        notNull(currentMonth, "Current month can't be null!");

        Calendar cal = new GregorianCalendar();
        cal.setTime(currentMonth);
        cal.add(MONTH, monthsToStepBack*-1);

        return cal.getTime();
    }

    public static Date stepForward(Date currentDate, int numberOfSteps) {

        notNull(currentDate, "Current date can't be null!");

        Calendar cal = new GregorianCalendar();
        cal.setTime(currentDate);
        cal.add(MILLISECOND, numberOfSteps);

        return cal.getTime();
    }

}
