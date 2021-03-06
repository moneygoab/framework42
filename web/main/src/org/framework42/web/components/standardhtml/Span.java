package org.framework42.web.components.standardhtml;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;
import org.framework42.web.components.ComponentBuilder;

public class Span extends HtmlComponent {

    private Builder builder;

    private Span(Builder builder) {

        this.builder = builder;

    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<span");

        if(builder.onload != null) {
            htmlBuilder.append(" onload=\"");
            htmlBuilder.append(builder.onload);
            htmlBuilder.append("\"");
        }

        if(builder.className != null) {
            htmlBuilder.append(" class=\"");
            htmlBuilder.append(builder.className);
            htmlBuilder.append("\"");
        }

        if(builder.id != null) {
            htmlBuilder.append(" id=\"");
            htmlBuilder.append(builder.id);
            htmlBuilder.append("\"");
        }

        if(builder.style != null) {
            htmlBuilder.append(" style=\"");
            htmlBuilder.append(builder.style);
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">\n");

        htmlBuilder.append(builder.htmlComponent.getHtml(page, this, false));

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</span>\n");

    }

    public static class Builder implements ComponentBuilder<Span> {

        private final HtmlComponent htmlComponent;

        private String className = null;

        private String id = null;

        private String style = null;

        private String onload = null;

        public Builder(HtmlComponent htmlComponent) {
            this.htmlComponent = htmlComponent;

        }

        public Builder className(String className){
            this.className = className;
            return this;
        }

        public Builder id(String id){
            this.id = id;
            return this;
        }

        public Builder style(String style) {
            this.style = style;
            return this;
        }

        public Builder onLoad(String onload){
            this.onload = onload;
            return this;
        }


        @Override
        public Span build() {
            return new Span(this);
        }
    }

}

