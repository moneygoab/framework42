package org.framework42.authorization;

import org.framework42.exceptions.NotAuthorizedException;

/**
 * This is the interface for the classes that do the actual authorization.
 */
public interface AuthorizationPerformer {

    /**
     *
     * */
    public void authorize(AuthorizationAction authorizationAction, String resource) throws NotAuthorizedException;

}
