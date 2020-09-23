package org.framework42.model.users;

import org.framework42.model.Country;

import java.io.Serializable;
import java.util.Locale;

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

        throw new IllegalArgumentException("No gender exists with getLevel " + genderId);

    }

    public static Gender findFromGovernmentId(Country country, String governmentId) {

        if(country==Country.SWEDEN) {

            Gender gender = Gender.MALE;
            if(Long.parseLong(governmentId.substring(6, 9))%2 == 0) {
                gender = Gender.FEMALE;
            }

            return gender;
        }

        return UNKNOWN;
    }

    @Override
    public String toString() {
        return "Gender{" +
                "getLevel=" + id +
                ", guiTextID='" + guiTextID + '\'' +
                '}';
    }
}
