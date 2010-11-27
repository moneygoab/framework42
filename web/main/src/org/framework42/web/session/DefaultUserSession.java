package org.framework42.web.session;

import org.framework42.model.users.User;

public class DefaultUserSession<T extends UserSession> extends UserSession<User> {

    @Override
    public User getUser() {
        //TODO: Not return null
        return null;
    }

    @Override
    public void logIn(User user) {
    }

    @Override
    public void logOut() {
    }

}
