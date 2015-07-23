package org.framework42.http.server;

import java.util.List;

public abstract class WWWEndPoint extends ServerEndPoint {

    protected WWWEndPoint(List<String> path, List<LogicWorker> preRenderLogicList) {
        super(path, preRenderLogicList);
    }

    protected WWWEndPoint(List<String> path, boolean catchAllBelow, List<LogicWorker> preRenderLogicList) {
        super(path, catchAllBelow, preRenderLogicList);
    }

    protected abstract void renderHead();

    protected abstract void renderBody();
}
