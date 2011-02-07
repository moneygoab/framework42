package org.framework42.web.components.standardhtml;

import org.framework42.web.components.EventComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

import java.util.List;

public class OrderedList extends HtmlComponent {


   private Builder builder;

   private OrderedList(Builder builder) {

      this.builder = builder;

   }

   @Override
   protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

      htmlBuilder.append("<ol");

      htmlBuilder.append(builder.addGeneralComponents());

      if(builder.className != null){
         htmlBuilder.append(" class=\"");
         htmlBuilder.append(builder.className);
         htmlBuilder.append("\"");
      }

      if(builder.id != null) {
         htmlBuilder.append("id =\"");
         htmlBuilder.append(builder.id);
         htmlBuilder.append("\"");
      }

      htmlBuilder.append(">\n");

      for(HtmlComponent comp: builder.listItems){
         htmlBuilder.append(Util.tab(tabs+1));
         htmlBuilder.append("<li>");
         htmlBuilder.append(comp.getHtml(page, this, false));
         htmlBuilder.append("</li>\n");
      }

      htmlBuilder.append(Util.tab(tabs));
      htmlBuilder.append("</ol>\n");

   }

   public static class Builder extends EventComponentBuilder<OrderedList> {

      private final List<HtmlComponent> listItems;

      private String className = null;

      private String id = null;

      public Builder(List<HtmlComponent> listItems) {
         this.listItems = listItems;
      }

      public Builder className(String className){
         this.className = className;
         return this;
      }

      public Builder id(String id){
         this.id = id;
         return this;
      }

      @Override
      public OrderedList build() {
         return new OrderedList(this);
      }
   }

}
