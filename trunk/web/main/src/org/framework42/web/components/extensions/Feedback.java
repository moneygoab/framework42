package org.framework42.web.components.extensions;

import org.framework42.web.components.EventComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.utils.Util;

public class Feedback extends HtmlComponent {

    private Builder builder;

    private Feedback(Builder builder) {

        this.builder = builder;

    }

    @Override
    protected void generateHtmlSpecific(HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("<span id=\"");
        htmlBuilder.append(builder.id);
        htmlBuilder.append("\" style=\"");
        htmlBuilder.append(builder.style);
        htmlBuilder.append("\"");

        htmlBuilder.append(builder.addGeneralComponents());

        if (builder.title != null) {
            htmlBuilder.append(" title=\"");
            htmlBuilder.append(builder.title);
            htmlBuilder.append("\"");
        }

        if (builder.className != null) {
            htmlBuilder.append(" class=\"");
            htmlBuilder.append(builder.className);
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">\n");

        htmlBuilder.append(Util.tab(tabs + 1));
        htmlBuilder.append(builder.message);
        htmlBuilder.append("\n");

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</span>\n");

        html = htmlBuilder.toString();

    }

    public static class Builder extends EventComponentBuilder {

        private final String id;

        private final String message;

        private String style = "color:red;";

        private String className = null;

        private String title = null;

        public Builder(String id, String message) {
            this.id = id;
            this.message = message;
        }

        public Builder style(String style) {
            this.style = style;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder className(String className) {
            this.className = className;
            return this;
        }

        @Override
        public Feedback build() {
            return new Feedback(this);
        }

    }


}
