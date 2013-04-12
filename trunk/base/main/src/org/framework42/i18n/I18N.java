package org.framework42.i18n;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Locale;

/**
 * A service class that handles I18N translation of texts and URLs.
 */
public enum I18N {

    INSTANCE;

    public HashMap<Locale, HashMap<String, String>> hashMap;
    public HashMap<Locale, HashMap<String, String>> hashMapURL;

    public Locale defaultLocale = new Locale("sv", "SE");

    private boolean initialized;

    private final Logger logger = Logger.getLogger("org.framework42");

    /**
     * The default constructor only initializes with empty data, you must provide it with a data provider before use
     * by calling setUpI18N.
     */
    I18N() {

        initialized = false;

        hashMap = new HashMap<Locale, HashMap<String, String>>();
        hashMapURL = new HashMap<Locale, HashMap<String, String>>();

    }

    /**
     * Checks whatever the I18N is initialized or not. setUpI18N must be called before it returns true.
     *
     * @return boolean that is true if it's ready for use and false if not.
     */
    public boolean isInitialized() {
        return initialized;
    }

    /**
     * Initializes the data with the sent in data provider. This method must be called before use.
     *
     * @param dataProvider The data provider that will be setting up the data.
     */
    public void setUpI18N(I18NDataProvider dataProvider) {

        hashMap = dataProvider.getLocalizedTexts();
        hashMapURL = dataProvider.getLocalizedURLs();

        this.initialized = true;

    }

    /**
     * This method returns the text mapped to the sent in key in the sent in locale language. This method must be
     * initialized before calling this method, that is done by sending in an data provider in setUpI18N.
     *
     * @param key        The key value that should be searched for.
     * @param locale     The locale to get a translation to.
     * @param insertData Any dynamic data that should be injected into the returned String.
     * @return String that contains the translated text with any dynamic data injected.
     */
    public String get(String key, Locale locale, String... insertData) {

        checkInstantiation(locale);

        String mappedText = hashMap.get(locale).get(key);

        if (mappedText != null) {
            for (int i = 0; i < insertData.length; i++) {
                mappedText = mappedText.replaceAll("T\\[" + (i) + "\\]", insertData[i]);
            }
            return mappedText;
        } else {
            logger.warn("I18N key: " + key + " not mapped");
            return "NOT MAPPED!";
        }

    }

    /**
     * This method returns the URL mapped to the sent in key in the sent in locale language. This method must be
     * initialized before calling this method, that is done by sending in an data provider in setUpI18N.
     *
     * @param key    The key value that should be searched for.
     * @param locale The locale to get a translation to.
     * @param insertData Any dynamic data that should be injected into the returned String.
     * @return String that contains the translated URL.
     */
    public String getURL(String key, Locale locale, String... insertData) {

        checkInstantiation(locale);

        String mappedURL = hashMapURL.get(locale).get(key);

        if (mappedURL != null) {
            for (int i = 0; i < insertData.length; i++) {
                mappedURL = mappedURL.replaceAll("T\\[" + (i) + "\\]", insertData[i]);
            }
            return mappedURL;
        } else {
            logger.error("I18N URL key: " + key + " not mapped");
            return "index.html";
        }

    }

    private void checkInstantiation(Locale locale) {

        if (!isInitialized()) {
            String errorMess = "I18N data not yet instantiated for locale " + locale.getDisplayName() + "! " +
                    "You must call the method setUpI18N and send a data provider before use.";
            logger.fatal(errorMess);
            throw new InstantiationError(errorMess);
        } else if(hashMap.get(locale) == null) {
            String errorMess = "No I18N data found for locale " + locale.getDisplayName() + "! " +
                    "Have you added this locale to your persistence?";
            logger.fatal(errorMess);
            throw new InstantiationError(errorMess);
        }

    }

}

