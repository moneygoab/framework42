package org.framework42.web.components.standardhtml;

import org.framework42.web.components.EventComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class TableRow extends HtmlComponent {

    private Builder builder;

    private TableRow(Builder builder) {

        this.builder = builder;

    }

    public static TableRow EMPTY() {

        TableRow.Builder emptyRow = new Builder();
        emptyRow.add(new TableData.Builder(new Label.Builder("&nbsp;").build()).build());

        return emptyRow.build();

    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<tr");

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

        for(HtmlComponent comp:builder.rowComponents){

            htmlBuilder.append(comp.getHtml(page, this, false));

        }

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</tr>\n");

    }

    public static class Builder extends EventComponentBuilder<TableRow> {

        private final List<TableData> rowComponents;

        private String className = null;

        private String id = null;

        private String style = null;

        public Builder() {
            this.rowComponents = new ArrayList<TableData>();
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

        public void add(TableData rowComponent) {
            rowComponents.add(rowComponent);
        }

        public void add(HtmlComponent rowComponent) {
            rowComponents.add(new TableData(rowComponent));
        }

        @Override
        public TableRow build() {
            return new TableRow(this);
        }
    }

}
