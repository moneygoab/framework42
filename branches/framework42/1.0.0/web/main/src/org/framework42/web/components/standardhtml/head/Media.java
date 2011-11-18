package org.framework42.web.components.standardhtml.head;

public enum Media {
   SCREEN("screen"), TTY("tty"), TV("tv"), PROJECTION("projection"), HANDHELD("handheld"),
   PRINT("print"), BRAILLE("braille"), AURAL("aural"), ALL("all");

   private String id;

   private Media(String id) {
      this.id = id;
   }

   public String getId() {
      return id;
   }

}
