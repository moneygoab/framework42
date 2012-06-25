package org.framework42.web.components.standardhtml;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.InputComponentBuilder;
import org.framework42.web.pages.WebPage;

public class Anchor extends HtmlComponent {

    protected Builder builder;

    public Anchor(Builder builder) {
        this.builder = builder;
    }

    public Anchor(String name, HtmlComponent component) {
        this.builder = new Builder(name, component);
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<a ");
        htmlBuilder.append(builder.addGeneralComponents());
        htmlBuilder.append(">");
        htmlBuilder.append(builder.component.getHtml(page, this, true));
        htmlBuilder.append("</a>");

    }

    public static class Builder extends InputComponentBuilder<Anchor> {

        private HtmlComponent component;

        public Builder(String name, HtmlComponent component) {
            super(name, "");

            this.component = component;
        }

        public Builder component(HtmlComponent component) {

            this.component = component;

            return this;
        }

        @Override
        public Anchor build() {
            return new Anchor(this);
        }


    }

}
