package org.framework42.utils.services;

public interface StringManipulator {

    public String fillWithCharacter(String originalString, String fillCharacter, int finalLength, boolean fillInFront);

    public String divideInGroups(String originalString, int groupSize, String inBetweenString);

}