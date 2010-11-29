package org.framework42.authorization;

import org.framework42.exceptions.NotAuthorizedException;

public abstract class AbstractAuthorizationPerformer implements AuthorizationPerformer {

    public AbstractAuthorizationPerformer() {
    }

    @Override
    public void authorize(AuthorizationAction authorizationAction) throws NotAuthorizedException {

        performGeneralAuthorization();

        performAuthorization(authorizationAction);

    }

    protected void performGeneralAuthorization() throws NotAuthorizedException {
    }

    protected abstract void performAuthorization(AuthorizationAction authorizationAction) throws NotAuthorizedException;

}
