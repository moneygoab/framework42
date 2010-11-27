package org.framework42.authorization;

import org.framework42.exceptions.NotAuthorizedException;

public interface AuthorizationPerformer {

    public void authorizeAction(AuthorizationAction authorizationAction) throws NotAuthorizedException;

}
