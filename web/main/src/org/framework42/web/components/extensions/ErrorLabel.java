package org.framework42.web.components.extensions;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.standardhtml.Label;
import org.framework42.web.components.standardhtml.Span;
import org.framework42.web.pages.WebPage;

public class ErrorLabel extends HtmlComponent {

    private final String errorMessage;

    public ErrorLabel(String errorMessage) {

        this.errorMessage = errorMessage;

    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append(
                new Span.Builder(new Label(errorMessage)).className("negative_feedback").build().getHtml(page, parent, false)
        );

    }
}
