package org.framework42.web.pages;

import org.framework42.i18n.I18N;
import org.framework42.model.MimeType;
import org.framework42.model.users.Role;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.RawHtml;
import org.framework42.web.components.js_components.JavaScript;
import org.framework42.web.components.standardhtml.Body;
import org.framework42.web.components.standardhtml.Html;
import org.framework42.web.components.standardhtml.head.*;
import org.framework42.web.pagelogic.PageLogic;
import org.framework42.web.pagemodel.PageModel;
import org.framework42.web.pagemodel.Tabable;
import org.framework42.web.session.TabableApp;
import org.framework42.web.session.UserSession;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public abstract class AbstractBaseWebPage<T extends UserSession, R extends PageModel> extends WebPage<T, R> {

    protected AbstractBaseWebPage(String loggerId, PageLogic<T, R> pageLogic) {
        super(loggerId, pageLogic, new ArrayList<Role>(), Arrays.asList(Role.LOCKED, Role.DISMISSED, Role.UNKNOWN_PERSON));
    }

    protected AbstractBaseWebPage(String loggerId, PageLogic<T, R> pageLogic, List<Role> accessRoles, List<Role> denyAccessRoles) {
        super(loggerId, pageLogic, accessRoles, denyAccessRoles);
    }

    @Override
    public void init() throws ServletException {
        super.init();

        if (!I18N.INSTANCE.isInitialized()) {

            initI18N();
        }
    }

    protected abstract void initI18N();

    @Override
    protected void doGetSub(R model, T session, Html.Builder htmlBuilder) throws ServletException, IOException {

        doSub(model, session, htmlBuilder);

    }

    @Override
    protected void doPostSub(R model, T session, Html.Builder htmlBuilder) throws ServletException, IOException {

        doSub(model, session, htmlBuilder);

    }

    protected void doSub(R model, T session, Html.Builder htmlBuilder) throws ServletException, IOException {

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
        
        head.add(new Title(i18n.get(model.getPageTitleKey(), locale)));
        head.add(new Meta.Builder(MetaName.KEYWORDS, i18n.get(model.getPageKeywordsKey(),locale)).build());
        head.add(new Meta.Builder(MetaName.DESCRIPTION, i18n.get(model.getPageDescriptionKey(),locale)).build());
        //TODO: Configurable stylesheet files.
        head.add(new HeadLink.Builder(HeadLinkRelationship.STYLESHEET, "css/base_style.css", MimeType.CSS).build());

        if(model instanceof Tabable) {

            JavaScript.Builder javascript = new JavaScript.Builder();
            javascript.addScriptLine("function saveTabState() {");
            javascript.addScriptLine("\tvar params = \"tabId="+((TabableApp)session).getActiveTabEnvironment().getId()+"&pageData=\"+escapeParameters(serializeXML(document.getElementById('page_area')));");
            javascript.addScriptLine("\t//alert(serializeXML(document.getElementById('page_area')));");
            javascript.addScriptLine("\tvar xmlHttp = getXmlHttp();");
            javascript.addScriptLine("\txmlHttp.open(\"POST\",\"SaveTabView\",false);");
            javascript.addScriptLine("\txmlHttp.setRequestHeader(\"Content-type\",\"application/x-www-form-urlencoded\");");
            javascript.addScriptLine("\txmlHttp.setRequestHeader(\"Content-length\", \"+params.length+\")");
            javascript.addScriptLine("\txmlHttp.send(params);");
            javascript.addScriptLine("\t");
            javascript.addScriptLine("\t");
            javascript.addScriptLine("}");
            javascript.addScriptLine("");
            javascript.addScriptLine("\tfunction serializeXML(xmlNode) {");
            javascript.addScriptLine("\ttry {");
            javascript.addScriptLine("\t\treturn (new XMLSerializer()).serializeToString(xmlNode);");
            javascript.addScriptLine("\t} catch(e) {");
            javascript.addScriptLine("\t\ttry {");
            javascript.addScriptLine("\t\t\treturn xmlNode.xml;");
            javascript.addScriptLine("\t\t} catch(e) {");
            javascript.addScriptLine("\t\t\t//TODO: handle unsupported browsers");
            javascript.addScriptLine("\t\t}");
            javascript.addScriptLine("\t}");
            javascript.addScriptLine("}");
            javascript.addScriptLine("");
            javascript.addScriptLine("function escapeParameters(parameters) {");
            javascript.addScriptLine("\t");
            javascript.addScriptLine("\tvar replacedParameters = parameters.replace(/&/g,\"%26\");");
            javascript.addScriptLine("\treturn replacedParameters;");
            javascript.addScriptLine("}");
            javascript.addScriptLine("");
            javascript.addScriptLine("function getXmlHttp() {");
            javascript.addScriptLine("\tvar xmlHttp = false;");
            javascript.addScriptLine("\ttry {");
            javascript.addScriptLine("\t\txmlHttp = new ActiveXObject(\"Msxml2.XMLHTTP\")  // For Old Microsoft Browsers");
            javascript.addScriptLine("\t} catch(e) {");
            javascript.addScriptLine("\t\ttry {");
            javascript.addScriptLine("\t\t\txmlHttp = new ActiveXObject(\"Microsoft.XMLHTTP\")  // For Microsoft IE 6.0+");
            javascript.addScriptLine("\t\t} catch(e2) {");
            javascript.addScriptLine("\t\t\txmlHttp = false   // No Browser accepts the XMLHTTP Object then false");
            javascript.addScriptLine("\t\t}");
            javascript.addScriptLine("\t}");
            javascript.addScriptLine("\tif (!xmlHttp && typeof XMLHttpRequest != 'undefined') {");
            javascript.addScriptLine("\t\txmlHttp = new XMLHttpRequest(); //For Firefox, Chrome, Safari, Opera Browsers");
            javascript.addScriptLine("\t}");
            javascript.addScriptLine("\treturn xmlHttp");
            javascript.addScriptLine("}");

            javascript.addScriptLine("window.onbeforeunload = saveTabState;");
            head.add(javascript.build());
        }

        //TODO: Handle Google Analytics
        //head.add(createGoogleAnalytics());

        return head.build();
    }

    protected HtmlComponent createBody(R model, T session) {

        Body.Builder bodyBuilder = new Body.Builder();

        bodyBuilder.add(createBodyMainDiv(model, session));

        bodyBuilder.add(createBodyFooter(model, session));

        return bodyBuilder.build();

    }

    protected abstract HtmlComponent createBodyMainDiv(R model, T session);

    protected abstract HtmlComponent createBodyFooter(R model, T session);

}
