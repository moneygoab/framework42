package org.framework42.authorization;

import org.framework42.exceptions.NotAuthorizedException;

public abstract class AbstractAuthorizationPerformer implements AuthorizationPerformer {

    public AbstractAuthorizationPerformer() {

    }

    @Override
    public void authorizeAction(AuthorizationAction authorizationAction) throws NotAuthorizedException {

        if (performGeneralAuthorization() != AuthorizationResult.AUTHORIZED) {
            throw new NotAuthorizedException();
        }

        AuthorizationResult authorizationResult = performAuthorization(authorizationAction);

        if (authorizationResult != AuthorizationResult.AUTHORIZED) {
            throw new NotAuthorizedException();
        }

    }

    protected AuthorizationResult performGeneralAuthorization() {

        return AuthorizationResult.AUTHORIZED;

    }

    protected abstract AuthorizationResult performAuthorization(AuthorizationAction authorizationAction);

}
