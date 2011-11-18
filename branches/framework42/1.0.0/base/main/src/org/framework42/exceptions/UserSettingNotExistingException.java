package org.framework42.exceptions;

public class UserSettingNotExistingException extends Exception {

    public UserSettingNotExistingException() {
    }

    public UserSettingNotExistingException(String message) {
        super(message);
    }
    
}
