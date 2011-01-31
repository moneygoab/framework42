package org.framework42.authorization;

public enum UserAuthorizationAction implements AuthorizationAction {

    HAS_VALID_ROLE(100000);

    private final int id;

    UserAuthorizationAction(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public UserAuthorizationAction getFromId(int id) {

        for (UserAuthorizationAction action : UserAuthorizationAction.values()) {
            if (id == action.getId()) {
                return action;
            }
        }

        throw new RuntimeException("Illegal id (" + id + ") no matching action found!");

    }

}
