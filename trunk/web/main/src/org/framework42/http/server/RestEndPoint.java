package org.framework42.http.server;

import java.util.List;

public abstract class RestEndPoint extends ServerEndPoint {

    protected RestEndPoint(List<String> path, List<LogicWorker> preRenderLogicList) {
        super(path, preRenderLogicList);
    }

    protected RestEndPoint(List<String> path, boolean catchAllBelow, List<LogicWorker> preRenderLogicList) {
        super(path, catchAllBelow, preRenderLogicList);
    }
}
