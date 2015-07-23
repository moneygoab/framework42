package org.framework42.http.server;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class TestEndPoint extends WWWEndPoint {

    public TestEndPoint(List<String> path, List<LogicWorker> preRenderLogicList) {
        super(path, preRenderLogicList);
    }

    @Override
    public void renderEndPointResponse(RequestData req, Map dataMap, PrintWriter out) {

        out.print("HTTP/1.1 200 OK\r\n");
        out.print("Connection: close\r\n");
        out.print("\r\n");
        out.print("<html><head></head><body><h1>Test ext</h1>" + System.currentTimeMillis() + "<br><img src=\"img.png\"></body></html>");
    }
}
