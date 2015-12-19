package org.framework42.http.server.html;

import org.framework42.web.utils.Util;

public abstract class HtmlComponentImpl implements HtmlComponent {

    protected StringBuilder htmlBuilder;

    protected int tabs = 0;

    protected int tabOffset = 1;

    public HtmlComponentImpl() {
        htmlBuilder = new StringBuilder();
    }

    public String getHtml(HtmlComponentImpl parent) {

        return getHtml(parent, false);
    }

    public String getHtml(HtmlComponentImpl parent, boolean onSameRow) {

        tabs = parent.tabs+tabOffset;
        if(!onSameRow){
            htmlBuilder.append(Util.tab(tabs));
        }

        generateHtmlSpecific(parent, onSameRow);

        return htmlBuilder.toString();
    }

    protected abstract void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow);

    @Override
    public String toString() {
        return htmlBuilder.toString();
    }

}
