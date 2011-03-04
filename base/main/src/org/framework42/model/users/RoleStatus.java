package org.framework42.model.users;

public enum RoleStatus {

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
