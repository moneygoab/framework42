package org.framework42.utils.services;

public interface StringManipulator {

    public String fillWithCharacter(String originalString, String fillCharacter, int finalLength, boolean fillInFront);

    public String divideInGroups(String originalString, int groupSize, String inBetweenString);

    public String formatGovernmentId(String governmentId);

    public String formatInterest(String interest);

    public String formatMoney(String moneyString);

    public String formatPercent(float value);

}
