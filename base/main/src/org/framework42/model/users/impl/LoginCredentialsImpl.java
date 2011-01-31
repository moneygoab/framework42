package org.framework42.model.users.impl;

import org.framework42.model.users.LoginCredentials;

public class LoginCredentialsImpl implements LoginCredentials {

    private final String userName;

    private final String hashedPassword;

    private final String ip;

    private final String userAgent;

    public LoginCredentialsImpl(String userName, String hashedPassword, String ip, String userAgent) {
        this.userName = userName;
        this.hashedPassword = hashedPassword;
        this.ip = ip;
        this.userAgent = userAgent;
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
