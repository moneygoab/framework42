package org.framework42.web.components.standardhtml;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.EventComponentBuilder;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

public class Headline extends HtmlComponent {

    public final static String H1 = "1";
    public final static String H2 = "2";
    public final static String H3 = "3";
    public final static String H4 = "4";
    public final static String H5 = "5";
    public final static String H6 = "6";

    private Builder builder;

    public Headline(String size, String label) {
        
        builder = new Builder(size, label);

    }

    private Headline(Builder builder) {

        this.builder = builder;

    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("<h");
        htmlBuilder.append(builder.size);

        htmlBuilder.append(builder.addGeneralComponents());

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

        if(builder.style != null){
            htmlBuilder.append(" style=\"");
            htmlBuilder.append(builder.style);
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">\n");
        htmlBuilder.append(Util.tab(tabs+1));
        htmlBuilder.append(builder.label);

        htmlBuilder.append("\n");
        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</h");
        htmlBuilder.append(builder.size);
        htmlBuilder.append(">\n");

    }

    public static class Builder extends EventComponentBuilder<Headline> {

        private final String size;
        private final String label;

        private String className = null;

        private String id = null;

        private String style = null;

        public Builder(String size, String label) {
            this.size = size;
            this.label = label;
        }

        public Builder className(String className){
            this.className = className;
            return this;
        }

        public Builder id(String id){
            this.id = id;
            return this;
        }

        public Builder style(String style){
            this.style = style;
            return this;
        }

        @Override
        public Headline build() {
            return new Headline(this);
        }

    }

}
