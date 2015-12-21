package org.framework42.http.server.html.body;

import org.framework42.http.server.html.BaseEventComponentBuilder;
import org.framework42.http.server.html.HtmlComponentImpl;
import org.framework42.web.utils.Util;

public class H extends HtmlComponentImpl {

    public final static String H1 = "1";
    public final static String H2 = "2";
    public final static String H3 = "3";
    public final static String H4 = "4";
    public final static String H5 = "5";
    public final static String H6 = "6";

    private final Builder builder;

    public H(String size, String label) {
        this.builder = new Builder(size, label);
    }

    public H(Builder builder) {
        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<h");
        htmlBuilder.append(builder.size);

        htmlBuilder.append(builder.addGeneralComponents());
        htmlBuilder.append(">");

        htmlBuilder.append(builder.label);

        htmlBuilder.append("</h");
        htmlBuilder.append(builder.size);
        htmlBuilder.append(">\n");

    }

    public static class Builder extends BaseEventComponentBuilder<H> {

        private final String size;

        private final String label;

        public Builder(String size, String label) {
            this.size = size;
            this.label = label;
        }

        @Override
        public H build() {
            return new H(this);
        }
    }

}
