package org.framework42.web.components;

public enum HtmlPostMethod {

   POST("post"), GET("get");

   private final String name;

   private HtmlPostMethod(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   @Override
   public String toString() {
      return name;
   }

}

