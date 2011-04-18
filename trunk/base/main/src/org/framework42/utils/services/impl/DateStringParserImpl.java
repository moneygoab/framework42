package org.framework42.utils.services.impl;

import org.framework42.utils.services.DateStringParser;

import java.util.Date;
import java.util.GregorianCalendar;

public class DateStringParserImpl implements DateStringParser {

    @Override
    public Date parseString(String dateString) {

        String[] splitStrings = dateString.split("-");

        return new GregorianCalendar(Integer.parseInt(splitStrings[0]), Integer.parseInt(splitStrings[1])-1, Integer.parseInt(splitStrings[2])).getTime();
        
    }

}
