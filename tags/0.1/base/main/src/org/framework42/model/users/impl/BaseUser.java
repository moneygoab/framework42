package org.framework42.model.users.impl;

import org.framework42.model.users.Role;
import org.framework42.model.users.User;
import org.framework42.model.users.UserRole;

import java.util.Date;
import java.util.Map;

public class BaseUser implements User {

    protected final int userId;

    protected final long dateCreated;

    protected final String loginName;

    protected final String displayName;

    protected Map<Role, UserRole> userRoles;

    public BaseUser(int userId, Date dateCreated, String loginName, String displayName, Map<Role, UserRole> userRoles) {
        this.userId = userId;
        this.dateCreated = dateCreated.getTime();
        this.loginName = loginName;
        this.displayName = displayName;
        this.userRoles = userRoles;
    }

    @Override
    public int getUserID() {
        return userId;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseUser baseUser = (BaseUser) o;

        if (dateCreated != baseUser.dateCreated) return false;
        if (userId != baseUser.userId) return false;
        if (!displayName.equals(baseUser.displayName)) return false;
        if (!loginName.equals(baseUser.loginName)) return false;
        if (!userRoles.equals(baseUser.userRoles)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (int) (dateCreated ^ (dateCreated >>> 32));
        result = 31 * result + loginName.hashCode();
        result = 31 * result + displayName.hashCode();
        result = 31 * result + userRoles.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BaseUser{" +
                "userId=" + userId +
                ", dateCreated=" + dateCreated +
                ", loginName='" + loginName + '\'' +
                ", displayName='" + displayName + '\'' +
                ", userRoles=" + userRoles +
                '}';
    }
}
