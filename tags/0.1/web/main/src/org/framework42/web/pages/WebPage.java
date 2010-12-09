package org.framework42.web.pages;

import org.apache.log4j.Logger;
import org.framework42.authorization.UserAuthorizationAction;
import org.framework42.authorization.UserAuthorizationPerformer;
import org.framework42.exceptions.NotAuthorizedException;
import org.framework42.i18n.I18N;
import org.framework42.model.users.Role;
import org.framework42.model.users.User;
import org.framework42.web.components.ComponentGroup;
import org.framework42.web.components.standardhtml.Html;
import org.framework42.web.exceptions.ManageablePageException;
import org.framework42.web.exceptions.StopServletExecutionException;
import org.framework42.web.pagelogic.PageLogic;
import org.framework42.web.pagemodel.PageModel;
import org.framework42.web.session.UserSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * This class is the base class of all WebPages.
 */
public abstract class WebPage<T extends UserSession, R extends PageModel> extends HttpServlet {

    protected final Logger logger;

    protected final List<Role> accessRoles;
    protected final List<Role> denyAccessRoles;

    protected final PageLogic<T, R> pageLogic;

    /**
     * Base constructor that allows everyone to access the page
     *
     * @param loggerId  The log4j logger id.
     * @param pageLogic The controller for this view if you think in MVC terms
     */
    protected WebPage(String loggerId, PageLogic<T, R> pageLogic) {

        logger = Logger.getLogger(loggerId);
        this.pageLogic = pageLogic;

        accessRoles = new ArrayList<Role>();
        denyAccessRoles = new ArrayList<Role>();

    }

    /**
     * Constructor that lets you configure who should be able to access the page. The Deny access roles take precedence
     * over the access roles, i.e. if a user has both an access and a deny access role it will be denied.
     *
     * @param loggerId        The log4j logger id.
     * @param pageLogic       The controller for this view if you think in MVC terms
     * @param accessRoles     List of roles that should be able to access the page
     * @param denyAccessRoles List of roles that should be denied to access the page
     */
    protected WebPage(String loggerId, PageLogic<T, R> pageLogic, List<Role> accessRoles, List<Role> denyAccessRoles) {

        logger = Logger.getLogger(loggerId);
        this.pageLogic = pageLogic;

        this.accessRoles = accessRoles;
        this.denyAccessRoles = denyAccessRoles;

    }

    /**
     * This method takes an htmlBuilder and simply builds the html code for the page.
     *
     * @param htmlBuilder The htmlBuilder that should build this page
     * @return String       The html code as a String
     */
    public String getHtml(Html.Builder htmlBuilder) {

        Html html = htmlBuilder.build();

        return html.getHtml(this, new ComponentGroup.Builder().build(), false);

    }

    protected abstract T createUserSession(HttpServletRequest req);

    @SuppressWarnings("unchecked")
    private T getSession(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        T session;

        try {

            Object sessionAsObject = req.getSession().getAttribute("userSession");

            if (sessionAsObject == null) {

                session = createUserSession(req);
                req.getSession().setAttribute("userSession", session);

            } else if (req.getParameter("userId") != null) {

                session = createUserSession(req);
                req.getSession().setAttribute("userSession", session);

            } else if (sessionAsObject instanceof UserSession) {

                session = (T) sessionAsObject;

            } else {

                logger.fatal("You have saved an object in the session attribute userSession that dose not " +
                        "inherit UserSession. Faulty object: " + sessionAsObject);
                session = createUserSession(req);
                req.getSession().setAttribute("userSession", session);

            }

        } catch (Exception e) {

            session = createUserSession(req);
            req.getSession().setAttribute("userSession", session);
            logger.fatal("Ops! an error occurred " + e);
            resp.sendRedirect(I18N.INSTANCE.getURL("error_page", Locale.getDefault()));

        }

        return session;

    }

    protected abstract void handleManageablePageException(T session, Html.Builder htmlBuilder) throws ServletException, IOException;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Html.Builder htmlBuilder = new Html.Builder();

        T session = getSession(req, resp);

        try {

            mayAccessPage(session);

            R model = pageLogic.perform(req, resp, session);

            doGetSub(model, session, htmlBuilder);

            writeHtmlPage(resp, htmlBuilder);

        } catch (NotAuthorizedException e) {

            logger.debug("User: " + session.getUser() + " not authorized to view page " + this.getServletName());
            resp.sendRedirect(I18N.INSTANCE.getURL("not_authorized_page", session.getLocale()));

        } catch (StopServletExecutionException e) {

            logger.debug("Execution stopped of servlet due to need for redirect.");

        } catch (ManageablePageException e) {

            handleManageablePageException(session, htmlBuilder);
            try {
                writeHtmlPage(resp, htmlBuilder);
            } catch (IOException ex) {
                logUnhandledException(e);
                resp.sendRedirect(I18N.INSTANCE.getURL("error_page", session.getLocale()));
            }

        } catch (Exception e) {

            logUnhandledException(e);
            resp.sendRedirect(I18N.INSTANCE.getURL("error_page", session.getLocale()));

        }

    }

    protected abstract void doGetSub(R model, T session, Html.Builder htmlBuilder) throws ServletException, IOException;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Html.Builder htmlBuilder = new Html.Builder();

        T session = getSession(req, resp);

        try {

            mayAccessPage(session);

            R model = pageLogic.perform(req, resp, session);

            doPostSub(model, session, htmlBuilder);

            writeHtmlPage(resp, htmlBuilder);

        } catch (NotAuthorizedException e) {

            logger.debug("User: " + session.getUser() + " not authorized to view page " + this.getServletName());
            resp.sendRedirect(I18N.INSTANCE.getURL("not_authorized_page", session.getLocale()));

        } catch (StopServletExecutionException e) {

            logger.debug("Execution stopped of servlet due to need for redirect.");

        } catch (Exception e) {

            logUnhandledException(e);
            resp.sendRedirect(I18N.INSTANCE.getURL("error_page", session.getLocale()));

        }

    }

    protected abstract void doPostSub(R model, T session, Html.Builder htmlBuilder) throws ServletException, IOException;

    private void writeHtmlPage(HttpServletResponse resp, Html.Builder htmlBuilder) throws IOException {

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.getWriter().print(getHtml(htmlBuilder));
        resp.getWriter().flush();
        resp.getWriter().close();

    }

    private void logUnhandledException(Exception e) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Ops! an error occurred ");
        stringBuilder.append(e);
        stringBuilder.append("\n");

        for (StackTraceElement ste : e.getStackTrace()) {
            stringBuilder.append(ste.toString());
            stringBuilder.append("\n");
        }

        logger.fatal(stringBuilder.toString());

    }

    public abstract ComponentGroup getPageSpecificHtml(R model, T session);

    protected void mayAccessPage(T session) throws NotAuthorizedException {

        User user = session.getUser();
        UserAuthorizationPerformer authPerformer = new UserAuthorizationPerformer(user, accessRoles, denyAccessRoles);

        authPerformer.authorize(UserAuthorizationAction.HAS_VALID_ROLE);

    }


}
