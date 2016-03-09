package org.framework42.http.server;

import org.framework42.http.server.html.body.*;
import org.framework42.http.server.html.ComponentGroup;
import org.framework42.http.server.html.head.*;
import org.framework42.model.MimeType;

import java.util.List;

public class TestEndPoint extends WWWEndPoint<TestResponseData> {

    public TestEndPoint(List<String> path, List<LogicWorker> preRenderLogicList) {
        super(path, preRenderLogicList);
    }

    @Override
    protected String renderHead(RequestData req, TestResponseData resp) {

        Head.Builder head = new Head.Builder();

        head.add(
                new Title("Test titel"),
                new Meta(MetaName.CHARSET, "UTF-8"),
                new Meta(MetaName.AUTHOR, "Fredrik Gustavsson"),
                new Link.Builder("test.css", Rel.STYLESHEET).mimeType(MimeType.CSS).build()
        );

        return head.build().getHtml(new ComponentGroup(head.build()));
    }

    @Override
    protected String renderBody(RequestData req, TestResponseData resp) {

        //sb.append("<h1>Test ext</h1>" + System.currentTimeMillis() + "<br><br>Param ID: "+req.getIntParam("id")+"<br><br>Test ID: "+resp.getTest()+"<br><br><img src=\"img.png\">");

        Body.Builder body = new Body.Builder();

        body.add(
                new H.Builder(H.H1, "Test ext").id("test_id").build(),
                new A.Builder("Test2", "Test länk").onClick("return confirm('Gå till länken?');").build(),
                BR.I1,
                new Img("img.png", "En bild")
        );

        return body.build().getHtml(new ComponentGroup(body.build()));
    }

    @Override
    protected TestResponseData createResponseData() {
        return new TestResponseData();
    }
}
