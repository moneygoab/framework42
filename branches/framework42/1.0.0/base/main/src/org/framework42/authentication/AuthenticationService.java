package org.framework42.authentication;

import org.framework42.exceptions.NotAuthenticatedException;
import org.framework42.model.users.LoginCredentials;
import org.framework42.model.users.User;

public interface AuthenticationService<T extends User> {

    public T login(LoginCredentials loginCredentials) throws NotAuthenticatedException;

}
