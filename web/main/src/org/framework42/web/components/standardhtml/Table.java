package org.framework42.web.components.standardhtml;

import org.framework42.web.components.EventComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class Table extends HtmlComponent {

    private EventComponentBuilder<Table> builder;

    private Table(EventComponentBuilder<Table> builder) {
        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        if(builder instanceof Table.Bootstrap){
            generateBootstrap((Table.Bootstrap)builder,page);
        }else{
            generateBuilder((Table.Builder)builder,page);
        }

    }

    private void generateBootstrap(Table.Bootstrap builder,WebPage page){
        htmlBuilder.append("<table");

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

        if(builder.width != null){
            htmlBuilder.append(" width=\"");
            htmlBuilder.append(builder.width);
            htmlBuilder.append("px\"");
        }

        if(builder.style != null){
            htmlBuilder.append(" style=\"");
            htmlBuilder.append(builder.style);
            htmlBuilder.append("\"");
        }

        if(builder.cellSpacing != null) {
            htmlBuilder.append(" cellspacing=\"");
            htmlBuilder.append(builder.cellSpacing);
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">\n");

        if(builder.alternativeHead.length > 0){
            htmlBuilder.append("<thead>\n");
            for(String head:builder.alternativeHead){
                htmlBuilder.append("<th scope='col'>" + head + "</th>\n");
            }
            htmlBuilder.append("</thead>\n");
        }


        htmlBuilder.append("<tbody>");
        for(TableRow row:builder.tableRows){
            htmlBuilder.append(row.getHtml(page, this, false));
            htmlBuilder.append("\n");
        }
        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</tbody>\n");
        htmlBuilder.append("</table>\n");

    }

    private void generateBuilder(Table.Builder builder,WebPage page){

        htmlBuilder.append("<table");

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

        if(builder.width != null){
            htmlBuilder.append(" width=\"");
            htmlBuilder.append(builder.width);
            htmlBuilder.append("px\"");
        }

        if(builder.style != null){
            htmlBuilder.append(" style=\"");
            htmlBuilder.append(builder.style);
            htmlBuilder.append("\"");
        }

        if(builder.cellSpacing != null) {
            htmlBuilder.append(" cellspacing=\"");
            htmlBuilder.append(builder.cellSpacing);
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">\n");

        if(builder.headLines != null) {
            htmlBuilder.append(builder.headLines.getHtml(page, this, false));
        }


        for(TableRow row:builder.tableRows){
            htmlBuilder.append(row.getHtml(page, this, false));
            htmlBuilder.append("\n");
        }
        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</table>\n");

    }


    public static class Bootstrap extends EventComponentBuilder<Table> {

        private final String[] alternativeHead;

        private final List<TableRow> tableRows;

        private String className = null;

        private String id = null;

        private String style = null;

        private String width = null;

        private String cellSpacing = null;

        public Bootstrap() {
            this.alternativeHead = new String[0];
            this.tableRows = new ArrayList<TableRow>();
        }

        public Bootstrap(String ...headLines) {
            this.alternativeHead = headLines;
            this.tableRows = new ArrayList<TableRow>();
        }


        public Bootstrap className(String className){
            this.className = className;
            return this;
        }

        public Bootstrap id(String id){
            this.id = id;
            return this;
        }

        public Bootstrap style(String style){
            this.style = style;
            return this;
        }

        public Bootstrap width(Integer width){
            this.width = width.toString();
            return this;
        }

        public Bootstrap cellSpacing(String cellSpacing) {
            this.cellSpacing = cellSpacing;
            return this;
        }

        @Override
        public Table build() {
            return new Table(this);
        }

        public void add(TableRow tableRow){
            tableRows.add(tableRow);
        }

        public void addFirst(TableRow tableRow){
            tableRows.add(0, tableRow);
        }

    }


    public static class Builder extends EventComponentBuilder<Table> {

        private final TableRow headLines;

        private final List<TableRow> tableRows;

        private String className = null;

        private String id = null;

        private String style = null;

        private String width = null;

        private String cellSpacing = null;

        public Builder() {
            this.headLines = null;
            this.tableRows = new ArrayList<TableRow>();
        }

        public Builder(TableRow headLines) {
            this.headLines = headLines;
            this.tableRows = new ArrayList<TableRow>();
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

        public Builder width(Integer width){
            this.width = width.toString();
            return this;
        }

        public Builder cellSpacing(String cellSpacing) {
            this.cellSpacing = cellSpacing;
            return this;
        }

        @Override
        public Table build() {
            return new Table(this);
        }

        public void add(TableRow tableRow){
            tableRows.add(tableRow);
        }

        public void addFirst(TableRow tableRow){
            tableRows.add(0, tableRow);
        }

    }

}
