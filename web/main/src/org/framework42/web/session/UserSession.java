package org.framework42.web.session;

import org.framework42.model.users.User;
import org.framework42.model.users.UserSetting;

import java.util.Locale;
import java.util.Map;

public abstract class UserSession<T extends User> {

    protected Locale locale;

    protected boolean authenticated;

    protected T user;

    public UserSession() {

        setDefaultLocale();

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

    protected void setUserLocale(T user) {

        Map<UserSetting, String> userSettings = user.getUserSettings();

        String language = userSettings.get(UserSetting.LANGUAGE);
        String country = userSettings.get(UserSetting.COUNTRY);

        if (language == null || country == null) {
            setDefaultLocale();
        } else {
            locale = new Locale(language, country);
        }

    }

    protected abstract void setDefaultLocale(); 

}
