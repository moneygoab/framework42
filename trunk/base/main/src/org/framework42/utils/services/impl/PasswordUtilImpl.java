package org.framework42.utils.services.impl;

import org.framework42.utils.services.PasswordUtil;

public enum PasswordUtilImpl implements PasswordUtil {

    INSTANCE;

    private final String[] easyDeck = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","0","1","2","3","4","5","6","7","8","9"};

    private PasswordUtilImpl() {
    }

    public String generateRandomPassword() {

        String password = "";

        for(int i = 0; i<6;i++){
            int charPos = (int)(Math.random()*easyDeck.length);
            password += easyDeck[charPos];
        }

        return password;
    }
}
