package org.framework42.authorization;

public class BaseAuthorizationAction implements AuthorizationAction {

    private final int databaseId;

    public BaseAuthorizationAction(int databaseId) {
        this.databaseId = databaseId;
    }

    @Override
    public int getDatabaseId() {
        return databaseId;
    }

}
