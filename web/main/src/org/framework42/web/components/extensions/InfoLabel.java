package org.framework42.web.components.extensions;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.standardhtml.Label;
import org.framework42.web.components.standardhtml.Span;
import org.framework42.web.pages.WebPage;

public class InfoLabel extends HtmlComponent {

    private final String infoMessage;

    public InfoLabel(String infoMessage) {

        this.infoMessage = infoMessage;

    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append(
                new Span.Builder(new Label(infoMessage)).className("positive_feedback").build().getHtml(page, parent, false)
        );

    }

}
