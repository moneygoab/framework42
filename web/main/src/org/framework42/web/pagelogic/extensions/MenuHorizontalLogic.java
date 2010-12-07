package org.framework42.web.pagelogic.extensions;

import org.framework42.web.exceptions.StopServletExecutionException;
import org.framework42.web.pagelogic.ComponentLogic;
import org.framework42.web.pagemodel.extensions.MenuHorizontalModel;
import org.framework42.web.session.UserSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MenuHorizontalLogic extends ComponentLogic<UserSession, MenuHorizontalModel> {

    public MenuHorizontalLogic(String loggerId) {
        super(loggerId);
    }

    @Override
    public MenuHorizontalModel perform(HttpServletRequest req, HttpServletResponse resp, UserSession session) throws IOException, StopServletExecutionException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
