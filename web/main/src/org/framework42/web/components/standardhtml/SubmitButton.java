package org.framework42.web.components.standardhtml;

import org.framework42.web.components.InputComponentBuilder;
import org.framework42.web.pages.WebPage;
import org.framework42.web.components.HtmlComponent;

public class SubmitButton extends HtmlComponent {

    private Builder builder;

    public SubmitButton(String name, String value) {

        this.builder = new Builder(name, value);
    }

    private SubmitButton(Builder builder) {

        this.builder = builder;

    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        if(builder.imageURL==null) {
            htmlBuilder.append("<input type=\"submit\"");
        } else {
            htmlBuilder.append("<input type=\"image\"");
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

    public static class Builder extends InputComponentBuilder<SubmitButton> {

        private String imageURL = null;

        private String style = null;

        private String id = null;

        public Builder(String name, String value) {
            super(name, value);
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

        @Override
        public SubmitButton build() {
            return new SubmitButton(this);
        }
    }

}
