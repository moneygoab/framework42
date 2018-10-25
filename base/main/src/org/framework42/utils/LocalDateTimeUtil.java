package org.framework42.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

public class LocalDateTimeUtil {

    public static Date getAsDate(LocalDateTime dateTime) {

        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime getFromDate(Date date) {

        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static LocalDateTime toBeginningOfDay(LocalDateTime dateTime) {

        return dateTime.withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    public static LocalDateTime toEndOfDay(LocalDateTime dateTime) {

        return dateTime.withHour(23).withMinute(59).withSecond(59);
    }

    public static LocalDateTime toBeginningOfMonth(LocalDateTime dateTime) {

        return dateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    public static LocalDateTime toEndOfMonth(LocalDateTime dateTime) {

        return dateTime.withDayOfMonth(dateTime.toLocalDate().lengthOfMonth()).withHour(23).withMinute(59).withSecond(59);
    }

}
