package org.framework42.web.pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RESTErrorPage extends HttpServlet {

    public RESTErrorPage() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        doCall(request, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        doCall(request, httpServletResponse);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        doCall(request, httpServletResponse);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        doCall(request, httpServletResponse);
    }

    protected void doCall(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        //System.out.println(request.get.getServletContext().getAttribute("javax.servlet.error.message"));
        System.out.println("attribute: "+request.getAttribute("javax.servlet.error.message"));
        System.out.println("parameter: "+request.getParameter("javax.servlet.error.message"));

        //httpServletResponse.getWriter().print(request.getServletContext().getAttribute("javax.servlet.error.message"));
    }

}
