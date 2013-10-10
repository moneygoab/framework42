package org.framework42.web.plugins.TinyMCEImageHandler;

import org.framework42.exceptions.ManageableException;
import org.framework42.web.exceptions.StopServletExecutionException;
import org.framework42.web.pagelogic.PageLogic;
import org.framework42.web.session.UserSession;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TinyMCEImageHandlerPageLogic extends PageLogic<UserSession,TinyMCEImageHandlerPageModel> {

    public TinyMCEImageHandlerPageLogic() {
        super("org.framework42.web.plugin.tinymce_image_handler");
    }

    @Override
    protected TinyMCEImageHandlerPageModel createPageModel(HttpServletRequest req, UserSession session) {
        return new TinyMCEImageHandlerPageModel();
    }

    @Override
    protected void addFormListeners() {

        formListenerList.add(new TinyMCEImageHandlerFormListener());
    }

    @Override
    protected TinyMCEImageHandlerPageModel performSpecific(HttpServletRequest req, HttpServletResponse resp, UserSession session, TinyMCEImageHandlerPageModel pageModel) throws IOException, StopServletExecutionException, ManageableException {

        return pageModel;
    }

    @Override
    protected void setupPageParametersSpecific(HttpServlet servlet, HttpServletRequest req, UserSession session, TinyMCEImageHandlerPageModel pageModel) {

    }
}
