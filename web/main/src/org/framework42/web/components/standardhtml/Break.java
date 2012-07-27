package org.framework42.web.components.standardhtml;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

public class Break extends HtmlComponent {

    public final static Break INSTANCE = new Break();

    int numberOfBreaks = 1;

    public Break() {
    }

    public Break(int numberOfBreaks) {

        this.numberOfBreaks = numberOfBreaks;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        for(int i=0;i<numberOfBreaks;i++) {

            htmlBuilder.append("<br>\n");
        }
    }

}
