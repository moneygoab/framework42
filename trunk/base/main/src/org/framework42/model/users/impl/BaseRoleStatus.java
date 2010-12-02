package org.framework42.model.users.impl;

import org.framework42.model.users.RoleStatus;

public enum BaseRoleStatus implements RoleStatus {

    ACTIVE(1, "ROLE_STATUS_ACTIVE"),
    LOCKED(2, "ROLE_STATUS_LOCEKD")
    ;

    private final int databaseId;

    private final String guiTextId;

    private BaseRoleStatus(int databaseId, String guiTextId) {
        this.databaseId = databaseId;
        this.guiTextId = guiTextId;
    }

    @Override
    public int getDatabaseId() {
        return databaseId;
    }

    @Override
    public String getGuiTextId() {
        return guiTextId;
    }

}
