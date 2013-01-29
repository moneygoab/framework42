package org.framework42.model.users;

import java.io.Serializable;
import java.util.Date;

public interface UserRole extends Serializable {

    public int getId();

    public Role getRole();

    public RoleStatus getRoleStatus();

    public Date getActiveFrom();

    public Date getActiveTo();

}

