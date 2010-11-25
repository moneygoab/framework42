package org.framework42.web.pagelogic;

import org.framework42.web.exceptions.ManageablePageException;
import org.framework42.web.exceptions.StopServletExecutionException;
import org.framework42.web.pagemodel.DefaultPageModel;
import org.framework42.web.pagemodel.PageModel;
import org.framework42.web.session.UserSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultPageLogic<T extends UserSession, R extends PageModel> extends PageLogic<T, PageModel> {

    public DefaultPageLogic(String loggerId) {
        super(loggerId);
    }

    @Override
    public PageModel perform(HttpServletRequest req, HttpServletResponse resp, T session) throws IOException, StopServletExecutionException, ManageablePageException {
        return new DefaultPageModel();
    }
}
