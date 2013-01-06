package org.framework42.web.pagemodel;

public enum UserClientAction {

    RELOAD_CLIENT(1);

    private final int id;

    private UserClientAction(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static UserClientAction getById(int id) {

        for(UserClientAction action: UserClientAction.values()) {

            if(action.getId()==id) {

                return action;
            }
        }

        throw new IllegalArgumentException("No user client action with id "+id+" exists!");
    }

}
