package org.framework42.web.components.standardhtml;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

public class Break extends HtmlComponent {

    public final static Break I1 = new Break();
    public final static Break I2 = new Break(2);
    public final static Break I3 = new Break(3);
    public final static Break I4 = new Break(4);
    public final static Break I5 = new Break(5);
    public final static Break I6 = new Break(6);

    int numberOfBreaks = 1;

    public Break() {
    }

    public Break(int numberOfBreaks) {

        this.numberOfBreaks = numberOfBreaks;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        for(int i=0;i<numberOfBreaks;i++) {

            htmlBuilder.append("<br>");
        }
        htmlBuilder.append("\n");
    }

}
