package org.framework42.web.components.standardhtml.head;

public enum MetaName {
   AUTHOR("author"), CONTENT_TYPE("content-type"), DESCRIPTION("description"),
   KEYWORDS("keywords"), GENERATOR("generator"), REVISED("revised"), CHARSET("charset"),
    VIEW_PORT("viewport"),
    NONE("")
    ;

   private final String id;

   private MetaName(String id) {
      this.id = id;
   }

   public String getId() {
      return id;
   }

}
