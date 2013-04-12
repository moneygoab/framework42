package org.framework42.i18n;

import java.util.HashMap;
import java.util.Locale;

public class SimpleI18NMapDataProvider implements I18NDataProvider {

    private final HashMap<Locale,HashMap<String,String>> localizedTexts;

    private final HashMap<Locale,HashMap<String,String>> localizedURLs;

    public SimpleI18NMapDataProvider() {

        HashMap<String,String> empty = new HashMap<String,String>();

        HashMap<Locale,HashMap<String,String>> localisedTexts = new HashMap<Locale,HashMap<String,String>>();
        localisedTexts.put(new Locale("sv", "SE"), empty);

        HashMap<Locale,HashMap<String,String>> localisedURLs = new HashMap<Locale,HashMap<String,String>>();
        localisedURLs.put(new Locale("sv", "SE"), empty);

        this.localizedTexts = localisedTexts;
        this.localizedURLs = localisedURLs;
    }

    public SimpleI18NMapDataProvider(HashMap<Locale,HashMap<String,String>> localizedTexts, HashMap<Locale,HashMap<String,String>> localizedURLs) {

        this.localizedTexts = localizedTexts;
        this.localizedURLs = localizedURLs;
    }

    @Override
    public HashMap<Locale, HashMap<String, String>> getLocalizedTexts() {
        return localizedTexts;
    }

    @Override
    public HashMap<Locale, HashMap<String, String>> getLocalizedURLs() {
        return localizedURLs;
    }
}
