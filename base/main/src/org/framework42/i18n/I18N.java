package org.framework42.i18n;

import java.util.HashMap;
import java.util.Locale;

public enum I18N {

    INSTANCE;

    public HashMap<Locale, HashMap<String, String>> hashMap;
    public HashMap<Locale, HashMap<String, String>> hashMapURL;

    private boolean initialized;

    I18N() {

        initialized = false;

        hashMap = new HashMap<Locale, HashMap<String, String>>();
        hashMapURL = new HashMap<Locale, HashMap<String, String>>();

    }

    public boolean isInitialized() {
        return initialized;
    }

    public void setUpI18N(I18NDataProvider dataProvider) {

        hashMap = dataProvider.getLocalizedTexts();
        hashMapURL = dataProvider.getLocalizedURLs();

        this.initialized = true;

    }

    public String get(String key, Locale locale, String... insertData) {

        Object obj = hashMap.get(locale).get(key);

        if (obj != null) {
            String str = obj.toString();
            for (int i = 0; i < insertData.length; i++) {
                str = str.replaceAll("T\\[" + (i) + "\\]", insertData[i]);
            }
            return str;
        } else {
            return "NOT MAPPED!";
        }

    }

    public String getURL(String key, Locale locale) {

        Object obj = hashMapURL.get(locale).get(key);

        if (obj != null) {
            return obj.toString();
        } else {
            return "index.html";
        }

    }

}

