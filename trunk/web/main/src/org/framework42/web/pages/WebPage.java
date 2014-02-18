package org.framework42.web.pages;

import org.apache.log4j.Logger;
import org.framework42.authorization.UserAuthAction;
import org.framework42.authorization.UserAuthPerformer;
import org.framework42.exceptions.ManageableException;
import org.framework42.exceptions.NotAuthorizedException;
import org.framework42.exceptions.UserBlockedException;
import org.framework42.i18n.I18N;
import org.framework42.model.users.Role;
import org.framework42.model.users.User;
import org.framework42.useragent_detection.services.UserAgentParser;
import org.framework42.useragent_detection.services.impl.UserAgentParserImpl;
import org.framework42.web.components.ComponentGroup;
import org.framework42.web.components.standardhtml.Html;
import org.framework42.web.exceptions.StopServletExecutionException;
import org.framework42.web.pagelogic.PageLogic;
import org.framework42.web.pagemodel.PageModel;
import org.framework42.web.session.UserSession;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is the base class of all WebPages.
 */
public abstract class WebPage<T extends UserSession, R extends PageModel> extends HttpServlet {

    protected final static Map<String,String> emptyParamList = new HashMap<String, String>();
    protected final static List<Role> emptyRolesList = new ArrayList<Role>();

    protected final Logger logger;

    protected int id = 0;

    protected List<Role> accessRoles;
    protected List<Role> denyAccessRoles;

    protected final PageLogic<T, R> pageLogic;

    protected final static UserAgentParser userAgentEngineParser = new UserAgentParserImpl();

    /**
     * Base constructor that allows everyone to access the page
     *
     * @param loggerId  The log4j logger getId.
     * @param pageLogic The controller for this view if you think in MVC terms
     */
    protected WebPage(String loggerId, PageLogic<T, R> pageLogic) {

        this(loggerId, pageLogic, emptyRolesList, emptyRolesList);
    }

    /**
     * Constructor that lets you configure who should be able to access the page. The Deny access roles take precedence
     * over the access roles, i.e. if a user has both an access and a deny access role it will be denied.
     *
     * @param loggerId        The log4j logger getId.
     * @param pageLogic       The controller for this view if you think in MVC terms
     * @param accessRoles     List of roles that should be able to access the page
     * @param denyAccessRoles List of roles that should be denied to access the page
     */
    protected WebPage(String loggerId, PageLogic<T, R> pageLogic, List<Role> accessRoles, List<Role> denyAccessRoles) {

        this.logger = Logger.getLogger(loggerId);
        this.pageLogic = pageLogic;

        this.accessRoles = accessRoles;
        this.denyAccessRoles = denyAccessRoles;
    }

    public void setUpRoles(List<Role> accessRoles, List<Role> denyAccessRoles) {
        this.accessRoles = accessRoles;
        this.denyAccessRoles = denyAccessRoles;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            processCall(req, resp);

        } catch(ServletException e) {

            if(!e.getMessage().equalsIgnoreCase("Image types should not be handle by servlet!")) {

                throw e;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            processCall(req, resp);

        } catch(ServletException e) {

            if(!e.getMessage().equalsIgnoreCase("Image types should not be handle by servlet!")) {

                throw e;
            }
        }
    }

    protected abstract void doGetSub(R model, T session, Html.Builder htmlBuilder) throws ServletException, IOException;

    private void processCall(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getCharacterEncoding() == null) {
            req.setCharacterEncoding("utf-8");
        }

        logger.debug("=================================================================");
        logger.debug(req.getHeader("accept"));
        logger.debug(req.getHeader("user-agent"));
        logger.debug(req.getRequestURI());
        logger.debug(req.getHeader("referer"));
        logger.debug("=================================================================");

        if(req.getHeader("accept")!=null) {

            if(req.getHeader("accept").contains("image")) {

                if(req.getHeader("accept").equals("image/webp,*/*;q=0.8") || req.getHeader("accept").equals("image/png,image/*;q=0.8,*/*;q=0.5")) {

                    throw new ServletException("Image types should not be handle by servlet!");

                } else {

                    logger.debug(req.getHeader("accept")+"::::"+req.getHeader("user-agent"));
                }
            }
        }

        Html.Builder htmlBuilder = new Html.Builder();

        T session = null;

        try {

            session = getSession(req, resp);

            R model = pageLogic.setupParameters(this, req, resp, session);

            mayAccessPage(session);
            mayAccessPageSpecific(session, model, resp);

            model = pageLogic.perform(this, req, resp, session, model);

            doGetSub(model, session, htmlBuilder);

            writeHtmlPage(resp, htmlBuilder);

            pageLogic.performPostRendering(getServletContext(), session, model);

        } catch (NotAuthorizedException e) {

            logger.debug("User: " + session.getUser() + " not authorized to view page " + this.getServletName());
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);

        } catch (UserBlockedException e) {

            logger.debug(e.getMessage()+" regarding viewing page " + this.getServletName());
            resp.sendRedirect(I18N.INSTANCE.getURL("blocked_page", session.getLocale()));

        }catch (StopServletExecutionException e) {

            logger.debug("Execution stopped of servlet due to need for redirect.");

        } catch (ManageableException e) {

            handleManageablePageException(session, e, htmlBuilder);
            try {

                writeHtmlPage(resp, htmlBuilder);

            } catch (IOException ex) {

                logUnhandledException(e);
                resp.sendRedirect(I18N.INSTANCE.getURL("error_page", session.getLocale()));
            }

        } catch (Exception e) {

            logUnhandledException(e);
            resp.sendRedirect(I18N.INSTANCE.getURL("error_page", I18N.INSTANCE.defaultLocale));
        }

    }

    @SuppressWarnings("unchecked")
    private T getSession(HttpServletRequest req, HttpServletResponse resp) throws IOException, StopServletExecutionException {

        T session;

        try {

            Object sessionAsObject = req.getSession().getAttribute("userSession");

            if (sessionAsObject == null) {

                session = createUserSession(req, resp);
                req.getSession().setAttribute("userSession", session);

            } else if (sessionAsObject instanceof UserSession) {

                session = (T) sessionAsObject;

                if(session.getUser().hasUserRole(Role.UNKNOWN_PERSON)) {

                    Cookie[] cookies = req.getCookies();
                    if(cookies!=null) {
                        for(Cookie c: cookies) {

                            if("keep_logged_in".equalsIgnoreCase(c.getName())) {

                                session = createUserSession(req, resp);
                                req.getSession().setAttribute("userSession", session);
                            }
                        }
                    }
                }

            } else {

                logger.fatal("You have saved an object in the session attribute userSession that dose not " +
                        "inherit UserSession. Faulty object: " + sessionAsObject);
                session = createUserSession(req, resp);
                req.getSession().setAttribute("userSession", session);
            }

        } catch(StopServletExecutionException e) {

            throw new StopServletExecutionException();

        }catch (Exception e) {

            session = createUserSession(req, resp);
            req.getSession().setAttribute("userSession", session);
            logger.fatal("Ops! an error occurred in WebPage.getSession " + e);
            resp.sendRedirect(I18N.INSTANCE.getURL("error_page", I18N.INSTANCE.defaultLocale));

        }

        String userAgent = "";
        if(req.getHeader("user-agent")!=null) {
            userAgent = req.getHeader("user-agent");
        }
        session.setParsedUserAgent(userAgentEngineParser.parse(session.getUser(), userAgent));
        logger.debug(session.getParsedUserAgent().toString());

        return session;

    }

    protected abstract T createUserSession(HttpServletRequest req, HttpServletResponse resp) throws StopServletExecutionException;

    private void mayAccessPage(T session) throws NotAuthorizedException {

        User user = session.getUser();
        UserAuthPerformer authPerformer = new UserAuthPerformer(user, accessRoles, denyAccessRoles);

        authPerformer.authorize(UserAuthAction.HAS_VALID_ROLE, this.getClass().getCanonicalName());
    }

    protected abstract void mayAccessPageSpecific(T session, R pageModel, HttpServletResponse resp) throws IOException, NotAuthorizedException,
            UserBlockedException, StopServletExecutionException;

    private void writeHtmlPage(HttpServletResponse resp, Html.Builder htmlBuilder) throws IOException {

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        PrintWriter writer = resp.getWriter();
        writer.print(getHtml(htmlBuilder));
        writer.flush();
        writer.close();
    }

    /**
     * This method takes an htmlBuilder and simply builds the html code for the page.
     *
     * @param htmlBuilder   The htmlBuilder that should build this page
     * @return String       The html code as a String
     */
    protected String getHtml(Html.Builder htmlBuilder) {

        Html html = htmlBuilder.build();

        return html.getHtml(this, new ComponentGroup.Builder().build(), true);
    }

    protected abstract void handleManageablePageException(T session, ManageableException exception, Html.Builder htmlBuilder) throws ServletException, IOException;

    private void logUnhandledException(Exception e) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("An unhandled error occurred ");
        stringBuilder.append(e);
        stringBuilder.append("\n");

        for (StackTraceElement ste : e.getStackTrace()) {
            stringBuilder.append(ste.toString());
            stringBuilder.append("\n");
        }

        logger.fatal(stringBuilder.toString());
    }

    public abstract ComponentGroup getPageSpecificHtml(R model, T session);

}
