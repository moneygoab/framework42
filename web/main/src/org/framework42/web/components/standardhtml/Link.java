package org.framework42.web.components.standardhtml;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;
import org.framework42.web.components.InputComponentBuilder;

import java.util.HashMap;
import java.util.Map;

public class Link extends HtmlComponent {

    private Builder builder;

    public Link(String name, String href, HtmlComponent linkedComponent) {

        this.builder = new Builder(name, href, new HashMap<String, String>(), linkedComponent);

    }

    public Link(String name, String href, Map<String, String> linkParameters, HtmlComponent linkedComponent) {

        this.builder = new Builder(name, href, linkParameters, linkedComponent);

    }

    private Link(Builder builder) {

        this.builder = builder;

    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("<a href=\"");
        htmlBuilder.append(builder.href);
        htmlBuilder.append("\"");

        htmlBuilder.append(builder.addGeneralComponents());

        if(builder.accessKey != null){
            htmlBuilder.append(" accesskey=\"");
            htmlBuilder.append(builder.accessKey);
            htmlBuilder.append("\"");
        }

        if(builder.target != null) {
            htmlBuilder.append(" target=\"");
            htmlBuilder.append(builder.target.getId());
            htmlBuilder.append("\"");
        }

        if(builder.style != null) {
            htmlBuilder.append(" style=\"");
            htmlBuilder.append(builder.style);
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">");
        htmlBuilder.append(builder.linkedComponent.getHtml(page, this, true));
        //htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</a>");

        if(builder.followString != null) {
            htmlBuilder.append(builder.followString);
        }

        htmlBuilder.append("\n");

    }

    public static class Builder extends InputComponentBuilder<Link> {

        private final String href;

        private String accessKey = null;

        private LinkTarget target = null;

        private HtmlComponent linkedComponent = null;

        private String style = null;

        private String followString = null;

        public Builder(String name, String href, Map<String, String> parameters, HtmlComponent linkedComponent) {
            super(name, "");
            this.href = addParameters(href, parameters);

            this.linkedComponent = linkedComponent;
        }

        private String addParameters(String href, Map<String, String> parameters) {

            boolean first = true;
            StringBuilder sb = new StringBuilder();
            sb.append(href);

            for(Map.Entry entry : parameters.entrySet()) {
                if(first) {
                    sb.append("?");
                    first = false;
                } else {
                    sb.append("&");
                }
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
            }

            return sb.toString();

        }

        public Builder accessKey(String accessKey){
            this.accessKey = accessKey;
            return this;
        }

        public Builder target(LinkTarget target){
            this.target = target;
            return this;
        }

        public Builder style(String style){
            this.style = style;
            return this;
        }

        public Builder followString(String followString) {
            this.followString = followString;
            return this;
        }

        @Override
        public Link build() {
            return new Link(this);
        }

    }

}
