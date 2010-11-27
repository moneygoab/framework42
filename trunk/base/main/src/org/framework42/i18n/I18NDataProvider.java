package org.framework42.i18n;

import java.util.HashMap;
import java.util.Locale;

public interface I18NDataProvider {

    public HashMap<Locale, HashMap<String, String>> getLocalizedTexts();

    public HashMap<Locale, HashMap<String, String>> getLocalizedURLs();

}
