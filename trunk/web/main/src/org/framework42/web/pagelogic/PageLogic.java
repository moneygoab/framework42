package org.framework42.web.pagelogic;

import org.framework42.exceptions.ManageableException;
import org.framework42.i18n.I18N;
import org.framework42.web.exceptions.StopServletExecutionException;
import org.framework42.web.pagemodel.*;
import org.framework42.web.session.TabableApp;
import org.framework42.web.session.UserSession;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    /**
     * Performs the logic of the class.
     * @param req                               The http request
     * @param resp                              The http response
     * @param session                           The end users session
     * @throws IOException                      Returns any io exceptions
     * @throws StopServletExecutionException    Returned if the page shouldn't be loaded, but the user rather be redirected.
     * @throws org.framework42.exceptions.ManageableException          Returned if an error occurs that might be handled and the page shown any way.
     * @return Returns a page model for the page.
     * */
    public R perform(HttpServletRequest req, HttpServletResponse resp, T session) throws IOException, StopServletExecutionException, ManageableException {

        R pageModel = createPageModel(req, session);

        setupPageParameters(req, session, pageModel);
        setupPageParametersSpecific(req, session, pageModel);

        setupEnvironmentInformation(req, session, pageModel);

        addHtmlParameters(req, pageModel);

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

        if(this instanceof TabablePage) {

            changeTab(req, session, pageModel);
            removeTab(req, resp, session, pageModel);
        }


    }

    protected void postPerformGeneral(HttpServletRequest req, HttpServletResponse resp, T session, R pageModel) throws IOException, StopServletExecutionException {

        if(this instanceof TabablePage) {

            saveTabPageModel(session, pageModel);
        }

    }

    private void changeTab(HttpServletRequest req, T session, R pageModel) {

        if(pageModel.getInParameters().containsKey("tabId") &&
                pageModel instanceof Tabable && session instanceof TabableApp
                && pageModel.getCurrentPageAction().getId() == BasePageAction.ACTIVATE_TAB.getId()) {

            TabableApp tabableApp = ((TabableApp)session);

            for(TabEnvironment tabEnv: tabableApp.getTabEnvironments()) {

                long tabId  = Long.parseLong(pageModel.getInParameters().get("tabId").getParameterValueAsString());
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

                long tabId  = Long.parseLong(pageModel.getInParameters().get("tabId").getParameterValueAsString());
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
     * @param req           The http request
     * @param session       The end users session
     * @param pageModel     The page model 
     * */
    protected void setupEnvironmentInformation(HttpServletRequest req, T session, R pageModel) {

        pageModel.getEnvironmentInformation().put("currentPageURI", req.getRequestURI().substring(1));

    }

    /**
     * Parses and adds the html parameters that is sent in to the page model
     * @param req       The http request
     * @param pageModel The page model
     * */
    protected void addHtmlParameters(HttpServletRequest req, R pageModel) {

        pageModel.setInParameters(HtmlParametersParser.INSTANCE.parseRequest(req, pageModel));

        setUpCurrentPageAction(pageModel);

    }

    private void setUpCurrentPageAction(R pageModel) {

        if(pageModel.getInParameters().containsKey("action")) {

            try {
                pageModel.setCurrentPageAction(
                        BasePageAction.getByIdentifier(pageModel.getInParameters().get("action").getParameterValueAsString())
                );
            } catch(IllegalArgumentException e) {

                pageModel.setCurrentPageAction(
                        new PageActionImpl(0, pageModel.getInParameters().get("action").getParameterValueAsString())
                );
            }
        } else {

            pageModel.setCurrentPageAction(BasePageAction.NONE);
        }

    }

}
