package org.framework42.examples.hello_i18n;

import org.framework42.i18n.I18NDataProvider;

import java.util.HashMap;
import java.util.Locale;

public class HelloI18NDataProvider implements I18NDataProvider {

    @Override
    public HashMap<Locale, HashMap<String, String>> getLocalizedTexts() {

        HashMap<Locale, HashMap<String, String>> hashMap = new HashMap<Locale, HashMap<String, String>>();

        Locale localeEnglish = new Locale("en", "EN");
        Locale localeSwedish = new Locale("sv", "SE");
        hashMap.put(localeEnglish, new HashMap<String, String>());
        hashMap.put(localeSwedish, new HashMap<String, String>());

        hashMap.get(localeEnglish).put("hello_i18n", "Hello I18N!");
        hashMap.get(localeSwedish).put("hello_i18n1", "Hej I18N!");

        return hashMap;
    }

    @Override
    public HashMap<Locale, HashMap<String, String>> getLocalizedURLs() {

        HashMap<Locale, HashMap<String, String>> hashMap = new HashMap<Locale, HashMap<String, String>>();

        Locale localeEnglish = new Locale("en", "EN");
        Locale localeSwedish = new Locale("sv", "SE");
        hashMap.put(localeEnglish, new HashMap<String, String>());
        hashMap.put(localeSwedish, new HashMap<String, String>());

        hashMap.get(localeEnglish).put("hello_i18n", "HelloI18N");
        hashMap.get(localeSwedish).put("hello_i18n", "HejI18N");

        return hashMap;
    }

}
