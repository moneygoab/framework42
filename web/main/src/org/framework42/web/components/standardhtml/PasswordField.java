package org.framework42.web.components.standardhtml;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.InputComponentBuilder;
import org.framework42.web.utils.Util;

public class PasswordField extends HtmlComponent {

   private Builder builder;

   private PasswordField(Builder builder) {

      this.builder = builder;

   }

   @Override
   protected void generateHtmlSpecific(HtmlComponent parent, boolean onSameRow) {

      htmlBuilder.append(Util.tab(tabs));
      htmlBuilder.append("<input type=\"password\"");

      htmlBuilder.append(builder.addGeneralComponents());

      if(builder.readonly!=null){
         htmlBuilder.append(" readonly");
      }

      if(builder.maxLength!=null){
         htmlBuilder.append(" maxlength=\"");
         htmlBuilder.append(builder.maxLength);
         htmlBuilder.append("\"");
      }

      htmlBuilder.append(">\n");

      html = htmlBuilder.toString();

   }

   public static class Builder extends InputComponentBuilder {

      private String readonly = null;

      private String maxLength = null;

      public Builder(String name, String value) {
         super(name, value);
      }

      public Builder readonly(boolean readonly){

         if(readonly){
            this.readonly = "readonly";
         }else{
            this.readonly = null;
         }
         return this;

      }

      public Builder maxLength(int maxLength){

         if(maxLength>0){
            this.maxLength = Integer.toString(maxLength);
         }else{
            this.maxLength = null;
         }
         return this;
      }

      @Override
      public PasswordField build() {
         return new PasswordField(this);
      }
   }

}
