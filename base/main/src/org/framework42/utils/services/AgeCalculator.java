package org.framework42.utils.services;

import java.util.Calendar;
import java.util.Date;

public interface AgeCalculator {

    public int calculateFromBirthDate(Date birthDate, Calendar now);

}
