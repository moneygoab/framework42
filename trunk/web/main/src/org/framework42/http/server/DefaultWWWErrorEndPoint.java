package org.framework42.http.server;

import java.util.List;

public class DefaultWWWErrorEndPoint extends WWWEndPoint {

    private final String headline;

    private final String text;

    public DefaultWWWErrorEndPoint(List<String> path, String headLine, String text) {
        super(path);

        this.headline = headLine;
        this.text =text;
    }

    @Override
    protected String renderHead(RequestData req, ResponseData resp) {

        return "";
    }

    @Override
    protected String renderBody(RequestData req, ResponseData resp) {

        StringBuilder sb = new StringBuilder();
        sb.append("<h2>");
        sb.append(headline);
        sb.append("</h2>");
        sb.append(text);

        return sb.toString();
    }

}
