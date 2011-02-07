package org.framework42.model;

public enum MimeCategory {

   APPLICATION("application"), AUDIO("audio"), IMAGE("image"), MESSAGE("message"), TEXT("text"),
   VIDEO("video"), X_WORLD("x-world");

   private String categoryId;

   private MimeCategory(String categoryId) {
      this.categoryId = categoryId;
   }

   public String getCategoryId() {
      return categoryId;
   }

   @Override
   public String toString() {
      return categoryId;
   }

}
