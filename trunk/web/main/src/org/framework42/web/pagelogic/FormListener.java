package org.framework42.web.pagelogic;

import org.apache.log4j.Logger;
import org.framework42.exceptions.NotAuthorizedException;
import org.framework42.web.pagemodel.PageModel;
import org.framework42.web.session.UserSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class FormListener<T extends UserSession, R extends PageModel> {

    protected final Logger logger;

    protected final String formEventId;

    protected FormListener(String formEventId, String loggerId) {
        this.formEventId = formEventId;
        this.logger = Logger.getLogger(loggerId);
    }

    public void tryFormEvent(String currentFormEventId, HttpServletRequest req, HttpServletResponse resp, T session, R pageModel) throws NotAuthorizedException {

        System.out.println(formEventId+":"+currentFormEventId);
        if(formEventId.equalsIgnoreCase(currentFormEventId)) {

            handleFormEvent(req, resp, session, pageModel);
        }
    }

    protected abstract void handleFormEvent(HttpServletRequest req, HttpServletResponse resp, T session, R pageModel) throws NotAuthorizedException;

}
