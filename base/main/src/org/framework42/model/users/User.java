package org.framework42.model.users;

import org.framework42.model.Role;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public interface User extends Serializable {

    public int getUserID();

    public Date getDateCreated();

    public String getLoginName();

    public String getDisplayName();

    public Map<Role, UserRole> getUserRoles();

}
