package org.framework42.web.components.jquerymobile;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.InputComponentBuilder;
import org.framework42.web.pages.WebPage;

public class JqmButton extends HtmlComponent {

    private Builder builder;

    public JqmButton(Builder builder) {

        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<a ");

        htmlBuilder.append("href=\"");
        htmlBuilder.append(builder.href);
        htmlBuilder.append("\"");

        htmlBuilder.append(" data-role=\"button\"");

        if(builder.inline) {
            htmlBuilder.append(" data-inline=\"true\"");
        }

        if(builder.mini) {
            htmlBuilder.append(" data-mini=\"true\"");
        }

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

        htmlBuilder.append(" id=\"");
        htmlBuilder.append(builder.name);
        htmlBuilder.append("\"");

        //htmlBuilder.append(builder.addGeneralComponents());

        htmlBuilder.append(">");

        htmlBuilder.append(builder.linkText);

        htmlBuilder.append("</a>");
    }

    public static class Builder extends InputComponentBuilder<JqmButton> {

        private String href;

        private String linkText;

        private String className = null;

        private String title = null;

        private String style = null;

        private String icon = null;

        private boolean inline = false;

        private boolean mini = false;

        private String name;

        public Builder(String name, String href, String linkText) {
            super(name, "");

            this.name = name;
            this.href = href;
            this.linkText = linkText;
        }

        public Builder(String name, String href, String linkText, String icon) {
            super(name, "");

            this.name = name;
            this.href = href;
            this.linkText = linkText;
            this.icon = icon;
        }

        public Builder className(String className) {

            this.className = className;
            return this;
        }

        public Builder style(String style) {

            this.style = style;
            return this;
        }

        public Builder inline(boolean inline) {

            this.inline = inline;
            return this;
        }

        public Builder mini(boolean mini) {

            this.mini = mini;
            return this;
        }

        @Override
        public JqmButton build() {
            return new JqmButton(this);
        }

    }
}
