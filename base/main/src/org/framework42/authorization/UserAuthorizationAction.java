package org.framework42.authorization;

public enum UserAuthorizationAction implements AuthorizationAction {

    HAS_VALID_ROLE(100000);

    private final int databaseId;

    UserAuthorizationAction(int databaseId) {
        this.databaseId = databaseId;
    }

    @Override
    public int getDatabaseId() {
        return databaseId;
    }

    public UserAuthorizationAction getFromDatabaseId(int databaseId) {

        for (UserAuthorizationAction action : UserAuthorizationAction.values()) {
            if (databaseId == action.getDatabaseId()) {
                return action;
            }
        }

        throw new RuntimeException("Illegal database id (" + databaseId + ") no matching action found!");

    }

}
