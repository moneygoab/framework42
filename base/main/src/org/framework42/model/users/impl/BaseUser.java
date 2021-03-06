package org.framework42.model.users.impl;

import org.framework42.model.users.*;

import java.io.Serializable;
import java.util.*;

import static org.framework42.utils.NullChecker.notNull;

public class BaseUser implements User, Serializable {

    protected final int id;

    protected final long dateCreated;

    protected final String loginName;

    protected final String displayName;

    protected Map<Role, UserRole> userRoles;

    protected Map<UserSetting, String> userSettings;

    protected final String requestId;

    public BaseUser(Date dateCreated) {
        this.id = 0;
        this.dateCreated = notNull(dateCreated, "Date created can't be null!").getTime();
        this.loginName = "";
        this.displayName = "";
        this.userRoles = new HashMap<Role, UserRole>();
        this.userRoles.put(Role.UNKNOWN_PERSON, new UserRoleImpl<Role,RoleStatus>(Role.UNKNOWN_PERSON, RoleStatus.ACTIVE, dateCreated));
        this.userSettings = new HashMap<UserSetting, String>();
        this.requestId = UUID.randomUUID().toString();
    }

    public BaseUser(int id, Date dateCreated, String loginName, String displayName, Map<Role, UserRole> userRoles) {
        this.id = id;
        this.dateCreated = notNull(dateCreated, "Date created can't be null!").getTime();
        this.loginName = notNull(loginName, "Login name can't be null!");
        this.displayName = notNull(displayName, "Display name can't be null!");
        this.userRoles = notNull(userRoles, "User roles can't be null or contain null parameters!");
        this.userSettings = new HashMap<UserSetting, String>();
        this.requestId = UUID.randomUUID().toString();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Date getDateCreated() {
        return new Date(dateCreated);
    }

    @Override
    public String getLoginName() {
        return loginName;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public Map<Role, UserRole> getUserRoles() {
        return userRoles;
    }

    @Override
    public void addUserRole(UserRole userRole) {
        userRoles.put(userRole.getRole(), userRole);
    }

    @Override
    public void removeUserRole(UserRole userRole) {
        userRoles.remove(userRole.getRole());
    }

    @Override
    public boolean hasUserRole(Role role) {

        return (userRoles.get(role)!=null);

    }

    @Override
    public boolean hasAnyUserRole(List<Role> roleList) {

        for(Role requiredRole: roleList) {

            if(userRoles.containsKey(requiredRole)) {

                return true;
            }
        }

        return false;
    }

    @Override
    public Map<UserSetting, String> getUserSettings() {
        return userSettings;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BaseUser baseUser = (BaseUser) o;

        if (dateCreated != baseUser.dateCreated) {
            return false;
        }
        if (id != baseUser.id) {
            return false;
        }
        if (!displayName.equals(baseUser.displayName)) {
            return false;
        }
        if (!loginName.equals(baseUser.loginName)) {
            return false;
        }
        if (!userRoles.equals(baseUser.userRoles)) {
            return false;
        }
        if (!userSettings.equals(baseUser.userSettings)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

        int result = id;
        result = 31 * result + (int) (dateCreated ^ (dateCreated >>> 32));
        result = 31 * result + loginName.hashCode();
        result = 31 * result + displayName.hashCode();
        result = 31 * result + userRoles.hashCode();
        result = 31 * result + userSettings.hashCode();
        return result;
    }

    @Override
    public String toString() {

        return "BaseUser{" +
               "id=" + id +
               ", dateCreated=" + dateCreated +
               ", loginName='" + loginName + '\'' +
               ", displayName='" + displayName + '\'' +
               ", userRoles=" + userRoles +
               ", userSettings=" + userSettings +
               '}';
    }

    @Override
    public String getRequestId() {
        return requestId;
    }
}
