package org.framework42.web.components.standardhtml;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pagemodel.BasePageAction;
import org.framework42.web.pagemodel.PageAction;
import org.framework42.web.pages.WebPage;
import org.framework42.web.components.InputComponentBuilder;

import java.util.HashMap;
import java.util.Map;

public class Link extends HtmlComponent {

    protected Builder builder;

    public Link(String name, String href, String textLabel) {

        this.builder = new Builder(name, href, new HashMap<String, String>(), textLabel);

    }

    public Link(String name, String href, HtmlComponent linkedComponent) {

        this.builder = new Builder(name, href, new HashMap<String, String>(), linkedComponent);

    }

    public Link(String name, String href, Map<String, String> linkParameters, HtmlComponent linkedComponent) {

        this.builder = new Builder(name, href, linkParameters, linkedComponent);

    }

    private Link(Builder builder) {

        this.builder = builder;

    }

    public Builder getBuilder() {
        return builder;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<a href=\"");
        htmlBuilder.append(builder.generateHref());
        if(builder.anchor!=null) {
            htmlBuilder.append("#");
            htmlBuilder.append(builder.anchor);
        }
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

        if(builder.rel != null) {
            htmlBuilder.append(" rel=\"");
            htmlBuilder.append(builder.rel);
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

        private String anchor = null;

        private String accessKey = null;

        private LinkTarget target = null;

        private HtmlComponent linkedComponent = null;

        private String style = null;

        private String followString = null;

        private PageAction linkedPageAction = BasePageAction.NONE;

        private Map<String, String> parameters;

        private String rel = null;

        public Builder(String name, String href, String linkText) {
            this(name, href, new HashMap<String, String>(), new Label(linkText));
        }

        public Builder(String name, String href, Map<String, String> parameters, String linkText) {
            this(name, href, parameters, new Label(linkText));
        }

        public Builder(String name, String href, Map<String, String> parameters, HtmlComponent linkedComponent) {
            super(name, "");
            this.parameters = parameters;
            this.href = href;

            this.linkedComponent = linkedComponent;
        }

        public String generateHref() {

            StringBuilder sb = new StringBuilder();
            sb.append(href);

            boolean hasPageAction = "".equals(linkedPageAction.getIdentifier());

            if(!hasPageAction) {
                sb.append("?action=");
                sb.append(linkedPageAction.getIdentifier());
            }

            boolean first = true;

            for(Map.Entry entry : parameters.entrySet()) {
                if(first && hasPageAction) {
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

        public String getHref() {
            return href;
        }

        public Builder anchor(String anchor) {
            this.anchor = anchor;
            return this;
        }

        public Builder addParameter(String key, String value) {
            parameters.put(key, value);
            return this;
        }

        public Builder removeParameter(String key) {
            parameters.remove(key);
            return this;
        }

        public Builder clearParameters() {
            parameters.clear();
            return this;
        }

        public void setParameters(Map<String, String> parameters) {

            this.parameters = parameters;
        }

        public Map<String, String> getParameters() {
            return parameters;
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

        public Builder rel(String rel) {
            this.rel = rel;
            return this;
        }

        public Builder followString(String followString) {
            this.followString = followString;
            return this;
        }

        public Builder linkedPageAction(PageAction linkedPageAction) {
            this.linkedPageAction = linkedPageAction;
            return this;
        }

        @Override
        public Link build() {
            return new Link(this);
        }

    }

}
