package org.framework42.utils;

import java.util.Calendar;
import java.util.Date;

public interface TimeSource {

    public long currentTimeMillis();

    public Date currentDate();

    public Calendar currentCalendar();

    public void setCurrentTime(Date currentTime);

}
