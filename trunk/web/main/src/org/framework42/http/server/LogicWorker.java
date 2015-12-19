package org.framework42.http.server;

public abstract class LogicWorker<R extends ResponseData> {

    public abstract void performLogic(HttpServerEnvironment serverEnv, RequestData req, R resp);

}
