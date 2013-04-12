package org.framework42.web.pages;


import org.framework42.i18n.I18N;
import org.framework42.model.MimeType;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.standardhtml.Html;
import org.framework42.web.components.standardhtml.head.*;
import org.framework42.web.pagelogic.PageLogic;
import org.framework42.web.pagemodel.CMSDataClass;
import org.framework42.web.pagemodel.PageModel;
import org.framework42.web.session.UserSession;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Locale;

public abstract class CMSWebPage<T extends UserSession, R extends PageModel> extends WebPage<T, R> {

    private final CMSDataClass dataClass;

    protected CMSWebPage(CMSDataClass dataClass, PageLogic<T, R> pageLogic) {
        super(dataClass.getLoggerId(), pageLogic, dataClass.getAccessRoles(), dataClass.getDenyAccessRoles());

        this.dataClass = dataClass;
    }

    @Override
    public void init() throws ServletException {
        super.init();

        initServiceBinder();

        if(!I18N.INSTANCE.isInitialized()) {

            initI18N();
        }
    }

    protected abstract void initServiceBinder();

    protected abstract void initI18N();

    @Override
    protected void doGetSub(R model, T session, Html.Builder htmlBuilder) throws ServletException, IOException {

        I18N i18n = I18N.INSTANCE;
        Locale locale = session.getLocale();

        htmlBuilder.add(createHead(session, model));
        //htmlBuilder.add(createBody(model, session));
    }

    protected HtmlComponent createHead(T session, R model) {

        I18N i18n = I18N.INSTANCE;
        Locale locale = session.getLocale();

        Head.Builder head = new Head.Builder();

        head.add(new Meta.Builder(MetaName.CHARSET, model.getPageCharacterSet()).build());

        head.add(new Title(i18n.get(model.getPageTitleKey(), locale)));
        head.add(new Meta.Builder(MetaName.KEYWORDS, i18n.get(model.getPageKeywordsKey(),locale)).build());
        head.add(new Meta.Builder(MetaName.DESCRIPTION, i18n.get(model.getPageDescriptionKey(),locale)).build());
        head.add(new HeadLink.Builder(HeadLinkRelationship.STYLESHEET, dataClass.getSiteConfiguration().getCss(), MimeType.CSS).build());

        //TODO: Handle Google Analytics
        //head.add(createGoogleAnalytics());

        return head.build();
    }

}
