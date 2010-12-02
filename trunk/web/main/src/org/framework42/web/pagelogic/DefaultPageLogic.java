package org.framework42.web.pagelogic;

import org.framework42.web.exceptions.ManageablePageException;
import org.framework42.web.exceptions.StopServletExecutionException;
import org.framework42.web.pagemodel.*;
import org.framework42.web.session.UserSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultPageLogic<T extends UserSession, R extends PageModel> extends PageLogic<T, PageModel> {

    public DefaultPageLogic(String loggerId) {
        super(loggerId);
    }

    @Override
    protected PageModel createPageModel(HttpServletRequest req, HttpServletResponse resp, T session) {
        return new DefaultPageModel();
    }

    @Override
    protected void setupPageParameters(HttpServletRequest req, T session, PageModel pageModel) {
        
    }

    @Override
    protected PageModel performSpecific(HttpServletRequest req, HttpServletResponse resp, T session, PageModel pageModel) throws IOException, StopServletExecutionException, ManageablePageException {

        return pageModel;

    }
}
