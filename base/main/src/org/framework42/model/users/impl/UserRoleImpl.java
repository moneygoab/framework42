package org.framework42.model.users.impl;

import org.framework42.model.users.Role;
import org.framework42.model.users.RoleStatus;
import org.framework42.model.users.UserRole;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class UserRoleImpl<R extends Role, RS extends RoleStatus> implements UserRole {

    private final R role;
    private final RS roleStatus;

    private final Date activeFrom;
    private final Date activeTo;

    /**
     * Constructor that takes a role and role status and gives the other values a default value
     * <p/>
     * activeFrom = now; activeTo = 9999-01-01;
     */
    public UserRoleImpl(R role, RS roleStatus) {
        this.role = role;
        this.roleStatus = roleStatus;
        this.activeFrom = Calendar.getInstance().getTime();
        this.activeTo = new GregorianCalendar(9999, 0, 1, 00, 00, 00).getTime();
    }

    public UserRoleImpl(R role, RS roleStatus, Date activeFrom, Date activeTo) {
        this.role = role;
        this.roleStatus = roleStatus;
        this.activeFrom = activeFrom;
        this.activeTo = activeTo;
    }

    public R getRole() {
        return role;
    }

    public RS getRoleStatus() {
        return roleStatus;
    }

    public Date getActiveFrom() {
        return activeFrom;
    }

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