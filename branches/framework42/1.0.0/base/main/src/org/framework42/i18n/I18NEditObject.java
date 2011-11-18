package org.framework42.i18n;

import java.util.List;
import java.util.Locale;

public class I18NEditObject {

    private final int id;

    private final List<Locale> localeList;

    private final I18NType type;

    private final int groupId;

    private final String key;

    private final String value;

    public I18NEditObject(int id, List<Locale> localeList, I18NType type, int groupId, String key, String value) {
        this.id = id;
        this.localeList = localeList;
        this.type = type;
        this.groupId = groupId;
        this.key = key;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public List<Locale> getLocaleList() {
        return localeList;
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
