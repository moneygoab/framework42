package org.framework42.model;

public enum Language {

    SWEDISH(1, "LANGUAGE_SWEDISH", "sv", "swe", "swe"),
    OTHER(2, "LANGUAGE_OTHER", "", "", ""),
    ENGLISH(3, "LANGUAGE_ENGLISH", "en", "eng", "eng");

    private final int databaseId;
    private final String guiTextId;

    private final String ISO_639_1_CODE;
    private final String ISO_639_2_CODE;
    private final String ISO_639_3_CODE;

    private Language(int databaseId, String guiTextId, String ISO_639_1_CODE, String ISO_639_2_CODE, String ISO_639_3_CODE) {
        this.databaseId = databaseId;
        this.guiTextId = guiTextId;
        this.ISO_639_1_CODE = ISO_639_1_CODE;
        this.ISO_639_2_CODE = ISO_639_2_CODE;
        this.ISO_639_3_CODE = ISO_639_3_CODE;
    }

    public int getDatabaseId() {
        return databaseId;
    }

    public String getGuiTextId() {
        return guiTextId;
    }

    public String getISO_639_1_CODE() {
        return ISO_639_1_CODE;
    }

    public String getISO_639_2_CODE() {
        return ISO_639_2_CODE;
    }

    public String getISO_639_3_CODE() {
        return ISO_639_3_CODE;
    }

    public static Language getLanguageByDatabaseID(int databaseId) {

        for (Language language : Language.values()) {

            if (language.getDatabaseId() == databaseId) {
                return language;
            }

        }

        throw new IllegalArgumentException("No language exists with id " + databaseId);

    }

}
