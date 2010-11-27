package org.framework42.model;

public enum Gender {

    MALE(2, "MALE"), FEMALE(1, "FEMALE"), INAPPLICABLE(3, "INAPPLICABLE"), UNKNOWN(0, "UNKNOWN");

    private final int databaseId;
    private final String guiTextID;

    Gender(int databaseId, String guiTextID) {
        this.databaseId = databaseId;
        this.guiTextID = guiTextID;
    }

    public int getDatabaseId() {
        return databaseId;
    }

    public String getGuiTextID() {
        return guiTextID;
    }

    public static Gender findFromDatabaseValue(int genderId) {

        for (Gender gender : Gender.values()) {

            if (gender.getDatabaseId() == genderId) {

                return gender;

            }

        }

        throw new IllegalArgumentException("No gender exists with id " + genderId);

    }

    @Override
    public String toString() {
        return "Gender{" +
                "databaseId=" + databaseId +
                ", guiTextID='" + guiTextID + '\'' +
                '}';
    }
}
