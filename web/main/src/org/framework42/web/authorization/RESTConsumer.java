package org.framework42.web.authorization;

import org.framework42.model.users.Role;
import org.framework42.model.users.UserRole;

import java.util.Map;

public class RESTConsumer {

    private final boolean authenticated;

    private final int consumerId;

    private final int internalId;

    private final Map<Role,UserRole> roleMap;

    public RESTConsumer(boolean authenticated, int consumerId, int internalId, Map<Role, UserRole> roleMap) {
        this.authenticated = authenticated;
        this.consumerId = consumerId;
        this.internalId = internalId;
        this.roleMap = roleMap;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public int getInternalId() {
        return internalId;
    }

    public Map<Role, UserRole> getRoleMap() {
        return roleMap;
    }

}
