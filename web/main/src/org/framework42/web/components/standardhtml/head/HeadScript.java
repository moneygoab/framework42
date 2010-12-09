package org.framework42.web.components.standardhtml.head;

import org.framework42.web.components.ComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.MimeType;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

public class HeadScript extends HtmlComponent {

    private Builder builder;

    public HeadScript(Builder builder) {

        this.builder = builder;

    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(Util.tab(tabs));
        stringBuilder.append("<script");

        stringBuilder.append(" type=\"");
        stringBuilder.append(builder.type.toString());
        stringBuilder.append("\"");

        if (builder.src != null) {
            stringBuilder.append("src=\"");
            stringBuilder.append(builder.src);
            stringBuilder.append("\"");
        }

        stringBuilder.append("></script>\n");

        html = stringBuilder.toString();

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
