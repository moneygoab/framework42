package org.framework42.http.server;

import org.framework42.http.server.html.ComponentGroup;
import org.framework42.http.server.html.head.*;

import java.util.Arrays;
import java.util.List;

public class TestEndPoint extends WWWEndPoint<TestResponseData> {

    public TestEndPoint(List<String> path, List<LogicWorker> preRenderLogicList) {
        super(path, preRenderLogicList);
    }

    @Override
    protected String renderHead(RequestData req, TestResponseData resp) {

        Head.Builder head = new Head.Builder();

        head.add(new Title("Test titel"));
        head.add(new Meta(MetaName.CHARSET, "UTF-8"));
        head.add(new Meta(MetaName.AUTHOR, "Fredrik Gustavsson"));
        head.add(new Link.Builder("test.css", Rel.STYLESHEET).build());

        return head.build().getHtml(new ComponentGroup(head.build()));
    }

    @Override
    protected String renderBody(RequestData req, TestResponseData resp) {

        StringBuilder sb = new StringBuilder();

        sb.append("<h1>Test ext</h1>" + System.currentTimeMillis() + "<br><br>Param ID: "+req.getIntParam("id")+"<br><br>Test ID: "+resp.getTest()+"<br><br><img src=\"img.png\">");

        return sb.toString();
    }

    @Override
    protected TestResponseData createResponseData() {
        return new TestResponseData();
    }
}
