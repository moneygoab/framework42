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
}
