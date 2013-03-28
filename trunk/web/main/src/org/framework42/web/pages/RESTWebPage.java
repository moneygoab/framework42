package org.framework42.web.pages;

import org.framework42.model.APIRequestType;
import org.framework42.model.APIResponseType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class RESTWebPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("consumer_key")==null) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        } else {

            int consumerId = getConsumerId(req.getParameter("consumer_key"), APIRequestType.GET);

            if(consumerId>0) {

                APIResponseType responseType = APIResponseType.XML;
                try {
                    if(req.getParameter("response_type")!=null) {

                        responseType = APIResponseType.getByName(req.getParameter("response_type"));
                    }
                } catch(Exception e) {}

                doGetSpecific(req, resp, responseType, consumerId);

            } else {

                resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        }
    }

    protected abstract void doGetSpecific(HttpServletRequest req, HttpServletResponse resp, APIResponseType responseType, int consumerId);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
    }

    protected abstract int getConsumerId(String consumerKey, APIRequestType requestType);

}
