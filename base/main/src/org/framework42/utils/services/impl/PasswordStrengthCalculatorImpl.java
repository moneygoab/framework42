package org.framework42.utils.services.impl;

import org.framework42.model.PasswordStrength;
import org.framework42.utils.services.PasswordStrengthCalculator;

public class PasswordStrengthCalculatorImpl implements PasswordStrengthCalculator {

    private final String[] bannedWordsList = new String[] {
            "123456", "password", "12345678", "lifehack", "qwerty", "abc123", "111111",
            "monkey", "consumer", "12345", "0", "letmein", "trustno1", "dragon", "1234567", "baseball", "superman", "iloveyou",
            "gizmodo", "sunshine", "1234", "princess", "starwars", "whatever", "shadow", "cheese", "123123", "nintendo", "football",
            "computer", "f---you", "654321", "blahblah", "passwOrd", "master", "soccer", "michael", "666666", "jennifer", "gawker",
            "Password", "jordan", "pokemon", "michelle", "killer", "pepper", "welcome", "batman", "kotaku"
    };
    
    @Override
    public PasswordStrength calculateStrength(String password) {

        if(isBannedWord(password)) {

            return PasswordStrength.NONE_EXISTING;
        }

        return PasswordStrength.MEDIUM;
    }

    private boolean isBannedWord(String password) {

        for(String bannedWord: bannedWordsList) {

            if(bannedWord.equalsIgnoreCase(password)) {

                return true;
            }
        }

        return false;
    }


}
