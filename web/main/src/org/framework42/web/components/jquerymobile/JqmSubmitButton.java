package org.framework42.web.components.jquerymobile;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.InputComponentBuilder;
import org.framework42.web.pages.WebPage;

public class JqmSubmitButton extends HtmlComponent {

    private Builder builder;

    public JqmSubmitButton(String name, String value) {

        this.builder = new Builder(name, value);
    }

    private JqmSubmitButton(Builder builder) {

        this.builder = builder;

    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        if(builder.imageURL==null) {
            htmlBuilder.append("<input type=\"submit\"");
        } else {
            htmlBuilder.append("<input type=\"image\"");
        }

        if(builder.mini) {
            htmlBuilder.append(" data-mini=\"true\"");
        }

        htmlBuilder.append(builder.addGeneralComponents());

        if(builder.imageURL != null) {

            htmlBuilder.append(" src=\"");
            htmlBuilder.append(builder.imageURL);
            htmlBuilder.append("\"");
        }

        if(builder.style != null) {

            htmlBuilder.append(" style=\"");
            htmlBuilder.append(builder.style);
            htmlBuilder.append("\"");
        }

        if(builder.id != null) {

            htmlBuilder.append(" id=\"");
            htmlBuilder.append(builder.id);
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">"+"\n");

    }

    public static class Builder extends InputComponentBuilder<JqmSubmitButton> {

        private String imageURL = null;

        private String style = null;

        private String id = null;

        private boolean mini = false;

        public Builder(String name, String value) {
            super(name, value);
            this.id = name;
            this.className = "submit";
        }

        public Builder className(String className) {
            this.className = className;
            return this;
        }

        public Builder imageURL(String imageURL) {
            this.imageURL = imageURL;
            return this;
        }

        public Builder style(String style) {
            this.style = style;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder mini(boolean mini) {

            this.mini = mini;
            return this;
        }

        @Override
        public JqmSubmitButton build() {
            return new JqmSubmitButton(this);
        }
    }


}
