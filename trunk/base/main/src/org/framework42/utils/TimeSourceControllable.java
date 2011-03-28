package org.framework42.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeSourceControllable implements TimeSource {

    private Calendar currentTime;

    public TimeSourceControllable(Calendar currentTime) {

        this.currentTime = currentTime;
    }

    @Override
    public long currentTimeMillis() {

        return currentTime.getTime().getTime();
    }

    @Override
    public Date currentDate() {

        return currentTime.getTime();
    }

    @Override
    public Calendar currentCalendar() {

        return new GregorianCalendar(currentTime.get(Calendar.YEAR), currentTime.get(Calendar.MONTH), currentTime.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void setCurrentTime(Date currentTime) {

        this.currentTime.setTime(currentTime);
    }
}
