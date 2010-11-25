package org.framework42.web.components.standardhtml;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.utils.Util;
import org.framework42.web.components.ComponentBuilder;

public class Span extends HtmlComponent {

   private Builder builder;

   public Span(Builder builder) {

      this.builder = builder;

   }

   @Override
   protected void generateHtmlSpecific(HtmlComponent parent, boolean onSameRow) {

      htmlBuilder.append(Util.tab(tabs));
      htmlBuilder.append("<span");

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

      htmlBuilder.append(">\n");

      htmlBuilder.append(builder.htmlComponent.getHtml(this, false));

      htmlBuilder.append(Util.tab(tabs));
      htmlBuilder.append("</span>\n");

      html = htmlBuilder.toString();

   }

   public static class Builder implements ComponentBuilder<Span> {

      private final HtmlComponent htmlComponent;

      private String className = null;

      private String id = null;

      public Builder(HtmlComponent htmlComponent) {
         this.htmlComponent = htmlComponent;

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
      public Span build() {
         return new Span(this);
      }
   }

}
