package org.framework42.model.users.impl;

import org.framework42.model.users.RoleStatus;

public enum BaseRoleStatus implements RoleStatus {

    ACTIVE(1, "ROLE_STATUS_ACTIVE"),
    LOCKED(2, "ROLE_STATUS_LOCKED")
    ;

    private final int id;

    private final String guiTextId;

    private BaseRoleStatus(int id, String guiTextId) {
        this.id = id;
        this.guiTextId = guiTextId;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getGuiTextId() {
        return guiTextId;
    }

}
