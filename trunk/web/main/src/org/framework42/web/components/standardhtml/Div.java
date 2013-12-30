package org.framework42.web.components.standardhtml;

import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;
import org.framework42.web.components.EventComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.HtmlComponentStorage;

import java.util.ArrayList;
import java.util.List;

public class Div extends HtmlComponent {

    public final static Div clear = new Builder().style("clear: both;").build();

    private Builder builder;

    private Div(Builder builder) {

        this.builder = builder;

    }

    public String getId(){
        return builder.id;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<div");

        if(builder.id != null && builder.id.length()>0) {
            htmlBuilder.append(" id=\"");
            htmlBuilder.append(builder.id);
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

        for(HtmlComponent comp: builder.childComponents){
            htmlBuilder.append(comp.getHtml(page, this, false));
        }

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</div>\n");

    }

    public static class Builder extends EventComponentBuilder<Div> implements HtmlComponentStorage<HtmlComponent> {

        private final String id;

        private final List<HtmlComponent> childComponents;

        private String className = null;

        private String title = null;

        private String style = null;

        public Builder() {
            this.id = null;
            this.childComponents = new ArrayList<HtmlComponent>();
        }

        public Builder(String id) {
            this.id = id;
            this.childComponents = new ArrayList<HtmlComponent>();
        }

        public Builder className(String className){
            this.className = className;
            return this;
        }

        public Builder title(String title){
            this.title = title;
            return this;
        }

        public Builder style(String style){
            this.style = style;
            return this;
        }

        public void add(HtmlComponent... htmlComponent) {

            for(HtmlComponent comp: htmlComponent) {

                childComponents.add(comp);
            }
        }

        @Override
        public void add(HtmlComponent htmlComponent) {
            childComponents.add(htmlComponent);
        }

        @Override
        public Div build() {
            return new Div(this);
        }

    }

}
