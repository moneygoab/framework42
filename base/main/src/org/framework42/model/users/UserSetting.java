package org.framework42.model.users;

import org.framework42.exceptions.UserSettingNotExistingException;

import java.io.Serializable;

public enum UserSetting implements Serializable {

    LANGUAGE(1), COUNTRY(2),
    STYLE_TEXT_EDITOR(30),
    ALLOW_COMMENTS(40),
    ALLOW_APPLAUSE(41)
    ;

    private final int id;

    UserSetting(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static UserSetting getFromId(int databaseId) throws UserSettingNotExistingException {

        for (UserSetting userSetting : UserSetting.values()) {
            if (userSetting.getId() == databaseId) {
                return userSetting;
            }
        }

        throw new UserSettingNotExistingException();

    }

}
