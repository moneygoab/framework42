package org.framework42.web.components.standardhtml.head;

import org.framework42.web.components.ComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

public class Title extends HtmlComponent {

    private Builder builder;

    public Title(String label) {

        this.builder = new Builder(label);

    }

    private Title(Builder builder) {

        this.builder = builder;

    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("<title>");
        htmlBuilder.append(builder.label);
        htmlBuilder.append("</title>\n");

        html = htmlBuilder.toString();

    }

    public static class Builder implements ComponentBuilder<Title> {

        private String label;

        public Builder(String label) {
            this.label = label;
        }

        @Override
        public Title build() {
            return new Title(this);
        }

    }

}
