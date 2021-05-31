package org.framework42.utils.services;

import org.framework42.utils.model.Fill;

import java.math.BigDecimal;
import java.util.Map;

public interface StringManipulator {

    String fillWithCharacter(String originalString, String fillCharacter, int finalLength, boolean fillInFront,boolean shouldCut);

    String fillWithCharacter(String originalString, String fillCharacter, int finalLength, Fill fillType,boolean shouldCut);

    String divideInGroups(String originalString, int groupSize, String inBetweenString);

    String formatGovernmentId(String governmentId);

    String formatInterest(String interest);

    String formatMoney(BigDecimal money);

    String formatMoney(String moneyString);

    String formatMoney(int moneyValue);

    String formatMoney(long moneyValue);

    String formatPercent(float value);

    String[] breakoutUrls(String text);

    String replaceUrls(String text,Map<String,String> urls);

}
