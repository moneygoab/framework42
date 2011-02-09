package org.framework42.web.pagelogic;

import org.framework42.web.exceptions.ManageablePageException;
import org.framework42.web.exceptions.StopServletExecutionException;
import org.framework42.web.pagemodel.*;
import org.framework42.web.session.UserSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimplePageLogic<T extends UserSession, R extends PageModel> extends PageLogic<T, SimplePageModel> {

    public SimplePageLogic(String loggerId) {
        super(loggerId);
    }

    @Override
    protected SimplePageModel createPageModel(HttpServletRequest req, HttpServletResponse resp, T session) {
        return new SimplePageModel();
    }

    @Override
    protected void setupPageParameters(HttpServletRequest req, T session, SimplePageModel pageModel) {
        
    }

    @Override
    protected SimplePageModel performSpecific(HttpServletRequest req, HttpServletResponse resp, T session, SimplePageModel pageModel) throws IOException, StopServletExecutionException, ManageablePageException {

        return pageModel;

    }
}
