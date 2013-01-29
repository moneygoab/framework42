package org.framework42.model.users.impl;

import org.framework42.model.users.Role;
import org.framework42.model.users.RoleStatus;
import org.framework42.model.users.UserRole;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

public class UserRoleImpl<R extends Role, RS extends RoleStatus> implements UserRole, Serializable {

    private final int id;

    private final R role;
    private final RS roleStatus;

    private final Date activeFrom;
    private final Date activeTo;

    /**
     * Constructor that takes a role and role status and gives the other values a default value
     * activeTo = 9999-01-01;
     * @param role          The role
     * @param roleStatus    The role status
     * @param activeFrom    The date the user role is active from
     *
     */
    public UserRoleImpl(R role, RS roleStatus, Date activeFrom) {
        this.id = 0;
        this.role = role;
        this.roleStatus = roleStatus;
        this.activeFrom = activeFrom;
        this.activeTo = new GregorianCalendar(9999, 0, 1, 0, 0, 0).getTime();
    }

    public UserRoleImpl(R role, RS roleStatus, Date activeFrom, Date activeTo) {
        this.id = 0;
        this.role = role;
        this.roleStatus = roleStatus;
        this.activeFrom = activeFrom;
        this.activeTo = activeTo;
    }

    public UserRoleImpl(int id, R role, RS roleStatus, Date activeFrom) {
        this.id = id;
        this.role = role;
        this.roleStatus = roleStatus;
        this.activeFrom = activeFrom;
        this.activeTo = new GregorianCalendar(9999, 0, 1, 0, 0, 0).getTime();
    }

    public UserRoleImpl(int id, R role, RS roleStatus, Date activeFrom, Date activeTo) {
        this.id = id;
        this.role = role;
        this.roleStatus = roleStatus;
        this.activeFrom = activeFrom;
        this.activeTo = activeTo;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public R getRole() {
        return role;
    }

    @Override
    public RS getRoleStatus() {
        return roleStatus;
    }

    @Override
    public Date getActiveFrom() {
        return activeFrom;
    }

    @Override
    public Date getActiveTo() {
        return activeTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserRoleImpl userRole = (UserRoleImpl) o;

        if (!activeTo.equals(userRole.activeTo)) {
            return false;
        }
        if (role != userRole.role) {
            return false;
        }
        if (roleStatus != userRole.roleStatus) {
            return false;
        }
        if (!activeFrom.equals(userRole.activeFrom)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = role.hashCode();
        result = 31 * result + roleStatus.hashCode();
        result = 31 * result + activeFrom.hashCode();
        result = 31 * result + activeTo.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "role=" + role +
                ", roleStatus=" + roleStatus +
                ", activeFrom=" + activeFrom +
                ", activeTo=" + activeTo +
                '}';
    }

}
