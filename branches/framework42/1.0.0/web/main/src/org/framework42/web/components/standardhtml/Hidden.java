package org.framework42.web.components.standardhtml;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.InputComponentBuilder;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

public class Hidden extends HtmlComponent {

    private Builder builder;

    public Hidden(String name, String value) {

        this.builder = new Builder(name, value);
    }

    private Hidden(Builder builder){

        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<input type=\"hidden\"");

        htmlBuilder.append(builder.addGeneralComponents());

        htmlBuilder.append(">\n");

    }

    public static class Builder extends InputComponentBuilder<Hidden> {

        public Builder(String name, String value) {
            super(name, value);
        }

        @Override
        public Hidden build() {
            return new Hidden(this);
        }
    }

}
