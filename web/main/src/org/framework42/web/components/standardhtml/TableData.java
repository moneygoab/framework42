package org.framework42.web.components.standardhtml;

import org.framework42.web.components.EventComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

public class TableData extends HtmlComponent {

    private Builder builder;

    private  TableData(Builder builder) {

        this.builder = builder;

    }

    public static TableData EMPTY() {

        TableData.Builder builder = new TableData.Builder(new Label.Builder("&nbsp;").build());

        return builder.build();
    }

    public static TableData EMPTY(String dataWidth) {

        TableData.Builder builder = new TableData.Builder(new Label("&nbsp;")).width(dataWidth);
                
        return builder.build();
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("<td");

        if(builder.colSpan != null) {
            htmlBuilder.append(" colspan=\"");
            htmlBuilder.append(builder.colSpan);
            htmlBuilder.append("\"");
        }

        if(builder.width != null) {
            htmlBuilder.append(" width=\"");
            htmlBuilder.append(builder.width);
            htmlBuilder.append("\"");
        }

        if(builder.className != null) {
            htmlBuilder.append(" class=\"");
            htmlBuilder.append(builder.className);
            htmlBuilder.append("\"");
        }

        if(builder.verticalAlign != null) {
            htmlBuilder.append(" valign=\"");
            htmlBuilder.append(builder.verticalAlign);
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">\n");

        htmlBuilder.append(builder.component.getHtml(page, this, false));

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</td>\n");

    }

    public static class Builder extends EventComponentBuilder<TableData> {

        private final HtmlComponent component;

        private String colSpan = null;

        private String width = null;

        private String className = null;

        private String verticalAlign = null;

        public Builder(HtmlComponent component) {
            this.component = component;
        }

        public Builder colSpan(int span){
            colSpan = span+"";
            return this;
        }

        public Builder width(String width){
            this.width = width;
            return this;
        }

        public Builder className(String className){
            this.className = className;
            return this;
        }

        //TODO: Don't make it a String
        public Builder verticalAlign(String verticalAlign) {
            this.verticalAlign = verticalAlign;
            return this;
        }

        @Override
        public TableData build() {

            return new TableData(this);

        }
    }


}
