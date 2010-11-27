package org.framework42.utils.services.impl;

import org.apache.log4j.Logger;
import org.framework42.utils.services.PasswordGenerator;

public enum PasswordGeneratorImpl implements PasswordGenerator {

    BASE_DECK(new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}),

    CASE_BASE_DECK(new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"});

    private final Logger logger = Logger.getLogger("org.framework42");

    private final String[] deck;

    private PasswordGeneratorImpl(String[] deck) {

        this.deck = deck;

    }

    public String generateRandomPassword(int passwordLength) {

        verifyPasswordLength(passwordLength);

        return generatePasswordFromDeck(passwordLength);

    }

    private void verifyPasswordLength(int passwordLength) {

        if (passwordLength < 1) {
            logger.error("Password length must be 1 or longer, your value of " + passwordLength + " is illegal.");
            throw new IllegalArgumentException("Password length must be 1 or longer, your value of " + passwordLength + " is illegal.");
        }

    }

    private String generatePasswordFromDeck(int passwordLength) {

        String password = "";

        for (int i = 0; i < passwordLength; i++) {
            int charPos = (int) (Math.random() * deck.length);
            password += deck[charPos];
        }

        logger.debug("Password generated with value: " + password);
        return password;

    }

}

