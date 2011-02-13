package org.framework42.model.users.impl;

import org.framework42.model.users.Role;

public enum BaseRole implements Role {

    ADMIN(1, "ADMIN"), MEMBER(2, "MEMBER"), LOCKED(3,"LOCKED"), SYSTEM(5, "SYSTEM"),
    VIP_MEMBER(11, "VIP_MEMBER"),
    USER_ADMIN(6, "USER_ADMIN"), DISMISSED(7, "DISMISSED"),
    UNKNOWN_PERSON(9, "UNKNOWN_PERSON"), MUST_CHANGE_PASSWORD(10, "MUST_CHANGE_PASSWORD");

    private final int databaseId;

    private final String guiTextId;

    BaseRole(int databaseId, String guiTextId) {
        this.databaseId = databaseId;
        this.guiTextId = guiTextId;
    }

    @Override
    public int getId() {
        return databaseId;
    }

    @Override
    public String getGuiTextId() {
        return guiTextId;
    }
}
