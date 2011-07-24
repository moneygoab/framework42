package org.framework42.model.users;

import java.io.Serializable;

public enum RoleStatus implements Serializable {

    ACTIVE(1),
    LOCKED(2)
    ;

    private final int id;

    private RoleStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
