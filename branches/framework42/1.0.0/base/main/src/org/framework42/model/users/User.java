package org.framework42.model.users;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface User extends Serializable {

    public int getId();

    public Date getDateCreated();

    public String getLoginName();

    public String getDisplayName();

    public Map<Role, UserRole> getUserRoles();

    public void addUserRole(UserRole userRole);

    public void removeUserRole(UserRole userRole);

    public boolean hasUserRole(Role role);

    public boolean hasAnyUserRole(List<Role> role);

    public Map<UserSetting, String> getUserSettings();
    
}
