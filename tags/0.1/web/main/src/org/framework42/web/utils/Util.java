package org.framework42.web.utils;

public class Util {

    public static String spacer(int spaces){

        StringBuilder html = new StringBuilder();

        for(int i=0;i<spaces;i++){
            html.append("&nbsp;");
        }

        return html.toString();

    }

    public static String tab(int tabs){

        StringBuilder html = new StringBuilder();

        for(int i=0;i<tabs;i++){
            html.append("\t");
        }

        return html.toString();

    }

    public static String shorter(String text, int maxLength){

        if(text.length()>= maxLength){

            text = text.substring(0,maxLength-3)+"...";

        }
        
        return text;

    }

}
