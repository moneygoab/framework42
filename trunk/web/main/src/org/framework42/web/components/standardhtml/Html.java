package org.framework42.web.components.standardhtml;

import org.framework42.web.components.ComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.HtmlComponentStorage;
import org.framework42.web.pages.WebPage;

import java.util.ArrayList;
import java.util.List;

public class Html extends HtmlComponent {

   private Builder builder;

   private Html(Builder builder) {
      this.builder = builder;
      tabs = 0;
   }

   @Override
   protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

      htmlBuilder.append("<!DOCTYPE html>\n");
      htmlBuilder.append("<html>\n");

      for(HtmlComponent component: builder.components){
         htmlBuilder.append(component.getHtml(page, this, false));
      }

      htmlBuilder.append("</html>\n");

   }

   public static class Builder implements ComponentBuilder<Html>, HtmlComponentStorage<HtmlComponent> {

      private List<HtmlComponent> components;

      public Builder() {
         components = new ArrayList<HtmlComponent>();
      }

      @Override
      public void add(HtmlComponent htmlComponent) {
         components.add(htmlComponent);
      }

      @Override
      public Html build() {
         return new Html(this);
      }

   }

}
