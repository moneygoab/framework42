package org.framework42.http.server;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class TestEndPoint extends WWWEndPoint {

    public TestEndPoint(List<String> path, List<LogicWorker> preRenderLogicList) {
        super(path, preRenderLogicList);
    }

    @Override
    public String renderEndPointResponse(RequestData req, RequestData data) {

        StringBuilder sb = new StringBuilder();

        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Connection: close\r\n");
        sb.append("\r\n");
        sb.append("<html><head></head><body><h1>Test ext</h1>" + System.currentTimeMillis() + "<br><br>ID: "+data.getIntParam("id")+"<br><br><img src=\"img.png\"></body></html>");

        return sb.toString();
    }

    @Override
    protected void renderHead() {

    }

    @Override
    protected void renderBody() {

    }
}
