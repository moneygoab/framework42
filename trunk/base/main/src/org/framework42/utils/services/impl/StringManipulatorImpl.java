package org.framework42.utils.services.impl;

import org.framework42.utils.services.StringManipulator;

public class StringManipulatorImpl implements StringManipulator {

    @Override
    public String fillWithCharacter(String originalString, String fillCharacter, int finalLength, boolean fillInFront) {

        String newString = originalString;

        while(newString.length()<finalLength) {

            if(fillInFront) {

                newString = fillCharacter + newString;

            } else {

                newString = newString + fillCharacter; 
            }
        }

        return newString;
    }

    @Override
    public String divideInGroups(String originalString, int groupSize, String inBetweenString) {

        String returnString = "";

        for(int i=0; i < originalString.length(); i+=groupSize) {

            if(i+groupSize < originalString.length()) {

                returnString += originalString.substring(i, i+groupSize);

            } else {

                returnString += originalString.substring(i);
            }

            returnString += inBetweenString;
        }

        return returnString;
    }

    @Override
    public String formatGovernmentId(String governmentId) {

        if(governmentId.length() == 10) {

            return governmentId.substring(0, governmentId.length()-4)+"-"+governmentId.substring(governmentId.length()-4);

        } else {

            return governmentId;
        }

    }

    @Override
    public String formatMoney(String moneyString) {

        String returnString;

        returnString = moneyString.split("\\.")[0];
        returnString = returnString.split(",")[0];

        if(returnString.length() > 3) {

            String tempString = "";

            for(int i= returnString.length(); i>0; i-=3) {

                int startId = i-3;
                if(startId<0) {
                    startId = 0;
                }

                tempString = returnString.substring(startId, i) + " "+tempString;
            }

            returnString = tempString;
        }

        return  returnString;
    }
}

