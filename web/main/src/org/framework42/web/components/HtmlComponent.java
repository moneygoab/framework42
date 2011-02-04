package org.framework42.web.components;

import org.apache.log4j.Logger;
import org.framework42.web.pages.WebPage;

public abstract class HtmlComponent {

    protected String html = null;

    protected StringBuilder htmlBuilder;

    protected int tabs = 0;

    protected int tabOffset = 1;

    protected final Logger logger = Logger.getLogger("com.nummer42.poeter");

    protected HtmlComponent() {
        htmlBuilder = new StringBuilder();
    }

    public String getHtml(WebPage page, HtmlComponent parent, boolean onSameRow) {
        htmlBuilder = new StringBuilder();
        tabs = parent.tabs+tabOffset;
        generateHtmlSpecific(page, parent, onSameRow);
        html = htmlBuilder.toString();
        return html;
    }

    protected abstract void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow);

    @Override
    public String toString() {
        return html;
    }

}
