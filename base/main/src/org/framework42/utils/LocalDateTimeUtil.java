package org.framework42.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class LocalDateTimeUtil {

    public static Date getAsDate(LocalDateTime dateTime) {

        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime getFromDate(Date date) {

        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

}
