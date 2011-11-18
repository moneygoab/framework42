package org.framework42.model.users.impl;

import org.framework42.model.users.LoginCredentials;

import static org.framework42.utils.NullChecker.notNull;

public class LoginCredentialsImpl implements LoginCredentials {

    private final String userName;

    private final String hashedPassword;

    private final String ip;

    private final String userAgent;

    public LoginCredentialsImpl(String userName, String hashedPassword, String ip, String userAgent) {
        this.userName = notNull(userName, "User name can't be null!");
        this.hashedPassword = notNull(hashedPassword, "Hashed password can't be null!");
        this.ip = notNull(ip, "Ip can't be null!");
        this.userAgent = notNull(userAgent, "User agent can't be null!");
    }

    @Override
    public String getUserAgent() {
        return userAgent;
    }

    @Override
    public String getIp() {
        return ip;
    }

    @Override
    public String getHashedPassword() {
        return hashedPassword;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "LoginCredentialsImpl{" +
                "userName='" + userName + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", ip='" + ip + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
