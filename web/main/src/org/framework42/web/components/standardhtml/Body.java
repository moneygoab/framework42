package org.framework42.web.components.standardhtml;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.HtmlComponentStorage;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;
import org.framework42.web.components.ComponentBuilder;

import java.util.ArrayList;
import java.util.List;

public class Body extends HtmlComponent {

   private Builder builder;

   public Body(Builder builder) {

      this.builder = builder;

      tabOffset = 0;

   }

   @Override
   protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

      htmlBuilder.append("<body>\n");

      for(HtmlComponent component: builder.components){
         htmlBuilder.append(component.getHtml(page, this, false));
      }

      htmlBuilder.append(Util.tab(tabs));
      htmlBuilder.append("</body>\n");

   }

   public static class Builder implements ComponentBuilder<Body>, HtmlComponentStorage<HtmlComponent> {

      private final List<HtmlComponent> components;

      public Builder() {

         this.components = new ArrayList<HtmlComponent>();

      }

      @Override
      public void add(HtmlComponent htmlComponent) {
         components.add(htmlComponent);
      }

      @Override
      public Body build() {
         return new Body(this);
      }
   }

}
