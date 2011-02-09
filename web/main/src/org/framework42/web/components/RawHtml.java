package org.framework42.web.components;

import org.framework42.web.pages.WebPage;

public class RawHtml extends HtmlComponent {

    private String rawHtml;

    public RawHtml(String rawHtml) {
        this.rawHtml = rawHtml;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append(rawHtml);

    }
}
