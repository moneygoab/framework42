package org.framework42.web.components.jquerymobile;

import org.framework42.web.components.EventComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.HtmlComponentStorage;
import org.framework42.web.components.InputComponentBuilder;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class JqmLink extends HtmlComponent {

    private Builder builder;

    public JqmLink(Builder builder) {

        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<a ");

        htmlBuilder.append("href=\"");
        htmlBuilder.append(builder.href);
        htmlBuilder.append("\"");

        if(builder.icon!=null) {
            htmlBuilder.append(" data-icon=\"");
            htmlBuilder.append(builder.icon);
            htmlBuilder.append("\"");
        }

        if(builder.className != null){
            htmlBuilder.append(" class=\"");
            htmlBuilder.append(builder.className);
            htmlBuilder.append("\"");
        }

        if(builder.title != null){
            htmlBuilder.append(" title=\"");
            htmlBuilder.append(builder.title);
            htmlBuilder.append("\"");
        }

        if(builder.style != null){
            htmlBuilder.append(" style=\"");
            htmlBuilder.append(builder.style);
            htmlBuilder.append("\"");
        }

        //htmlBuilder.append(builder.addGeneralComponents());

        htmlBuilder.append(">");

        htmlBuilder.append(builder.linkText);

        htmlBuilder.append("</a>");
    }

    public static class Builder extends InputComponentBuilder<JqmLink> {

        private String href;

        private String linkText;

        private String className = null;

        private String title = null;

        private String style = null;

        private String icon = null;

        public Builder(String name, String href, String linkText) {
            super(name, "");

            this.href = href;
            this.linkText = linkText;
        }

        public Builder(String name, String href, String linkText, String icon) {
            super(name, "");

            this.href = href;
            this.linkText = linkText;
            this.icon = icon;
        }

        public Builder className(String className) {

            this.className = className;
            return this;
        }

        @Override
        public JqmLink build() {
            return new JqmLink(this);
        }


    }
}
