package org.framework42.http.server;

import java.util.List;

public abstract class WWWEndPoint extends ServerEndPoint {

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
    protected String renderSpecificEndPointResponse(RequestData req, ResponseData resp) {

        StringBuilder sb = new StringBuilder();

        sb.append(renderHead(req, resp));
        sb.append(renderBody(req, resp));

        return sb.toString();
    }

    protected abstract String renderHead(RequestData req, ResponseData resp);

    protected abstract String renderBody(RequestData req, ResponseData resp);
}
