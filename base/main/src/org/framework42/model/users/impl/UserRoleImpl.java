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

    private final Date startDate;
    private final Date endDate;

    /**
     * Constructor that takes a role and role status and gives the other values a default value
     * <p/>
     * startDate = now; endDate = 9999-01-01;
     */
    public UserRoleImpl(R role, RS roleStatus) {
        this.role = role;
        this.roleStatus = roleStatus;
        this.startDate = Calendar.getInstance().getTime();
        this.endDate = new GregorianCalendar(9999, 0, 1, 00, 00, 00).getTime();
    }

    public UserRoleImpl(R role, RS roleStatus, Date startDate, Date endDate) {
        this.role = role;
        this.roleStatus = roleStatus;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public R getRole() {
        return role;
    }

    public RS getRoleStatus() {
        return roleStatus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
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

        if (!endDate.equals(userRole.endDate)) {
            return false;
        }
        if (role != userRole.role) {
            return false;
        }
        if (roleStatus != userRole.roleStatus) {
            return false;
        }
        if (!startDate.equals(userRole.startDate)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = role.hashCode();
        result = 31 * result + roleStatus.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "role=" + role +
                ", roleStatus=" + roleStatus +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

}
