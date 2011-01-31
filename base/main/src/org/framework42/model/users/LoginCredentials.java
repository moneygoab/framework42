package org.framework42.model.users;

public interface LoginCredentials {

    public String getUserName();

    public String getHashedPassword();

    public String getIp();

    public String getUserAgent();

}
