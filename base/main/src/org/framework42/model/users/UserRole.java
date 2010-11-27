package org.framework42.model.users;

import org.framework42.model.Role;
import org.framework42.model.RoleStatus;

import java.util.Date;

public interface UserRole {

    public Role getRole();

    public RoleStatus getRoleStatus();

    public Date getStartDate();

    public Date getEndDate();

}

