package org.framework42.http.server.html.body;

import org.framework42.http.server.html.HtmlComponentImpl;

public class Text extends HtmlComponentImpl {

    private final String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append(text);
    }
}
