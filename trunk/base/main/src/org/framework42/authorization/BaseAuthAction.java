package org.framework42.authorization;

public enum BaseAuthAction implements AuthorizationAction {

    ALL(1);

    private final int id;

    BaseAuthAction(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}