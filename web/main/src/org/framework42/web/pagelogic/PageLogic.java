package org.framework42.web.pagelogic;

import org.framework42.web.exceptions.ManageablePageException;
import org.framework42.web.exceptions.StopServletExecutionException;
import org.framework42.web.pagemodel.HtmlParametersParser;
import org.framework42.web.pagemodel.PageModel;
import org.framework42.web.session.UserSession;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents the base of all page logic.
 * */
public abstract class PageLogic<T extends UserSession, R extends PageModel> {

    protected final Logger logger;

    protected List<ComponentLogic> componentLogic;

    /**
     * The constructor
     * @param loggerId      The logger id, used to make it possible to define different log levels in the application.
     * */
    protected PageLogic(String loggerId) {

        logger = Logger.getLogger(loggerId);
        componentLogic = new ArrayList<ComponentLogic>();
    }

    /**
     * Performs the logic of the class.
     * @param req                               The http request
     * @param resp                              The http response
     * @param session                           The end users session
     * @throws IOException                      Returns any io exceptions
     * @throws StopServletExecutionException    Returned if the page shouldn't be loaded, but the user rather be redirected.
     * @throws ManageablePageException          Returned if an error occurs that might be handled and the page shown any way.
     * @return Returns a page model for the page.
     * */
    public R perform(HttpServletRequest req, HttpServletResponse resp, T session) throws IOException, StopServletExecutionException, ManageablePageException {

        R pageModel = createPageModel(req, resp, session);

        setupPageParameters(req, session, pageModel);

        addHtmlParameters(req, pageModel);

        return performSpecific(req, resp, session, pageModel);

    }

    /**
     * Creates a page model of the type of the extending class.
     * @param req           The http request
     * @param resp           The http response
     * @param session       The users session
     * @return Returns the created page model
     * */
    protected abstract R createPageModel(HttpServletRequest req, HttpServletResponse resp, T session);

    /**
     * This method defines what logic is actually done in the extending class in the call.
     * @param req                               The http request
     * @param resp                              The http response
     * @param session                           The end users session
     * @param pageModel                         The page model
     * @throws IOException                      Returns any io exceptions
     * @throws StopServletExecutionException    Returned if the page shouldn't be loaded, but the user rather be redirected.
     * @throws ManageablePageException          Returned if an error occurs that might be handled and the page shown any way.
     * @return Returns a page model for the page.
     * */
    protected abstract R performSpecific(HttpServletRequest req, HttpServletResponse resp, T session, R pageModel) throws IOException, StopServletExecutionException, ManageablePageException;

    /**
     * Adds the definition of parameters of the page, in other words no values are set.
     * @param req           The http request
     * @param session       The end users session
     * @param pageModel     The page model 
     * */
    protected abstract void setupPageParameters(HttpServletRequest req, T session, R pageModel);

    /**
     * Parses and adds the html parameters that is sent in to the page model
     * @param req       The http request
     * @param pageModel The page model
     * */
    protected void addHtmlParameters(HttpServletRequest req, R pageModel) {

        pageModel.setInParameters(HtmlParametersParser.INSTANCE.parseRequest(req, pageModel));

    }

}
