package org.framework42.http.server.html.body;

import org.framework42.http.server.html.BaseEventComponentBuilder;
import org.framework42.http.server.html.HtmlComponentImpl;

public class Img extends HtmlComponentImpl {

    private final Builder builder;

    public Img(String src) {
        this.builder = new Builder(src);
    }

    public Img(String src, String alt) {
        this.builder = new Builder(src, alt);
    }

    public Img(Builder builder) {
        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<img src=\"");
        htmlBuilder.append(builder.src);
        htmlBuilder.append("\"");

        if(builder.alt!=null) {
            htmlBuilder.append(" alt=\"");
            htmlBuilder.append(builder.alt);
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(builder.addGeneralComponents());

        if(builder.width>=0) {
            htmlBuilder.append(" width=\"");
            htmlBuilder.append(builder.width);
            htmlBuilder.append("\"");
        }

        if(builder.height>=0) {
            htmlBuilder.append(" height=\"");
            htmlBuilder.append(builder.height);
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">\n");
    }

    public static class Builder extends BaseEventComponentBuilder<Img> {

        private String alt = null;

        //TODO: Implement crossorigin

        private int height = -1;

        //TODO: Implement ismap

        //TODO: Implement longdesc

        private String src = null;

        //TODO: Implement usemap

        private int width = -1;

        public Builder(String src) {
            this.src = src;
        }

        public Builder(String src, String alt) {
            this.src = src;
            this.alt = alt;
        }

        @Override
        public Img build() {
            return new Img(this);
        }

        public Builder alt(String alt) {
            this.alt = alt;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder src(String src) {
            this.src = src;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

    }

}
