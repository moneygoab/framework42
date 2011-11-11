package org.framework42.web.components.standardhtml;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;

public class LinkBookmark extends HtmlComponent {

    protected Builder builder;

    public LinkBookmark(String name, HtmlComponent component) {

        this.builder = new Builder(name, component);
    }

    public LinkBookmark(Builder builder) {

        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<a name=\"");
        htmlBuilder.append(builder.name);
        htmlBuilder.append("\">");

        htmlBuilder.append(builder.component.getHtml(page, parent, true));

        htmlBuilder.append("</a>");
    }

    public static class Builder {

        private String name;

        private HtmlComponent component;

        public Builder(String name, HtmlComponent component) {

            this.name = name;
            this.component = component;
        }

        public Builder name(String name) {

            this.name = name;
            return this;
        }

        public Builder component(HtmlComponent component) {

            this.component = component;
            return this;
        }

    }

}
