package org.framework42.web.session;

import org.framework42.model.users.User;
import org.framework42.model.users.UserSetting;
import org.framework42.useragent_detection.model.*;
import org.framework42.useragent_detection.model.impl.OperatingSystemImpl;
import org.framework42.useragent_detection.model.impl.ParsedUserAgentImpl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public abstract class UserSession<T extends User> implements Serializable {

    protected Locale locale;

    protected boolean authenticated;

    protected boolean allowUndefinedParameters;

    protected Map<String, String> miscParameters;

    protected T user;

    protected ParsedUserAgent parsedUserAgent;

    public UserSession(T user, boolean allowUndefinedParameters) {

        setDefaultLocale();

        this.user = user;
        this.allowUndefinedParameters = allowUndefinedParameters;
        this.authenticated = false;

        this.parsedUserAgent = new ParsedUserAgentImpl("", 0, UserAgentType.UNKNOWN, DeviceType.UNKNOWN, RenderingEngine.UNKNOWN, new OperatingSystemImpl(OperatingSystemBranch.UNKNOWN, "", 0, ""));

        miscParameters = new HashMap<String, String>();

    }

    public ParsedUserAgent getParsedUserAgent() {
        return parsedUserAgent;
    }

    public void setParsedUserAgent(ParsedUserAgent parsedUserAgent) {
        this.parsedUserAgent = parsedUserAgent;
    }

    public T getUser() {
        return user;
    }

    public boolean isAllowUndefinedParameters() {
        return allowUndefinedParameters;
    }

    public Map<String, String> getMiscParameters() {
        return miscParameters;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public boolean isAuthenticated() {
        return authenticated;
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
