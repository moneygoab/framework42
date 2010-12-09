package org.framework42.web.components.standardhtml.head;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;
import org.framework42.web.components.ComponentBuilder;

public class Meta extends HtmlComponent {

   private Builder builder;

   private Meta(Builder builder) {

      this.builder = builder;

   }

   @Override
   protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

      htmlBuilder.append(Util.tab(tabs));
      htmlBuilder.append("<meta");

      htmlBuilder.append(" name=\"");
      htmlBuilder.append(builder.metaName.getId());
      htmlBuilder.append("\"");

      htmlBuilder.append(" content=\"");
      htmlBuilder.append(builder.content);
      htmlBuilder.append("\"");

      if(builder.httpEquivalent != null) {
         htmlBuilder.append(" http-equiv=\"");
         htmlBuilder.append(builder.httpEquivalent.getId());
         htmlBuilder.append("\"");
      }

      htmlBuilder.append(">\n");

      html = htmlBuilder.toString();

   }

   public static class Builder implements ComponentBuilder<Meta> {

      private final MetaName metaName;

      private final String content;

      private HttpEquivalent httpEquivalent = null;

      public Builder(MetaName metaName, String content) {
         this.metaName = metaName;
         this.content = content;
      }

      public Builder httpEquivalent(HttpEquivalent httpEquivalent){
         this.httpEquivalent = httpEquivalent;
         return this;
      }

      @Override
      public Meta build() {
         return new Meta(this);
      }
   }

}
