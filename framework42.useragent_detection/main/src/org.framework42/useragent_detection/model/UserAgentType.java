package org.framework42.useragent_detection.model;

import java.io.Serializable;

public enum UserAgentType implements Serializable {

    WEB_BROWSER(1), TEXT_BROWSER(2),
    ROBOT(3), EMAIL_CLIENT(4), UNKNOWN(0);

    private final int id;

    private UserAgentType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static UserAgentType getById(int id) {

        for(UserAgentType type: UserAgentType.values()) {

            if(type.getId() == id) {

                return type;
            }
        }

        throw new IllegalArgumentException("No user agent type with id "+id+" exists!");
    }

}
