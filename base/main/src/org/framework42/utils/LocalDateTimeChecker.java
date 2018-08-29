package org.framework42.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeChecker {

    public static LocalDateTime isBefore(LocalDateTime dateObject, LocalDateTime compareDateObject) {

        if(dateObject.isBefore(compareDateObject)) {

            return dateObject;
        }

        throw new IllegalArgumentException("LocalDateTime object "+dateObject.format(DateTimeFormatter.ISO_DATE_TIME)+" should be before "+compareDateObject.format(DateTimeFormatter.ISO_DATE_TIME));
    }

    public static LocalDateTime isAfter(LocalDateTime dateObject, LocalDateTime compareDateObject) {

        if(dateObject.isAfter(compareDateObject)) {

            return dateObject;
        }

        throw new IllegalArgumentException("LocalDateTime object "+dateObject.format(DateTimeFormatter.ISO_DATE_TIME)+" should be before "+compareDateObject.format(DateTimeFormatter.ISO_DATE_TIME));
    }

}
