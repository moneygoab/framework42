package org.framework42.web.components.standardhtml;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;
import org.framework42.web.components.EventComponentBuilder;

public class Image extends HtmlComponent {

    private Builder builder;

    public Image(String imageURL, String altDescription) {
        this.builder = new Builder(imageURL, altDescription);
    }

    public Image(Builder builder) {

        this.builder = builder;

    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<img");

        htmlBuilder.append(" src=\"");
        htmlBuilder.append(builder.imageURL);
        htmlBuilder.append("\"");

        htmlBuilder.append(" alt=\"");
        htmlBuilder.append(builder.altDescription);
        htmlBuilder.append("\"");

        htmlBuilder.append(builder.addGeneralComponents());

        if(builder.longDescription != null){
            htmlBuilder.append(" longdesc=\"");
            htmlBuilder.append(builder.longDescription);
            htmlBuilder.append("\"");
        }

        if(builder.className != null){
            htmlBuilder.append(" class=\"");
            htmlBuilder.append(builder.className);
            htmlBuilder.append("\"");
        }

        if(builder.id != null){
            htmlBuilder.append(" id=\"");
            htmlBuilder.append(builder.id);
            htmlBuilder.append("\"");
        }

        if(builder.style != null) {
            htmlBuilder.append(" style=\"");
            htmlBuilder.append(builder.style);
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">");
        if(!onSameRow){
            htmlBuilder.append("\n");
        }

    }

    public final static class Builder extends EventComponentBuilder<Image> {

        private final String imageURL;

        private final String altDescription;

        private String longDescription = null;

        private String className = null;

        private String id = null;

        private String style = null;

        public Builder(String imageURL, String altDescription) {
            this.imageURL = imageURL;
            this.altDescription = altDescription;
        }

        public Builder longDescription(String longDescription){
            this.longDescription = longDescription;
            return this;
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

        @Override
        public Image build() {
            return new Image(this);
        }
    }

}
