package org.framework42.web.components.standardhtml.head;

import org.framework42.web.components.ComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.MimeType;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

public class HeadLink extends HtmlComponent {

   private Builder builder;

   public HeadLink(Builder builder) {

      this.builder = builder;

   }

   @Override
   protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

      htmlBuilder.append("<link");

      htmlBuilder.append(" rel=\"");
      htmlBuilder.append(builder.relationship.getId());
      htmlBuilder.append("\"");

      htmlBuilder.append(" href=\"");
      htmlBuilder.append(builder.link);
      htmlBuilder.append("\"");

      htmlBuilder.append(" type=\"");
      htmlBuilder.append(builder.mimeType.toString());
      htmlBuilder.append("\"");

      if(builder.media != null){
         htmlBuilder.append(" media=\"");
         htmlBuilder.append(builder.media.getId());
         htmlBuilder.append("\"");
      }

      htmlBuilder.append(">\n");

   }

   public static class Builder implements ComponentBuilder<HeadLink> {

      private final HeadLinkRelationship relationship;

      private final String link;

      private final MimeType mimeType;

      private Media media = null;

      public Builder(HeadLinkRelationship relationship, String link, MimeType mimeType) {
         this.relationship = relationship;
         this.link = link;
         this.mimeType = mimeType;
      }

      public Builder media(Media media){
         this.media = media;
         return this;
      }

      @Override
      public HeadLink build() {
         return new HeadLink(this);
      }

   }

}
