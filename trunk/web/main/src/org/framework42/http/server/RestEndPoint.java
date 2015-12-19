package org.framework42.http.server;

import org.framework42.utils.DateUtil;

import java.util.Date;
import java.util.List;

import static org.framework42.http.StatusCode.NOT_IMPLEMENTED_501;

public abstract class RestEndPoint extends ServerEndPoint {

    protected RestEndPoint(List<String> path, List<LogicWorker> preRenderLogicList) {
        super(path, preRenderLogicList);
    }

    protected RestEndPoint(List<String> path, boolean catchAllBelow, List<LogicWorker> preRenderLogicList) {
        super(path, catchAllBelow, preRenderLogicList);
    }

    public byte[] renderEndPointResponse(HttpServerEnvironment serverEnv, RequestData req, ResponseDataImpl resp) {

        StringBuilder sb = new StringBuilder();

        String responseData = renderSpecificEndPointResponse(serverEnv, req, resp);

        switch (req.getMethod()) {

            case GET:
                renderEndPointGetResponse(serverEnv, req, resp);
            case POST:
                renderEndPointPostResponse(serverEnv, req, resp);
            case HEAD:
                renderEndPointHeadResponse(serverEnv, req, resp);
            case PUT:
                renderEndPointPutResponse(serverEnv, req, resp);
            case DELETE:
                renderEndPointDeleteResponse(serverEnv, req, resp);
            case CONNECT:
                renderEndPointConnectResponse(serverEnv, req, resp);
            case OPTIONS:
                renderEndPointOptionsResponse(serverEnv, req, resp);
            case TRACE:
                renderEndPointTraceResponse(serverEnv, req, resp);
            break;
        }

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

    protected String renderGeneralNotImplementedResponse(HttpServerEnvironment serverEnvironment) {

        StringBuilder sb = new StringBuilder();

        sb.append("HTTP/1.1 "+ NOT_IMPLEMENTED_501.getId()+" "+NOT_IMPLEMENTED_501.getName()+"\r\n");
        sb.append("Date: "+dateFormat.format(DateUtil.stepBack(new Date(), 3600000))+" GMT\r\n");
        sb.append("Server: "+serverEnvironment.getServerName()+"\r\n");
        sb.append("Connection: close\r\n");

        return sb.toString();
    }

    protected String renderEndPointGetResponse(HttpServerEnvironment serverEnvironment, RequestData req, ResponseDataImpl resp) {

        return renderGeneralNotImplementedResponse(serverEnvironment);
    }

    protected String renderEndPointHeadResponse(HttpServerEnvironment serverEnvironment, RequestData req, ResponseDataImpl resp) {

        return renderGeneralNotImplementedResponse(serverEnvironment);
    }

    protected String renderEndPointPostResponse(HttpServerEnvironment serverEnvironment, RequestData req, ResponseDataImpl resp) {

        return renderGeneralNotImplementedResponse(serverEnvironment);
    }

    protected String renderEndPointPutResponse(HttpServerEnvironment serverEnvironment, RequestData req, ResponseDataImpl resp) {

        return renderGeneralNotImplementedResponse(serverEnvironment);
    }

    protected String renderEndPointDeleteResponse(HttpServerEnvironment serverEnvironment, RequestData req, ResponseDataImpl resp) {

        return renderGeneralNotImplementedResponse(serverEnvironment);
    }

    protected String renderEndPointConnectResponse(HttpServerEnvironment serverEnvironment, RequestData req, ResponseDataImpl resp) {

        return renderGeneralNotImplementedResponse(serverEnvironment);
    }

    protected String renderEndPointOptionsResponse(HttpServerEnvironment serverEnvironment, RequestData req, ResponseDataImpl resp) {

        return renderGeneralNotImplementedResponse(serverEnvironment);
    }

    protected String renderEndPointTraceResponse(HttpServerEnvironment serverEnvironment, RequestData req, ResponseDataImpl resp) {

        return renderGeneralNotImplementedResponse(serverEnvironment);
    }

}
