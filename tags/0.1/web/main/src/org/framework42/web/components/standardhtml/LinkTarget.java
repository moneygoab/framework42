package org.framework42.web.components.standardhtml;

public enum LinkTarget {
   BLANK("_blank"), PARENT("_parent"), SELF("_self"), TOP("_top");

   private String id;

   LinkTarget(String id) {
      this.id = id;
   }

   public String getId() {
      return id;
   }

}
