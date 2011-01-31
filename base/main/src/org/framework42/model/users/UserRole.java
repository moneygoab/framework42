package org.framework42.model.users;

import java.util.Date;

public interface UserRole {

    public Role getRole();

    public RoleStatus getRoleStatus();

    public Date getActiveFrom();

    public Date getActiveTo();

}

