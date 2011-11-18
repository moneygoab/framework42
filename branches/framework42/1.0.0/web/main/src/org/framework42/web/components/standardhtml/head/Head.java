package org.framework42.web.components.standardhtml.head;

import org.framework42.web.components.ComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.HtmlComponentStorage;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class Head extends HtmlComponent {

   private Builder builder;

   public Head(Builder builder) {

      this.builder = builder;

      tabOffset = 0;

   }

   @Override
   protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

      htmlBuilder.append("<head>\n");

      for(HtmlComponent component: builder.components){
         htmlBuilder.append(component.getHtml(page, this, false));
      }

      htmlBuilder.append(Util.tab(tabs));
      htmlBuilder.append("</head>\n\n");

   }

   public static class Builder implements ComponentBuilder<Head>, HtmlComponentStorage<HtmlComponent> {

      private final List<HtmlComponent> components;

      public Builder() {

         this.components = new ArrayList<HtmlComponent>();

      }

      @Override
      public void add(HtmlComponent htmlComponent) {
         components.add(htmlComponent);
      }

      @Override
      public Head build() {
         return new Head(this);
      }
   }

}
