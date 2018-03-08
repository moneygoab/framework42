package org.framework42.utils;

import java.time.LocalDate;

public class LocalDateUtil {

    public static LocalDate getFromGovernmentId(String governmentId) {
        if (governmentId != null && governmentId.length() >= 1) {
            int year = Integer.parseInt(governmentId.substring(0, 2));
            int month = Integer.parseInt(governmentId.substring(2, 4));
            int day = Integer.parseInt(governmentId.substring(4, 6));
            if (2000 + year <= LocalDate.now().getYear()) {
                year += 2000;
            } else {
                year += 1900;
            }

            return LocalDate.of(year, month, day);
        } else {
            return null;
        }
    }

}
