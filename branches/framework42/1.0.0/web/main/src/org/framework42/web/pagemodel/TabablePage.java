package org.framework42.web.pagemodel;

import org.framework42.web.session.UserSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This interface defines an interface as tabable, it should be implemented by the page logic of the page to make sure
 * that a tab is added and removed when it should be added or removed.
 * */
public interface TabablePage<T extends UserSession, R extends PageModel> {

    public void addTab(HttpServletRequest req, HttpServletResponse resp, T session, R pageModel);
        
}

