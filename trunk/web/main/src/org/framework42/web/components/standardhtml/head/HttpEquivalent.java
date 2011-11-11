package org.framework42.web.components.standardhtml.head;

public enum HttpEquivalent {
   CONTENT_TYPE("content-type"), CONTENT_STYLE_TYPE("content-style-type"),
   EXPIRES("expires"), REFRESH("refresh"), SET_COOKIE("set-cookie"), X_UA_COMPATIBLE("X-UA-Compatible");

   private final String id;

   HttpEquivalent(String id) {
      this.id = id;
   }

   public String getId() {
      return id;
   }

}
