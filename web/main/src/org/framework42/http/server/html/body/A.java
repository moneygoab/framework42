package org.framework42.http.server.html.body;

import org.framework42.http.server.html.BaseEventComponentBuilder;
import org.framework42.http.server.html.HtmlComponentImpl;
import org.framework42.http.server.html.Target;
import org.framework42.model.MimeType;
import org.framework42.web.utils.Util;

import java.util.HashMap;
import java.util.Map;

public class A extends HtmlComponentImpl {

    private final Builder builder;

    public A(Builder builder) {
        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<a");

        htmlBuilder.append(" href=\"");
        htmlBuilder.append(builder.generateHref());
        htmlBuilder.append("\"");

        htmlBuilder.append(builder.addGeneralComponents());

        if(builder.hrefLang!=null) {

            htmlBuilder.append(" hreflang=\"");
            htmlBuilder.append(builder.hrefLang);
            htmlBuilder.append("\"");
        }

        if(builder.media!=null) {

            htmlBuilder.append(" media=\"");
            htmlBuilder.append(builder.media);
            htmlBuilder.append("\"");
        }

        if(builder.target!=null) {

            htmlBuilder.append(" target=\"");
            htmlBuilder.append(builder.target);
            htmlBuilder.append("\"");
        }

        if(builder.type!=null) {

            htmlBuilder.append(" type=\"");
            htmlBuilder.append(builder.type.toString());
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">\n");

        htmlBuilder.append(builder.linkComponent.getHtml(this, false));
        htmlBuilder.append("\n");

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</a>\n");
    }

    public static class Builder extends BaseEventComponentBuilder<A> {

        //TODO: Implement download

        protected HtmlComponentImpl linkComponent = null;

        protected Map<String,String> parameters = new HashMap<>();

        protected String href = null;

        protected String hrefLang = null;

        protected String media = null;

        //TODO: Implement rel

        protected String target = null;

        protected MimeType type = null;

        public Builder(String href, String linkText) {
            this.href = href;
            this.linkComponent = new Text(linkText);
        }

        public Builder(String href, HtmlComponentImpl linkComponent) {
            this.href = href;
            this.linkComponent = linkComponent;
        }

        public Builder(String href, Map<String,String> parameters, String linkText) {
            this.href = href;
            this.parameters = parameters;
            this.linkComponent = new Text(linkText);
        }

        public Builder(String href, Map<String,String> parameters, HtmlComponentImpl linkComponent) {
            this.href = href;
            this.parameters = parameters;
            this.linkComponent = linkComponent;
        }

        @Override
        public A build() {
            return new A(this);
        }

        public String generateHref() {

            StringBuilder sb = new StringBuilder();
            sb.append(href);

            boolean hasPageAction = false;
            //boolean hasPageAction = "".equals(linkedPageAction.getIdentifier());

            /*if(!hasPageAction) {
                sb.append("?action=");
                sb.append(linkedPageAction.getIdentifier());
            }*/

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

        public Builder href(String href) {
            this.href = href;
            return this;
        }

        public Builder hrefLang(String hrefLang) {
            this.hrefLang = hrefLang;
            return this;
        }

        public Builder media(String media) {
            this.media = media;
            return this;
        }

        public Builder target(String target) {
            this.target = target;
            return this;
        }

        public Builder target(Target target) {
            this.target = target.getValue();
            return this;
        }

        public Builder type(MimeType type) {
            this.type = type;
            return this;
        }

        public Builder linkComponent(HtmlComponentImpl linkComponent) {
            this.linkComponent = linkComponent;
            return this;
        }

        public Builder linkComponent(String linkText) {
            this.linkComponent = new Text(linkText);
            return this;
        }

        public Builder parameters(Map<String,String> parameters) {
            this.parameters = parameters;
            return this;
        }

    }

}
