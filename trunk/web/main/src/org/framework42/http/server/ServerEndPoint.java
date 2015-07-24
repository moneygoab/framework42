package org.framework42.http.server;

import org.framework42.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.framework42.http.StatusCode.*;

public abstract class ServerEndPoint<R extends RequestData> {

    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMMM YYYY HH:mm:ss");

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

    public abstract String renderEndPointResponse(R req, RequestData data);

    protected String renderGeneralNotImplementedResponse(HttpServerEnvironment serverEnvironment) {

        StringBuilder sb = new StringBuilder();

        sb.append("HTTP/1.1 "+ NOT_IMPLEMENTED_501.getId()+" "+NOT_IMPLEMENTED_501.getName()+"\r\n");
        sb.append("Date: "+dateFormat.format(DateUtil.stepBack(new Date(), 3600000))+" GMT\r\n");
        sb.append("Server: "+serverEnvironment.getServerName()+"\r\n");
        sb.append("Connection: close\r\n");

        return sb.toString();
    }

    protected String renderEndPointGetResponse(HttpServerEnvironment serverEnvironment) {

        return renderGeneralNotImplementedResponse(serverEnvironment);
    }

    private String renderEndPointHeadResponse(HttpServerEnvironment serverEnvironment) {

        return renderGeneralNotImplementedResponse(serverEnvironment);
    }

    private String processPost(HttpServerEnvironment serverEnvironment) {

        return renderGeneralNotImplementedResponse(serverEnvironment);
    }

    private String processPut(HttpServerEnvironment serverEnvironment) {

        return renderGeneralNotImplementedResponse(serverEnvironment);
    }

    private String processDelete(HttpServerEnvironment serverEnvironment) {

        return renderGeneralNotImplementedResponse(serverEnvironment);
    }

    private String processConnect(HttpServerEnvironment serverEnvironment) {

        return renderGeneralNotImplementedResponse(serverEnvironment);
    }

    private String processOptions(HttpServerEnvironment serverEnvironment) {

        return renderGeneralNotImplementedResponse(serverEnvironment);
    }

    private String processTrace(HttpServerEnvironment serverEnvironment) {

        return renderGeneralNotImplementedResponse(serverEnvironment);
    }

}
