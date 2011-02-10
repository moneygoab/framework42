package org.framework42.model.users.impl;

import org.framework42.model.users.Role;
import org.framework42.model.users.User;
import org.framework42.model.users.UserRole;
import org.framework42.model.users.UserSetting;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BaseUser implements User {

    protected final int id;

    protected final long dateCreated;

    protected final String loginName;

    protected final String displayName;

    protected Map<Role, UserRole> userRoles;

    protected Map<UserSetting, String> userSettings;

    public BaseUser(int id, Date dateCreated, String loginName, String displayName, Map<Role, UserRole> userRoles) {
        this.id = id;
        this.dateCreated = dateCreated.getTime();
        this.loginName = loginName;
        this.displayName = displayName;
        this.userRoles = userRoles;
        this.userSettings = new HashMap<UserSetting, String>();
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
    public UserRole getUserRole(Role role) {

        for(Role currentRole: userRoles.keySet()) {
            if(currentRole.getId() == role.getId()) {
                return userRoles.get(currentRole);
            }
        }

        throw new IllegalArgumentException("No user role with the role "+role+" exists!");
    }

    @Override
    public Map<UserSetting, String> getUserSettings() {
        return userSettings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseUser baseUser = (BaseUser) o;

        if (dateCreated != baseUser.dateCreated) return false;
        if (id != baseUser.id) return false;
        if (!displayName.equals(baseUser.displayName)) return false;
        if (!loginName.equals(baseUser.loginName)) return false;
        if (!userRoles.equals(baseUser.userRoles)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) (dateCreated ^ (dateCreated >>> 32));
        result = 31 * result + loginName.hashCode();
        result = 31 * result + displayName.hashCode();
        result = 31 * result + userRoles.hashCode();
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
                '}';
    }
}
