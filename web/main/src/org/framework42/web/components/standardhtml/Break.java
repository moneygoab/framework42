package org.framework42.web.components.standardhtml;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.utils.Util;

public class Break extends HtmlComponent {

   public Break() {
   }

   @Override
   protected void generateHtmlSpecific(HtmlComponent parent, boolean onSameRow) {

      htmlBuilder.append(Util.tab(tabs));
      htmlBuilder.append("<br>\n");

      html = htmlBuilder.toString();

   }

}
