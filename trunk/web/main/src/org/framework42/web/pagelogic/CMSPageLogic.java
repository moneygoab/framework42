package org.framework42.web.pagelogic;

import org.framework42.web.pagemodel.CMSDataProvider;
import org.framework42.web.pagemodel.CMSPageModel;
import org.framework42.web.pagemodel.CMSSiteConfiguration;
import org.framework42.web.pages.CMSWebPage;
import org.framework42.web.session.UserSession;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public abstract class CMSPageLogic<T extends UserSession, R extends CMSPageModel> extends PageLogic<T,R> {

    protected CMSPageLogic(String loggerId) {
        super(loggerId);
    }

    @Override
    protected void setupPageParametersSpecific(HttpServlet servlet, HttpServletRequest req, T session, R pageModel) {

        CMSDataProvider dataProvider = ((CMSWebPage) servlet).getDataProvider();

        pageModel.setPageId(dataProvider.getPageId(req.getRequestURI()));

        logger.debug(pageModel.getPageId()+"*******");

        ((CMSWebPage) servlet).setUpRoles(
                dataProvider.getAccessRoles(pageModel.getPageId()),
                dataProvider.getDenyAccessRoles(pageModel.getPageId())
        );

        pageModel.setSiteConfiguration(dataProvider.getSiteConfiguration(pageModel.getPageId()));
    }
}
