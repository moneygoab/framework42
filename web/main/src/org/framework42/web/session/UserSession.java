package org.framework42.web.session;

import com.nummer42.model.users.User;

import java.util.Locale;

public abstract class UserSession<T extends User> {

    protected Locale locale;

    protected boolean authenticated;

    protected T user;

    public UserSession() {

    }

    public abstract T getUser();

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public abstract void logIn(T user);

    public abstract void logOut();

}
