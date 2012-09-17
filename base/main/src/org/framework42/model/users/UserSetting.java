package org.framework42.model.users;

import java.io.Serializable;

public enum UserSetting implements Serializable {

    LANGUAGE(1), COUNTRY(2),
    STYLE_TEXT_EDITOR(30),
    ALLOW_COMMENTS(40),
    ALLOW_APPLAUSE(41),
    DO_NOT_SHOW_IN_TEXTS_READ_LOG(42),
    SHOW_BOOKMARKS(43),
    SHOW_ONLY_MEMBERS_IN_READ_LOG(44),
    SHOW_EMAIL(45),
    SHOW_NEWS_LINKS(46),
    START_PAGE(47),
    SHOW_DIARY(48),
    SHOW_RELATED_TEXTS(49),
    SHOW_TYPE_OF_TEXTS_MY_PAGE(50)
    ;

    private final int id;

    UserSetting(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static UserSetting getFromId(int databaseId) {

        for (UserSetting userSetting : UserSetting.values()) {
            if (userSetting.getId() == databaseId) {
                return userSetting;
            }
        }

        throw new IllegalArgumentException();

    }

    public static UserSetting getFromId(String databaseId) {

        return getFromId(Integer.parseInt(databaseId));
    }

}
