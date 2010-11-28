package org.framework42.i18n;

import java.util.HashMap;
import java.util.Locale;

/**
 * A data provider for the I18N facility in of the framework.
 * You should implement this interface to create the I18N data to use in the application.
 */
public interface I18NDataProvider {

    /**
     * Here you retrieve the localized texts from your persistence, for example an database.
     *
     * @return Returns the localized texts for all the locales that you handle.
     */
    public HashMap<Locale, HashMap<String, String>> getLocalizedTexts();

    /**
     * Here you retrieve the localized URLs from your persistence, for example an database.
     *
     * @return Returns the localized URLs for all the locales that you handle.
     */
    public HashMap<Locale, HashMap<String, String>> getLocalizedURLs();

}
