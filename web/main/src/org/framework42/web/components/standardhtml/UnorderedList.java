package org.framework42.web.components.standardhtml;

import org.framework42.web.components.EventComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

import java.util.List;

public class UnorderedList extends HtmlComponent {

    private Builder builder;

    public UnorderedList(Builder builder) {

        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<ul");

        htmlBuilder.append(builder.addGeneralComponents());

        if(builder.className != null){
            htmlBuilder.append(" class=\"");
            htmlBuilder.append(builder.className);
            htmlBuilder.append("\"");
        }

        if(builder.id != null) {
            htmlBuilder.append("id =\"");
            htmlBuilder.append(builder.id);
            htmlBuilder.append("\"");
        }

        if(builder.style != null) {
            htmlBuilder.append("style =\"");
            htmlBuilder.append(builder.style);
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">\n");

        for(HtmlComponent comp: builder.listItems){
            htmlBuilder.append(Util.tab(tabs + 1));
            htmlBuilder.append("<li>");
            htmlBuilder.append(comp.getHtml(page, this, false));
            htmlBuilder.append("</li>\n");
        }

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</ul>\n");

    }

    public static class Builder extends EventComponentBuilder<UnorderedList> {

        private final List<HtmlComponent> listItems;

        private String className = null;

        private String id = null;

        private String style = null;

        public Builder(List<HtmlComponent> listItems) {
            this.listItems = listItems;
        }

        public Builder className(String className){
            this.className = className;
            return this;
        }

        public Builder id(String id){
            this.id = id;
            return this;
        }

        public Builder style(String style) {
            this.style = style;
            return this;
        }

        @Override
        public UnorderedList build() {
            return new UnorderedList(this);
        }
    }

}
