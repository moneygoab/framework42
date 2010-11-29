package org.framework42.model.users.impl;

import org.framework42.model.users.RoleStatus;

public class BaseRoleStatus implements RoleStatus {

    private final int databaseId;

    private final String guiTextId;

    public BaseRoleStatus(int databaseId, String guiTextId) {
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
