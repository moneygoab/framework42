package org.framework42.web.components.jquerymobile;

import org.framework42.web.components.EventComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.HtmlComponentStorage;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class JqmNavBar extends HtmlComponent {

    private Builder builder;

    public JqmNavBar(Builder builder) {

        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<div");

        htmlBuilder.append(" data-role=\"navbar\"");

        if(builder.theme != null) {
            htmlBuilder.append(" data-theme=\"");
            htmlBuilder.append(builder.theme);
            htmlBuilder.append("\"");
        }

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

        htmlBuilder.append("<ul>\n");

        for(HtmlComponent comp: builder.childComponents){
            htmlBuilder.append("<li>");
            htmlBuilder.append(comp.getHtml(page, this, false));
            htmlBuilder.append("</li>\n");
        }
        htmlBuilder.append("</ul>\n");

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</div>\n");
    }

    public static class Builder extends EventComponentBuilder<JqmNavBar> implements HtmlComponentStorage<HtmlComponent> {

        private final String id;

        private final List<HtmlComponent> childComponents;

        private String className = null;

        private String title = null;

        private String style = null;

        private String theme = null;

        public Builder(String id) {
            this.id = id;
            this.childComponents = new ArrayList<HtmlComponent>();
        }

        public Builder theme(String theme) {

            this.theme = theme;
            return this;
        }

        @Override
        public JqmNavBar build() {
            return new JqmNavBar(this);
        }

        @Override
        public void add(HtmlComponent htmlComponent) {
            childComponents.add(htmlComponent);
        }
    }
}
