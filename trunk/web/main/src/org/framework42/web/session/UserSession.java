package org.framework42.web.session;

import org.framework42.model.users.User;

import java.util.Locale;

public abstract class UserSession<T extends User> {

    protected Locale locale;

    protected boolean authenticated;

    protected T user;

    public UserSession() {

        setLocale(new Locale("sv","SE"));

        this.authenticated = false;

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
