package org.framework42.utils;

import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeSourceSystem implements TimeSource {

    private static final Logger logger = Logger.getLogger("org.framework42");

    @Override
    public long currentTimeMillis() {

        return System.currentTimeMillis();
    }

    @Override
    public Date currentDate() {

        return new Date();
    }

    @Override
    public Calendar currentCalendar() {

        return new GregorianCalendar();
    }

    @Override
    public void setCurrentTime(Date currentTime) {

        logger.info("Can't set time in a system time source.");
    }

}
