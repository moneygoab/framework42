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
}
