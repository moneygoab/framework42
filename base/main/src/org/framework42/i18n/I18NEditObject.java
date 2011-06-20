package org.framework42.i18n;

import java.util.Locale;

public class I18NEditObject {

    private final int id;

    private final Locale locale;

    private final I18NType type;

    private final int groupId;

    private final String key;

    private final String value;

    public I18NEditObject(int id, Locale locale, I18NType type, int groupId, String key, String value) {
        this.id = id;
        this.locale = locale;
        this.type = type;
        this.groupId = groupId;
        this.key = key;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public Locale getLocale() {
        return locale;
    }

    public I18NType getType() {
        return type;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
