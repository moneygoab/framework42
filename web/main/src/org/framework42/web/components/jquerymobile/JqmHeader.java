package org.framework42.web.components.jquerymobile;

import org.framework42.web.components.EventComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.HtmlComponentStorage;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class JqmHeader extends HtmlComponent {

    private Builder builder;

    public JqmHeader(Builder builder) {

        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<div id=\"");
        htmlBuilder.append(builder.id);
        htmlBuilder.append("\"");

        htmlBuilder.append(" data-role=\"");
        htmlBuilder.append("header");
        htmlBuilder.append("\"");

        if(builder.className != null){
            htmlBuilder.append(" class=\"");
            htmlBuilder.append(builder.className);
            htmlBuilder.append("\"");
        }

        if(builder.title != null){
            htmlBuilder.append(" title=\"");
            htmlBuilder.append(builder.title);
            htmlBuilder.append("\"");
        }

        if(builder.style != null){
            htmlBuilder.append(" style=\"");
            htmlBuilder.append(builder.style);
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(builder.addGeneralComponents());

        htmlBuilder.append(">\n");

        for(HtmlComponent comp: builder.childComponents){
            htmlBuilder.append(comp.getHtml(page, this, false));
        }

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</div>\n");
    }

    public static class Builder extends EventComponentBuilder<JqmHeader> implements HtmlComponentStorage<HtmlComponent> {

        private final String id;

        private final List<HtmlComponent> childComponents;

        private String className = null;

        private String title = null;

        private String style = null;

        public Builder(String id) {
            this.id = id;
            this.childComponents = new ArrayList<HtmlComponent>();
        }

        @Override
        public JqmHeader build() {
            return new JqmHeader(this);
        }

        @Override
        public void add(HtmlComponent htmlComponent) {
            childComponents.add(htmlComponent);
        }
    }
}
