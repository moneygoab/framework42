package org.framework42.authorization;

public class BaseAuthorizationAction implements AuthorizationAction {

    private final int actionId;

    public BaseAuthorizationAction(int actionId) {
        this.actionId = actionId;
    }

    @Override
    public int getId() {
        return actionId;
    }

}
