package org.framework42.web.components.standardhtml;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.InputComponentBuilder;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

public class CheckBox extends HtmlComponent {

   private Builder builder;

   private CheckBox(Builder builder){

      this.builder = builder;

   }

   @Override
   protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

      htmlBuilder.append(Util.tab(tabs));
      htmlBuilder.append("<input type=\"checkbox\"");

      htmlBuilder.append(builder.addGeneralComponents());

      if(builder.checked!=null){
         htmlBuilder.append(" ");
         htmlBuilder.append(builder.checked);
      }

      htmlBuilder.append("> ");
      htmlBuilder.append(builder.label);
      htmlBuilder.append("\n");

      html = htmlBuilder.toString();

   }

   public static class Builder extends InputComponentBuilder {

      private final Label label;

      private String checked = null;

      public Builder(String name, String value, Label label) {
         super(name, value);
         this.label = label;
      }

      public Builder checked(boolean checked){
         if(checked){
            this.checked = "checked";
         }else{
            this.checked = null;
         }
         return this;
      }

      @Override
      public CheckBox build() {
         return new CheckBox(this);
      }



   }


}
