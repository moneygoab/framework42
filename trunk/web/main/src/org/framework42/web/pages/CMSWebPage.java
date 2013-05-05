package org.framework42.web.pages;

import org.framework42.i18n.I18N;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.RawHtml;
import org.framework42.web.components.standardhtml.Body;
import org.framework42.web.components.standardhtml.Html;
import org.framework42.web.components.standardhtml.Label;
import org.framework42.web.components.standardhtml.head.*;
import org.framework42.web.pagelogic.PageLogic;
import org.framework42.web.pagemodel.CMSDataProvider;
import org.framework42.web.pagemodel.CMSPageModel;
import org.framework42.web.session.UserSession;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Locale;

public abstract class CMSWebPage<T extends UserSession, R extends CMSPageModel> extends WebPage<T, R> {

    private final CMSDataProvider dataProvider;

    protected CMSWebPage(String loggerId, CMSDataProvider dataProvider, PageLogic<T, R> pageLogic) {
        super(loggerId, pageLogic);

        this.dataProvider = dataProvider;
    }

    public CMSDataProvider getDataProvider() {
        return dataProvider;
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
        htmlBuilder.add(createBody(model, session));
    }

    protected HtmlComponent createHead(T session, R model) {

        I18N i18n = I18N.INSTANCE;
        Locale locale = session.getLocale();

        Head.Builder head = new Head.Builder();

        head.add(new Meta.Builder(MetaName.CHARSET, model.getPageCharacterSet()).build());

        head.add(new Title(i18n.get("page_head_"+model.getPageId()+"_title", locale)));
        head.add(new Meta.Builder(MetaName.KEYWORDS, i18n.get("page_head_"+model.getPageId()+"_keywords", locale)).build());
        head.add(new Meta.Builder(MetaName.DESCRIPTION, i18n.get("page_head_"+model.getPageId()+"_description", locale)).build());
        head.add(new RawHtml("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"));
        for(String headEntry: model.getSiteConfiguration().getHeadEntries().values()) {
            head.add(new RawHtml(headEntry+"\n"));
        }



        //TODO: Handle Google Analytics
        //head.add(createGoogleAnalytics());

        try {
            String s = (String) Class.forName("java.lang.String").newInstance();

        } catch (ClassCastException e) {System.out.println(e.toString());}
        catch (ClassNotFoundException e) {System.out.println(e.toString());}
        catch (InstantiationException e) {System.out.println(e.toString());}
        catch (IllegalAccessException e) {System.out.println(e.toString());}

        return head.build();
    }

    protected HtmlComponent createBody(R model, T session) {

        I18N i18n = I18N.INSTANCE;
        Locale locale = session.getLocale();

        Body.Builder body = new Body.Builder();

        body.add(new Label(""));

        return body.build();
    }

}
