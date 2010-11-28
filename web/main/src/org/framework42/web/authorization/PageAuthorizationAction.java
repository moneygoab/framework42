package org.framework42.web.authorization;

import org.framework42.authorization.AuthorizationAction;

public enum PageAuthorizationAction implements AuthorizationAction {

    VIEW_PAGE(100000);

    private final int databaseId;

    PageAuthorizationAction(int databaseId) {
        this.databaseId = databaseId;
    }

    @Override
    public int getDatabaseId() {
        return databaseId;
    }

    public PageAuthorizationAction getFromDatabaseId(int databaseId) {

        for (PageAuthorizationAction action : PageAuthorizationAction.values()) {
            if (databaseId == action.getDatabaseId()) {
                return action;
            }
        }

        throw new RuntimeException("Illegal database id (" + databaseId + ") no matching action found!");

    }

}
