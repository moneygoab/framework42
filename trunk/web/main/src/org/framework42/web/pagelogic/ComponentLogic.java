package org.framework42.web.pagelogic;

import org.framework42.web.exceptions.StopServletExecutionException;
import org.framework42.web.pagemodel.ComponentModel;
import org.framework42.web.session.UserSession;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class ComponentLogic<T extends UserSession, R extends ComponentModel> {

    protected final Logger logger;

    protected ComponentLogic(String loggerId) {
        logger = Logger.getLogger(loggerId);
    }

    public abstract R perform(HttpServletRequest req, HttpServletResponse resp, T session) throws IOException, StopServletExecutionException;
            
}
