package org.framework42.utils.services;

import org.framework42.utils.model.Fill;

import java.math.BigDecimal;
import java.util.Map;

public interface StringManipulator {

    public String fillWithCharacter(String originalString, String fillCharacter, int finalLength, boolean fillInFront,boolean shouldCut);

    public String fillWithCharacter(String originalString, String fillCharacter, int finalLength, Fill fillType,boolean shouldCut);

    public String divideInGroups(String originalString, int groupSize, String inBetweenString);

    public String formatGovernmentId(String governmentId);

    public String formatInterest(String interest);

    public String formatMoney(BigDecimal money);

    public String formatMoney(String moneyString);

    public String formatMoney(int moneyValue);

    public String formatPercent(float value);

    public String[] breakoutUrls(String text);

    public String replaceUrls(String text,Map<String,String> urls);

}
