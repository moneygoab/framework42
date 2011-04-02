package org.framework42.web.pagelogic;

import org.framework42.exceptions.ManageableException;
import org.framework42.i18n.I18N;
import org.framework42.web.exceptions.StopServletExecutionException;
import org.framework42.web.pageflow.Flowable;
import org.framework42.web.pageflow.FlowableApp;
import org.framework42.web.pagemodel.*;
import org.framework42.web.session.TabableApp;
import org.framework42.web.session.UserSession;
import org.apache.log4j.Logger;
import org.omg.CORBA.PUBLIC_MEMBER;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * This class represents the base of all page logic.
 * */
public abstract class PageLogic<T extends UserSession, R extends PageModel> {

    protected final Logger logger;

    /**
     * The constructor
     * @param loggerId      The logger getId, used to make it possible to define different log levels in the application.
     * */
    protected PageLogic(String loggerId) {

        logger = Logger.getLogger(loggerId);
    }

    public R setupParameters(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp, T session) {

        R pageModel = createPageModel(req, session);

        setupPageParameters(req, session, pageModel);
        setupPageParametersSpecific(req, session, pageModel);

        setupEnvironmentInformation(servlet, req, session, pageModel);

        addHtmlParameters(req, pageModel, session);

        return pageModel;

    }

    /**
     * Performs the logic of the class.
     * @param servlet                           The servlet
     * @param req                               The http request
     * @param resp                              The http response
     * @param session                           The end users session
     * @throws IOException                      Returns any io exceptions
     * @throws StopServletExecutionException    Returned if the page shouldn't be loaded, but the user rather be redirected.
     * @throws org.framework42.exceptions.ManageableException          Returned if an error occurs that might be handled and the page shown any way.
     * @return Returns a page model for the page.
     * */
    public R perform(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp, T session, R pageModel) throws IOException, StopServletExecutionException, ManageableException {

        performGeneral(req, resp, session, pageModel);

        pageModel = performSpecific(req, resp, session, pageModel);

        postPerformGeneral(req, resp, session, pageModel);

        return pageModel;

    }

    /**
     * Creates a page model of the type of the extending class.
     * @param req           The http request
     * @param session       The users session
     * @return Returns the created page model
     * */
    protected abstract R createPageModel(HttpServletRequest req, T session);

    protected void performGeneral(HttpServletRequest req, HttpServletResponse resp, T session, R pageModel) throws IOException, StopServletExecutionException {

        if(pageModel instanceof Flowable && session instanceof FlowableApp) {

            checkFlowable(resp, session, pageModel);
        }

        if(this instanceof TabablePage) {

            changeTab(req, session, pageModel);
            removeTab(req, resp, session, pageModel);
        }


    }

    protected void postPerformGeneral(HttpServletRequest req, HttpServletResponse resp, T session, R pageModel) throws IOException, StopServletExecutionException {

        if(session instanceof FlowableApp) {

            FlowableApp flowableApp = ((FlowableApp)session);

            flowableApp.setLastPageClassName(pageModel.getClass().getCanonicalName());
            flowableApp.setLastPageAction(pageModel.getCurrentPageAction());
        }

        if(this instanceof TabablePage) {

            saveTabPageModel(session, pageModel);
        }

    }

    private void checkFlowable(HttpServletResponse resp, T session, R pageModel) throws IOException, StopServletExecutionException {

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

    private void changeTab(HttpServletRequest req, T session, R pageModel) {

        if(pageModel.getInParameters().containsKey("tabId") &&
                pageModel instanceof Tabable && session instanceof TabableApp
                && pageModel.getCurrentPageAction().getId() == BasePageAction.ACTIVATE_TAB.getId()) {

            TabableApp tabableApp = ((TabableApp)session);

            for(TabEnvironment tabEnv: tabableApp.getTabEnvironments()) {

                long tabId  = Long.parseLong(pageModel.getInParameters().get("tabId").getValueAsString());
                if( tabEnv.getId() == tabId) {

                    tabableApp.setActiveTabEnvironment(tabEnv);

                }

            }
        }

    }

    private void removeTab(HttpServletRequest req, HttpServletResponse resp, T session, R pageModel) throws IOException, StopServletExecutionException {

        I18N i18n = I18N.INSTANCE;
        Locale locale = session.getLocale();

        if(pageModel.getInParameters().containsKey("tabId") &&
                pageModel instanceof Tabable && session instanceof TabableApp
                && pageModel.getCurrentPageAction().getId() == BasePageAction.REMOVE_TAB.getId()) {

            TabableApp tabableApp = ((TabableApp)session);

            for(TabEnvironment tabEnv: tabableApp.getTabEnvironments()) {

                long tabId  = Long.parseLong(pageModel.getInParameters().get("tabId").getValueAsString());
                if( tabEnv.getId() == tabId) {

                    tabableApp.setActiveTabEnvironment(tabableApp.getTabEnvironments().get(0));
                    tabableApp.getTabEnvironments().remove(tabEnv);

                    resp.sendRedirect(tabableApp.getActiveTabEnvironment().getTabButton().getBuilder().getTabLink().getBuilder().generateHref());
                    throw new StopServletExecutionException();
                }

            }

        }

    }

    private void saveTabPageModel(T session, R pageModel) {

        if(pageModel instanceof Tabable && session instanceof TabableApp) {

            TabableApp tabableApp = ((TabableApp)session);

            tabableApp.getActiveTabEnvironment().setPageModel(pageModel);
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

        pageModel.addPageParameter(new ParameterImpl("action", ParameterType.STRING, false, ""));
        pageModel.addPageParameter(new ParameterImpl("tabId", ParameterType.STRING, false, ""));

    }

    /**
     * Adds the definition of parameters of the page, in other words no values are set.
     * @param req           The http request
     * @param session       The end users session
     * @param pageModel     The page model 
     * */
    protected abstract void setupPageParametersSpecific(HttpServletRequest req, T session, R pageModel);

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

    }

    /**
     * Parses and adds the html parameters that is sent in to the page model
     * @param req       The http request
     * @param pageModel The page model
     * */
    protected void addHtmlParameters(HttpServletRequest req, R pageModel, T session) {

        pageModel.setInParameters(HtmlParametersParser.INSTANCE.parseRequest(req, pageModel, session));

        setUpCurrentPageAction(pageModel);

    }

    private void setUpCurrentPageAction(R pageModel) {

        if(pageModel.getInParameters().containsKey("action")) {

            try {
                pageModel.setCurrentPageAction(
                        BasePageAction.getByIdentifier(pageModel.getInParameters().get("action").getValueAsString())
                );
            } catch(IllegalArgumentException e) {

                pageModel.setCurrentPageAction(
                        new PageActionImpl(0, pageModel.getInParameters().get("action").getValueAsString())
                );
            }
        } else {

            pageModel.setCurrentPageAction(BasePageAction.NONE);
        }

    }

}
