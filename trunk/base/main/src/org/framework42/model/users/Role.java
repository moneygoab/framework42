package org.framework42.model.users;

import java.io.Serializable;

public enum Role implements Serializable {

    /* General */
    ADMIN(1, "ADMIN"),LOCKED(3,"LOCKED"), SYSTEM(5, "SYSTEM"), DISMISSED(7, "DISMISSED"),
    UNKNOWN_PERSON(9, "UNKNOWN_PERSON"), MUST_CHANGE_PASSWORD(10, "MUST_CHANGE_PASSWORD"),
    USER_ADMIN(6, "USER_ADMIN"), I18N_ADMIN(12, "I18N_ADMIN"),


    /* Corporate roles */
    CUSTOMER_SERVICE(2, "CUSTOMER_SERVICE"), ACCOUNT_CREATOR(8, "ACCOUNT_CREATOR"),
    DISBURSEMENTS_MAKER(11, "DISBURSEMENTS_MAKER"),

    /* End user roles */
    MEMBER(100, "MEMBER"), VIP_MEMBER(101, "VIP_MEMBER")

    ;

    private final int databaseId;

    private final String guiTextId;

    Role(int databaseId, String guiTextId) {
        this.databaseId = databaseId;
        this.guiTextId = guiTextId;
    }

    public int getId() {
        return databaseId;
    }

    public String getGuiTextId() {
        return guiTextId;
    }

    public static Role getById(int id) {

        for(Role role: Role.values()) {

            if(id == role.getId()) {

                return role;
            }
        }

        throw new IllegalArgumentException();
    }

}
