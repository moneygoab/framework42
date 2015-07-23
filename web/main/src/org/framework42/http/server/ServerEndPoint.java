package org.framework42.http.server;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public abstract class ServerEndPoint<R extends RequestData> {

    private final List<String> path;

    private final boolean catchAllBelow;

    private final List<LogicWorker> preRenderLogicList;

    protected ServerEndPoint(List<String> path, List<LogicWorker> preRenderLogicList) {
        this.path = path;
        this.catchAllBelow = false;
        this.preRenderLogicList = preRenderLogicList;
    }

    protected ServerEndPoint(List<String> path, boolean catchAllBelow, List<LogicWorker> preRenderLogicList) {
        this.path = path;
        this.catchAllBelow = catchAllBelow;
        this.preRenderLogicList = preRenderLogicList;
    }

    public List<String> getPath() {
        return path;
    }

    public boolean isCatchAllBelow() {
        return catchAllBelow;
    }

    public List<LogicWorker> getPreRenderLogicList() {
        return preRenderLogicList;
    }

    public abstract void renderEndPointResponse(R req, Map<String,Object> dataMap, PrintWriter out);

}
