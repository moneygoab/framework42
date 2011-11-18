package org.framework42.model.users;

import java.io.Serializable;

public enum Gender implements Serializable {

    MALE(2, "MALE"), FEMALE(1, "FEMALE"), INAPPLICABLE(3, "INAPPLICABLE"), UNKNOWN(0, "UNKNOWN");

    private final int id;
    private final String guiTextID;

    Gender(int id, String guiTextID) {
        this.id = id;
        this.guiTextID = guiTextID;
    }

    public int getId() {
        return id;
    }

    public String getGuiTextID() {
        return guiTextID;
    }

    public static Gender findFromDatabaseValue(int genderId) {

        for (Gender gender : Gender.values()) {

            if (gender.getId() == genderId) {

                return gender;

            }

        }

        throw new IllegalArgumentException("No gender exists with getId " + genderId);

    }

    @Override
    public String toString() {
        return "Gender{" +
                "getId=" + id +
                ", guiTextID='" + guiTextID + '\'' +
                '}';
    }
}
