package org.framework42.web.pagelogic;

import org.framework42.exceptions.ManageableException;
import org.framework42.exceptions.NotAuthorizedException;
import org.framework42.i18n.I18N;
import org.framework42.model.ParameterType;
import org.framework42.web.exceptions.StopServletExecutionException;
import org.framework42.web.pageflow.Flowable;
import org.framework42.web.pageflow.FlowableApp;
import org.framework42.web.pagemodel.*;
import org.framework42.web.session.UserSession;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * This class represents the base of all page logic.
 * */
public abstract class PageLogic<T extends UserSession, R extends PageModel> {

    protected final Logger logger;

    protected List<FormListener<T,R>> formListenerList;

    /**
     * The constructor
     * @param loggerId      The logger getId, used to make it possible to define different log levels in the application.
     * */
    protected PageLogic(String loggerId) {

        logger = Logger.getLogger(loggerId);
        formListenerList = new ArrayList<FormListener<T,R>>();
        addFormListeners();
    }

    public R setupParameters(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp, T session) {

        R pageModel = createPageModel(req, session);

        Object userClientActionMap = servlet.getServletContext().getAttribute("userClientActionMap");
        if(userClientActionMap==null) {
            pageModel.setUserClientActionMap(new HashMap<Integer, UserClientAction>());
        } else {
            pageModel.setUserClientActionMap((Map<Integer, UserClientAction>)userClientActionMap);
        }

        setupPageParameters(req, session, pageModel);

        setupEnvironmentInformation(servlet, req, session, pageModel);

        addHtmlParameters(req, pageModel, session);

        setupPageParametersSpecific(servlet, req, session, pageModel);

        return pageModel;

    }

    /**
     * Performs the logic of the class.
     * @param servlet                           The servlet
     * @param req                               The http request
     * @param resp                              The http response
     * @param session                           The end users session
     * @param pageModel                         The page model
     * @throws IOException                      Returns any io exceptions
     * @throws StopServletExecutionException    Returned if the page shouldn't be loaded, but the user rather be redirected.
     * @throws org.framework42.exceptions.ManageableException          Returned if an error occurs that might be handled and the page shown any way.
     * @return Returns a page model for the page.
     * */
    public R perform(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp, T session, R pageModel) throws IOException, StopServletExecutionException, ManageableException {

        performGeneral(req, resp, session, pageModel);

        pageModel = performSpecific(req, resp, session, pageModel);

        postPerformGeneral(req, resp, session, pageModel);

        servlet.getServletContext().setAttribute("userClientActionMap", pageModel.getUserClientActionMap());

        return pageModel;

    }

    public void performPostRendering(ServletContext context, T session, R pageModel) {

        context.setAttribute("userClientActionMap", pageModel.getUserClientActionMap());
    }

    /**
     * Creates a page model of the type of the extending class.
     * @param req           The http request
     * @param session       The users session
     * @return Returns the created page model
     * */
    protected abstract R createPageModel(HttpServletRequest req, T session);

    protected abstract void addFormListeners();

    @SuppressWarnings("unchecked")
    protected void performGeneral(HttpServletRequest req, HttpServletResponse resp, T session, R pageModel) throws IOException, StopServletExecutionException {

        checkFlowable(resp, session, pageModel);

    }

    protected void postPerformGeneral(HttpServletRequest req, HttpServletResponse resp, T session, R pageModel) throws IOException, StopServletExecutionException, ManageableException {

        handleFormEvent(req, resp, session, pageModel);

        if(session instanceof FlowableApp) {

            FlowableApp flowableApp = ((FlowableApp)session);

            flowableApp.setLastPageClassName(pageModel.getClass().getCanonicalName());
            flowableApp.setLastPageAction(pageModel.getCurrentPageAction());
        }

    }

    private void handleFormEvent(HttpServletRequest req, HttpServletResponse resp, T session, R pageModel) throws StopServletExecutionException, ManageableException {

        if(pageModel.getInParameters().containsKey("form_action")) {

            String formActionId = pageModel.getInParameters().get("form_action").asString();

            for(FormListener<T,R> formListener: formListenerList) {

                try {

                    formListener.tryFormEvent(formActionId, req, resp, session, pageModel);

                } catch(NotAuthorizedException e) {

                    logger.error(e.getMessage());
                    throw new StopServletExecutionException();
                }
            }
        }
    }

    private void checkFlowable(HttpServletResponse resp, T session, R pageModel) throws IOException, StopServletExecutionException {

        if(pageModel instanceof Flowable && session instanceof FlowableApp) {

            I18N i18n = I18N.INSTANCE;
            Locale locale = session.getLocale();

            FlowableApp flowableApp = ((FlowableApp)session);

            List<Flowable> originatingPages = ((Flowable)pageModel).getOriginatingPages(flowableApp.getLastPageAction());
            boolean notInFlow = true;

            if(originatingPages.size() == 0) {
                notInFlow = false;
            } else {
                for(Flowable flowablePage: originatingPages) {

                    String lastPageClass = flowableApp.getLastPageClassName();
                    String pageClass = flowablePage.getClass().getCanonicalName();

                    if(lastPageClass.equals(pageClass)) {

                        notInFlow = false;
                        break;
                    }
                }
            }

            if(notInFlow) {
                resp.sendRedirect(i18n.getURL("error_page", locale));
                throw new StopServletExecutionException();
            }
        }
    }

    /**
     * This method defines what logic is actually done in the extending class in the call.
     * @param req                               The http request
     * @param resp                              The http response
     * @param session                           The end users session
     * @param pageModel                         The page model
     * @throws IOException                      Returns any io exceptions
     * @throws StopServletExecutionException    Returned if the page shouldn't be loaded, but the user rather be redirected.
     * @throws org.framework42.exceptions.ManageableException          Returned if an error occurs that might be handled and the page shown any way.
     * @return Returns a page model for the page.
     * */
    protected abstract R performSpecific(HttpServletRequest req, HttpServletResponse resp, T session, R pageModel) throws IOException, StopServletExecutionException, ManageableException;

    protected void setupPageParameters(HttpServletRequest req, T session, R pageModel) {

        pageModel.addPageParameter(new ParameterImpl<String>("action", ParameterType.STRING, false, ""));
        pageModel.addPageParameter(new ParameterImpl<String>("form_action", ParameterType.STRING, false, ""));
    }

    /**
     * Adds the definition of parameters of the page, in other words no values are set.
     * @param req           The http request
     * @param session       The end users session
     * @param pageModel     The page model 
     * */
    protected abstract void setupPageParametersSpecific(HttpServlet servlet, HttpServletRequest req, T session, R pageModel);

    /**
     * Sets up the environment variables.
     * @param servlet       The servlet
     * @param req           The http request
     * @param session       The end users session
     * @param pageModel     The page model 
     * */
    protected void setupEnvironmentInformation(HttpServlet servlet, HttpServletRequest req, T session, R pageModel) {

        pageModel.getEnvironmentInformation().put("currentPageURI", req.getRequestURI().substring(1));
        pageModel.getEnvironmentInformation().put("localRootDir", servlet.getServletContext().getRealPath("/"));
        pageModel.getEnvironmentInformation().put("userAgentId", req.getHeader("User-Agent"));
        if(req.getHeader( "X-Forwarded-For" )==null) {
            pageModel.getEnvironmentInformation().put("clientIP", req.getRemoteAddr());
        } else {
            pageModel.getEnvironmentInformation().put("clientIP", req.getHeader( "X-Forwarded-For" ));
        }
        if(req.getHeader("Referer")!=null) {

            String referer = req.getHeader("Referer");
            if(referer.startsWith("http://")) {

                referer = referer.replace("http://", "");
            }
            if(referer.startsWith("https://")) {
                referer = referer.replace("https://", "");
            }
            referer = referer.replaceFirst("www.","");

            referer = referer.split("/")[0];

            session.getMiscParameters().put("Referer", referer);
        }
        if(session.getMiscParameters().get("Referer")!=null) {
            pageModel.getEnvironmentInformation().put("referer", session.getMiscParameters().get("Referer").toString());
        }

    }

    /**
     * Parses and adds the html parameters that is sent in to the page model
     * @param req       The http request
     * @param pageModel The page model
     * @param session   The session
     * */
    protected void addHtmlParameters(HttpServletRequest req, R pageModel, T session) {

        pageModel.setInParameters(HtmlParametersParser.INSTANCE.parseRequest(req, pageModel, session));

        setUpCurrentPageAction(pageModel);
    }

    private void setUpCurrentPageAction(R pageModel) {

        if(pageModel.getInParameters().containsKey("action")) {

            pageModel.setCurrentPageAction(
                    new PageActionImpl(0, pageModel.getInParameters().get("action").asString())
            );

        } else {

            pageModel.setCurrentPageAction(BasePageAction.NONE);
        }
    }

}
