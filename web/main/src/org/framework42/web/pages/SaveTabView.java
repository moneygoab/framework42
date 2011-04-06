package org.framework42.web.pages;

import org.framework42.web.pagemodel.TabEnvironment;
import org.framework42.web.session.TabableApp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

public class SaveTabView extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Object sessionAsObject = req.getSession().getAttribute("userSession");

        if(sessionAsObject instanceof TabableApp) {

            System.out.println(req.getParameter("tabId")+" save tab view.");
            //System.out.println(req.getParameter("pageData"));

            TabEnvironment tabEnvironment = ((TabableApp)sessionAsObject).getActiveTabEnvironment();
            tabEnvironment.setSavedPageContentState(URLDecoder.decode(req.getParameter("pageData"), "UTF-8"));

        }

    }
}
