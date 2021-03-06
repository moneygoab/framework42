package org.framework42.utils.services.impl;

import org.framework42.utils.model.Fill;
import org.framework42.utils.services.StringManipulator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StringManipulatorImpl implements StringManipulator {

    @Override
    public String fillWithCharacter(String originalString, String fillCharacter, int finalLength, boolean fillInFront, boolean shouldCut) {

        if(fillCharacter==null||fillCharacter.length()==0) {
            throw new IllegalArgumentException("Parameter fill character is invalid value is '"+fillCharacter+"'");
        }

        String newString = originalString;

        while(newString.length()<finalLength) {

            if(fillInFront) {

                newString = fillCharacter + newString;

            } else {

                newString = newString + fillCharacter; 
            }
        }

        if(shouldCut && newString.length() > finalLength){
            newString = newString.substring(0,finalLength);
        }

        return newString;
    }

    @Override
    public String fillWithCharacter(String originalString, String fillCharacter, int finalLength, Fill fillType, boolean shouldCut) {

        if(fillCharacter==null||fillCharacter.length()==0) {
            throw new IllegalArgumentException("Parameter fill character is invalid value is '"+fillCharacter+"'");
        }

        String newString = originalString;

        while(newString.length()<finalLength) {

            if(fillType == Fill.FRONT) {

                newString = fillCharacter + newString;

            } else {

                newString = newString + fillCharacter;
            }
        }

        if(shouldCut && newString.length() > finalLength){
            newString = newString.substring(0,finalLength);
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
    public String formatInterest(String interestString) {

        String returnString;

        returnString = interestString.split("\\.")[0];
        returnString = returnString.split(",")[0];

        if(interestString.split("\\.").length>1 ) {

            if(interestString.split("\\.")[1].length()>2) {

                returnString += ","+interestString.split("\\.")[1].substring(0, 2);

            } else {

                returnString += ","+interestString.split("\\.")[1];
            }
        }

        returnString += " %";

        if("0E-7 %".equalsIgnoreCase(returnString)) {

            returnString = "-";
        }

        return returnString;
    }

    @Override
    public String formatMoney(BigDecimal money) {

        String moneyString = money.setScale(2, RoundingMode.UP).toString();

        return formatMoney(moneyString);
    }

    @Override
    public String formatMoney(String moneyString) {

        String returnString;

        returnString = moneyString.split("\\.")[0];
        returnString = returnString.split(",")[0];

        if(moneyString.startsWith("-")) {
            returnString = returnString.split("-")[1];
        }

        if( (moneyString.split("\\.").length>1 && !moneyString.split("\\.")[1].equals("00")) || (moneyString.split("\\,").length>1 && !moneyString.split("\\,")[1].equals("00")) ) {
            returnString += ","+moneyString.split("\\.")[1];
        }

        if(returnString.length() > 3) {

            String tempString = "";

            for(int i= returnString.length(); i>0; i-=3) {

                int startId = i-3;
                if(startId<0) {
                    startId = 0;
                }

                tempString = returnString.substring(startId, i) + " "+tempString;
            }

            if(tempString.contains(" ,")) {

                String tString = "";

                for(String a: tempString.split(" ,")) {

                    tString = tString+a+",";
                }

                tString=tString.substring(0, tString.length()-1);

                tempString = tString;
            }

            returnString = tempString;
        }

        if(moneyString.startsWith("-")) {
            returnString = "-"+returnString;
        }

        return  returnString.trim();
    }

    @Override
    public String formatMoney(int moneyValue) {

        return formatMoney(moneyValue+"");
    }

    @Override
    public String formatMoney(long moneyValue) {

        return formatMoney(moneyValue+"");
    }

    @Override
    public String formatPercent(float value) {

        Double d = Math.round(value*100.0) / 100.0;

        return (d+"").replaceAll("\\.", ",")+" %";
    }

    @Override
    public String[] breakoutUrls(String breakoutText) {
        String[] texts = breakoutText.split("\\s+");
        ArrayList<String> urls = new ArrayList<>();

        for(String text:texts){
            if(text.startsWith("http")){
                urls.add(text);
            }
        }

        return urls.toArray(new String[urls.size()]);
    }

    @Override
    public String replaceUrls(String text,Map<String, String> urls) {
        for(Map.Entry<String,String> url:urls.entrySet()){
            text = text.replace(url.getKey(),url.getValue());
        }
        return text;
    }

}

