package org.framework42.web.pagemodel;

import org.framework42.model.users.Role;

import java.util.List;
import java.util.Locale;

public interface CMSDataProvider {

    public int getPageId(String pageURI);

    public List<Role> getAccessRoles(int pageId);

    public List<Role> getDenyAccessRoles(int pageId);

    public CMSSiteConfiguration getSiteConfiguration(int pageId);

}
