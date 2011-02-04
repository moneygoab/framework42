package org.framework42.web.components.standardhtml;

import org.framework42.web.components.InputComponentBuilder;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;
import org.framework42.web.components.HtmlComponent;

public class SubmitButton extends HtmlComponent {

   private Builder builder;

   private SubmitButton(Builder builder) {

      this.builder = builder;

   }

   @Override
   protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

      htmlBuilder.append(Util.tab(tabs));
      htmlBuilder.append("<input type=\"submit\"");

      htmlBuilder.append(builder.addGeneralComponents());

      if(builder.imageURL!=null){
         htmlBuilder.append(" src=\"");
         htmlBuilder.append(builder.imageURL);
         htmlBuilder.append("\"");
      }

      htmlBuilder.append(">"+"\n");

   }

   public static class Builder extends InputComponentBuilder<SubmitButton> {

      private String imageURL = null;

      public Builder(String name, String value) {
         super(name, value);
      }

      public Builder className(String className){
         this.className = className;
         return this;
      }

      @Override
      public SubmitButton build() {
         return new SubmitButton(this);
      }
   }

}
