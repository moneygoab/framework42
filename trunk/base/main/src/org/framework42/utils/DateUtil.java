package org.framework42.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.framework42.utils.NullChecker.notNull;
import static java.util.Calendar.*;

public class DateUtil {

    public static Date toDayInMonth(Date dateToTransform, int day, boolean toStartOfDay) {

        notNull(dateToTransform, "Date to transform can't be null!");

        Calendar fromCal = new GregorianCalendar();
        fromCal.setTime(dateToTransform);
        fromCal.set(DAY_OF_MONTH, day);

        if(toStartOfDay) {

            fromCal.set(HOUR_OF_DAY, fromCal.getActualMinimum(HOUR_OF_DAY));
            fromCal.set(MINUTE, fromCal.getActualMinimum(MINUTE));
            fromCal.set(SECOND, fromCal.getActualMinimum(SECOND));
            fromCal.set(MILLISECOND, fromCal.getActualMinimum(MILLISECOND));

        } else {

            fromCal.set(HOUR_OF_DAY, fromCal.getActualMaximum(HOUR_OF_DAY));
            fromCal.set(MINUTE, fromCal.getActualMaximum(MINUTE));
            fromCal.set(SECOND, fromCal.getActualMaximum(SECOND));
            fromCal.set(MILLISECOND, fromCal.getActualMaximum(MILLISECOND));
        }

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

    public static Date toFirstInYear(Date dateToTransform) {

        notNull(dateToTransform, "Date to transform can't be null!");

        Calendar fromCal = new GregorianCalendar();
        fromCal.setTime(dateToTransform);
        fromCal.set(MONTH, JANUARY);
        fromCal.set(DAY_OF_MONTH, 1);
        fromCal.set(HOUR_OF_DAY, 0);
        fromCal.set(MINUTE, 0);
        fromCal.set(SECOND, 0);
        fromCal.set(MILLISECOND, 0);

        return fromCal.getTime();
    }

    public static Date toLastInYear(Date dateToTransform) {

        notNull(dateToTransform, "Date to transform can't be null!");

        Calendar toCal = new GregorianCalendar();
        toCal.setTime(dateToTransform);
        toCal.set(MONTH, DECEMBER);
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

    public static Date stepBackDays(Date currentDate, int daysToStepBack) {

        notNull(currentDate, "Current date can't be null!");

        Calendar cal = new GregorianCalendar();
        cal.setTime(currentDate);
        cal.add(DAY_OF_MONTH, daysToStepBack*-1);

        return cal.getTime();
    }

    public static Date stepBackMonths(Date currentMonth, int monthsToStepBack) {

        notNull(currentMonth, "Current month can't be null!");

        Calendar cal = new GregorianCalendar();
        cal.setTime(currentMonth);
        cal.add(MONTH, monthsToStepBack*-1);

        return cal.getTime();
    }

    public static Date stepForwardMonths(Date currentMonth, int monthsToStepForward) {

        notNull(currentMonth, "Current month can't be null!");

        Calendar cal = new GregorianCalendar();
        cal.setTime(currentMonth);
        cal.add(MONTH, monthsToStepForward);

        return cal.getTime();
    }

    public static Date stepForwardDays(Date currentDate, int numberOfDays) {

        notNull(currentDate, "Current date can't be null!");

        Calendar cal = new GregorianCalendar();
        cal.setTime(currentDate);
        cal.add(DAY_OF_MONTH, numberOfDays);

        return cal.getTime();
    }

    public static Date stepBack(Date currentDate, int numberOfSteps) {

        notNull(currentDate, "Current date can't be null!");

        Calendar cal = new GregorianCalendar();
        cal.setTime(currentDate);
        cal.add(MILLISECOND, numberOfSteps*-1);

        return cal.getTime();
    }

    public static Date stepForward(Date currentDate, int numberOfSteps) {

        notNull(currentDate, "Current date can't be null!");

        Calendar cal = new GregorianCalendar();
        cal.setTime(currentDate);
        cal.add(MILLISECOND, numberOfSteps);

        return cal.getTime();
    }

    public static Date getFromGovernmentId(String governmentId) {

        if(governmentId==null || governmentId.length()<1) {

            return null;
        }

        int year = Integer.parseInt(governmentId.substring(0, 2));
        int month = Integer.parseInt(governmentId.substring(2, 4));
        int day = Integer.parseInt(governmentId.substring(4, 6));

        if( (2000+year) <= getInstance().get(YEAR)) {

            year += 2000;

        } else {

            year += 1900;
        }

        Calendar cal = new GregorianCalendar(year, month-1, day);

        return cal.getTime();
    }

    public static boolean isSameDay(Date date1, Date date2) {

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        return (cal1.get(Calendar.YEAR)==cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH)==cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH)==cal2.get(Calendar.DAY_OF_MONTH));
    }

}
