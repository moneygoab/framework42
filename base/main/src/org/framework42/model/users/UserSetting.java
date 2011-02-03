package org.framework42.model.users;

import org.framework42.exceptions.UserSettingNotExistingException;

public enum UserSetting {

    LANGUAGE(1), COUNTRY(2);

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
