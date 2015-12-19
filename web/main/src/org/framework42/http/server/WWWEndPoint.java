package org.framework42.http.server;

import java.util.List;

public abstract class WWWEndPoint<R extends ResponseData> extends ServerEndPoint<R> {

    protected WWWEndPoint(List<String> path) {
        super(path);
    }

    protected WWWEndPoint(List<String> path, List<LogicWorker> preRenderLogicList) {
        super(path, preRenderLogicList);
    }

    protected WWWEndPoint(List<String> path, boolean catchAllBelow, List<LogicWorker> preRenderLogicList) {
        super(path, catchAllBelow, preRenderLogicList);
    }

    @Override
    protected String renderSpecificEndPointResponse(HttpServerEnvironment serverEnv, RequestData req, R resp) {

        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>\n");
        sb.append("<html>\n");


        //sb.append("\t<meta charset=\"UTF-8\">\n");

        sb.append(renderHead(req, resp));

        sb.append(renderBody(req, resp));

        sb.append("</html>");

        return sb.toString();
    }

    protected abstract String renderHead(RequestData req, R resp);

    protected abstract String renderBody(RequestData req, R resp);
}
