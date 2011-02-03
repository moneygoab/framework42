package org.framework42.web.utils;

/**
 * A web util class that provides convenience methods for doing some
 * */
public class Util {

    /**
     * This method inserts the number of spaces that is sent in. In the form of &nbsp tags.
     * @param spaces        The number of spaces to create.
     * @return A string with the created spaces.
     * */
    public static String spacer(int spaces){

        StringBuilder html = new StringBuilder();

        for(int i=0;i<spaces;i++){
            html.append("&nbsp;");
        }

        return html.toString();

    }

    /**
     * This method inserts the number of tabs that is sent in. In the form of \ escaped t.
     * @param tabs          The number of tabs to create.
     * @return A string with the created tabs.
     * */
    public static String tab(int tabs){

        StringBuilder html = new StringBuilder();

        for(int i=0;i<tabs;i++){
            html.append("\t");
        }

        return html.toString();

    }

    /**
     * This method shortens a text and adds ... at the end.
     * @param text          The text to shorten.
     * @param maxLength     The number of characters the text should be shortened to.
     * @return The string that is shortened and has ... added to the end.
     * */
    public static String shorten(String text, int maxLength){

        if(text.length()>= maxLength){

            text = text.substring(0,maxLength-3)+"...";

        }
        
        return text;

    }

}
