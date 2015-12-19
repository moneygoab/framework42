package org.framework42.http.server;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class TestEndPoint extends WWWEndPoint {

    public TestEndPoint(List<String> path, List<LogicWorker> preRenderLogicList) {
        super(path, preRenderLogicList);
    }

    @Override
    protected String renderHead(RequestData req, ResponseData resp) {

        return "";
    }

    @Override
    protected String renderBody(RequestData req, ResponseData resp) {

        StringBuilder sb = new StringBuilder();

        sb.append("<html><head></head><body><h1>Test ext</h1>" + System.currentTimeMillis() + "<br><br>ID: "+req.getIntParam("id")+"<br><br><img src=\"img.png\"></body></html>");

        return sb.toString();
    }
}
