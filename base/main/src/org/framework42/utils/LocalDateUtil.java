package org.framework42.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class LocalDateUtil {

    public static LocalDate getFromGovernmentId(String governmentId) {

        if(governmentId==null || governmentId.length()<1) {

            return null;
        }

        int year = Integer.parseInt(governmentId.substring(0, 2));
        int month = Integer.parseInt(governmentId.substring(2, 4));
        int day = Integer.parseInt(governmentId.substring(4, 6));

        if( (2000+year) <= LocalDate.now().getYear()) {

            year += 2000;

        } else {

            year += 1900;
        }

        return LocalDate.of(year, month, day);
    }

    public static Date getAsDate(LocalDate date) {

        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate getFromDate(Date date) {

        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDate toBeginningOfMonth(LocalDate date) {

        return date.withDayOfMonth(1);
    }

    public static LocalDate toEndOfMonth(LocalDate date) {

        return date.withDayOfMonth(date.lengthOfMonth());
    }

}
