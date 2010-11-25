package org.framework42.web.components;

import org.apache.log4j.Logger;

public abstract class HtmlComponent {

    protected String html = null;

    protected StringBuilder htmlBuilder;

    protected int tabs = 0;

    protected int tabOffset = 1;

    protected final Logger logger = Logger.getLogger("com.nummer42.poeter");

    protected HtmlComponent() {
        htmlBuilder = new StringBuilder();
    }

    public String getHtml(HtmlComponent parent, boolean onSameRow) {
        tabs = parent.tabs+tabOffset;
        generateHtmlSpecific(parent, onSameRow);
        return html;
    }

    protected abstract void generateHtmlSpecific(HtmlComponent parent, boolean onSameRow);

    @Override
    public String toString() {
        return html;
    }

}
