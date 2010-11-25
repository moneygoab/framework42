package org.framework42.web.components;

public enum EncodingType {

   URL_ENCODED("application/;x-www-form-urlencoded"),
   MULTIPART("multipart/form-data"),
   PLAIN("text/plain");

   private String encodingType;

   EncodingType(String encodingType) {
      this.encodingType = encodingType;
   }

   public String getEncodingType() {
      return encodingType;
   }

   @Override
   public String toString() {
      return encodingType;
   }
}
