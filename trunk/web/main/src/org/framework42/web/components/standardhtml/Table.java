package org.framework42.web.components.standardhtml;

import org.framework42.web.components.EventComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class Table extends HtmlComponent {

   private Builder builder;

   private Table(Builder builder) {

      this.builder = builder;

   }

   @Override
   protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

      htmlBuilder.append(Util.tab(tabs));
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

      htmlBuilder.append(">\n");

      htmlBuilder.append(builder.headLines.getHtml(page, this, false));

      for(TableRow row:builder.tableRows){
         htmlBuilder.append(row.getHtml(page, this, false));
         htmlBuilder.append("\n");
      }

      htmlBuilder.append(Util.tab(tabs));
      htmlBuilder.append("</table>\n");

   }

   public static class Builder extends EventComponentBuilder<Table> {

      private final TableRow headLines;

      private final List<TableRow> tableRows;

      private String className = null;

      private String id = null;

      private String style = null;

      private String width = null;

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

      @Override
      public Table build() {
         return new Table(this);
      }

      public void add(TableRow tableRow){
         tableRows.add(tableRow);
      }

   }

}
