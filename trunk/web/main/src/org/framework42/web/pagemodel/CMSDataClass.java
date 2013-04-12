package org.framework42.web.pagemodel;

import org.framework42.model.users.Role;

import java.util.List;

public class CMSDataClass {

    private final String loggerId;

    private final CMSSiteConfiguration siteConfiguration;

    protected final List<Role> accessRoles;
    protected final List<Role> denyAccessRoles;

    public CMSDataClass(String loggerId, CMSSiteConfiguration siteConfiguration, List<Role> accessRoles, List<Role> denyAccessRoles) {

        this.loggerId = loggerId;

        this.siteConfiguration = siteConfiguration;

        this.accessRoles = accessRoles;
        this.denyAccessRoles = denyAccessRoles;
    }

    public String getLoggerId() {
        return loggerId;
    }

    public CMSSiteConfiguration getSiteConfiguration() {
        return siteConfiguration;
    }

    public List<Role> getAccessRoles() {
        return accessRoles;
    }

    public List<Role> getDenyAccessRoles() {
        return denyAccessRoles;
    }
}
