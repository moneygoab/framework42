package org.framework42.web.components.standardhtml.head;

import org.framework42.model.MimeType;
import org.framework42.web.components.ComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;

public class HeadScript extends HtmlComponent {

    private Builder builder;

    public HeadScript(Builder builder) {

        this.builder = builder;

    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<script");

        htmlBuilder.append(" type=\"");
        htmlBuilder.append(builder.type.toString());
        htmlBuilder.append("\"");

        if (builder.src != null) {
            htmlBuilder.append("src=\"");
            htmlBuilder.append(builder.src);
            htmlBuilder.append("\"");
        }

        htmlBuilder.append("></script>\n");

    }

    public static class Builder implements ComponentBuilder<HeadScript> {

        private final MimeType type;

        private String src = null;

        public Builder(MimeType type) {
            this.type = type;
        }

        @Override
        public HeadScript build() {
            return new HeadScript(this);
        }

        public Builder src(String src) {
            this.src = src;
            return this;
        }

    }

}
