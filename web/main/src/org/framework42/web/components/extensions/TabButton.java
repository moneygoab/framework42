package org.framework42.web.components.extensions;

import org.framework42.web.components.EventComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.standardhtml.Label;
import org.framework42.web.components.standardhtml.Link;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

public class TabButton extends HtmlComponent {

    private final Builder builder;

    private int id;

    public TabButton(Builder builder) {
        this.builder = builder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        builder.tabLink.getBuilder().removeParameter("apptab");
        builder.tabLink.getBuilder().addParameter("apptab", Integer.toString(id));
        this.id = id;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append(Util.tab(tabs));

        htmlBuilder.append(builder.tabLink.getHtml(page, this, true));

    }

    public Builder getBuilder() {
        return builder;
    }

    public static class Builder extends EventComponentBuilder<TabButton> {

        private Link tabLink;

        private String closeButtonURL;

        private String closeButtonMouseOverURL;

        public Builder(Link tabLink, String closeButtonURL, String closeButtonMouseOverURL) {
            this.tabLink = tabLink;
            this.closeButtonURL = closeButtonURL;
            this.closeButtonMouseOverURL = closeButtonMouseOverURL;
        }

        @Override
        public TabButton build() {
            return new TabButton(this);
        }
    }

}
