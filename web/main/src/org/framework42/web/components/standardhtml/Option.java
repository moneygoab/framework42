package org.framework42.web.components.standardhtml;

import org.framework42.web.components.EventComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

public class Option extends HtmlComponent {

   private Builder builder;

   private Option(Builder builder) {

      this.builder = builder;

   }

   @Override
   protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

      htmlBuilder.append(Util.tab(tabs));
      htmlBuilder.append("<option value=\"");
      htmlBuilder.append(builder.value);
      htmlBuilder.append("\"");

      htmlBuilder.append(builder.addGeneralComponents());

      if(builder.selected != null){
         htmlBuilder.append(" selected");
      }

      if(builder.disabled!=null){
         htmlBuilder.append(" disabled");
      }

      if(builder.label!=null){
         htmlBuilder.append(" label=\"");
         htmlBuilder.append(builder.label);
         htmlBuilder.append("\"");
      }

      htmlBuilder.append(">\n");

      htmlBuilder.append(Util.tab(tabs+1));
      htmlBuilder.append(builder.text);
      htmlBuilder.append("\n");

      htmlBuilder.append(Util.tab(tabs));
      htmlBuilder.append("</option>\n");

   }

   public static class Builder extends EventComponentBuilder<Option> {

      private final String value;

      private final String text;

      private String selected;

      private String disabled;

      private String label;

      public Builder(String value, String text) {
         this.value = value;
         this.text = text;
      }

      public Builder selected(boolean selected){

         if(selected){
            this.selected = "selected";
         }else{
            this.selected = null;
         }
         return this;
      }

      public Builder disabled(boolean disabled){
         if(disabled){
            this.disabled = "disabled";
         }else{
            this.disabled = null;
         }
         return this;
      }

      public Builder label(String label){
         this.label = label;
         return this;
      }

      @Override
      public Option build() {
         return new Option(this);
      }
   }

}

