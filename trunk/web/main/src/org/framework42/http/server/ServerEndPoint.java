package org.framework42.http.server;

import org.framework42.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public abstract class ServerEndPoint <R extends ResponseData> {

    protected final static SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMMM YYYY HH:mm:ss");

    private final List<String> path;

    private final boolean catchAllBelow;

    protected final List<LogicWorker> preRenderLogicList;

    protected ServerEndPoint(List<String> path) {

        this.path = path;
        this.catchAllBelow = false;
        this.preRenderLogicList = new ArrayList<>();
    }

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

    public byte[] renderEndPointResponse(HttpServerEnvironment serverEnv, RequestData req, R resp) {

        StringBuilder sb = new StringBuilder();

        String responseData = renderSpecificEndPointResponse(serverEnv, req, resp);

                sb.append("HTTP/1.1 ");
        sb.append(resp.getResponseCode().getId());
        sb.append(" ");
        sb.append(resp.getResponseCode().getName());
        sb.append("\r\n");

        sb.append("Date: ");
        sb.append(dateFormat.format(DateUtil.stepBack(new Date(), 3600000)));
        sb.append(" GMT\r\n");

        sb.append("Server: ");
        sb.append(serverEnv.getServerName());
        sb.append("\r\n");

        sb.append("Connection: close\r\n");
        sb.append("\r\n");
        sb.append(responseData);
        sb.append("\r\n");

        return sb.toString().getBytes();
    }

    protected abstract String renderSpecificEndPointResponse(HttpServerEnvironment serverEnv, RequestData req, R resp);

    protected abstract R createResponseData();

}
