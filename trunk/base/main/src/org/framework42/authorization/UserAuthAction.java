package org.framework42.authorization;

public enum UserAuthAction implements AuthorizationAction {

    HAS_VALID_ROLE(100000),
    HAS_ROLE(100001);

    private final int id;

    UserAuthAction(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public UserAuthAction getFromId(int id) {

        for (UserAuthAction action : UserAuthAction.values()) {
            if (id == action.getId()) {
                return action;
            }
        }

        throw new RuntimeException("Illegal id (" + id + ") no matching action found!");

    }

}
