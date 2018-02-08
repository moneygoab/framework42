package org.framework42.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static java.util.Calendar.YEAR;
import static java.util.Calendar.getInstance;

public class LocalDateUtil {

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

}
