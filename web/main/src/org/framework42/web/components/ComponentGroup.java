package org.framework42.web.components;

import org.framework42.web.pages.WebPage;

import java.util.ArrayList;
import java.util.List;

public class ComponentGroup extends HtmlComponent {

    private final List<HtmlComponent> components;

    private ComponentGroup(Builder builder) {

        this.components = builder.components;

        this.tabOffset = 0;

    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        for(HtmlComponent comp: components){
            htmlBuilder.append(comp.getHtml(page, this, false));
        }

    }

    public static class Builder implements ComponentBuilder<ComponentGroup>, HtmlComponentStorage<HtmlComponent>{

        private final List<HtmlComponent> components;

        public Builder() {
            this.components = new ArrayList<HtmlComponent>();
        }

        public void add(HtmlComponent... htmlComponent) {

            for(HtmlComponent comp: htmlComponent) {

                components.add(comp);
            }
        }

        @Override
        public void add(HtmlComponent component) {
            components.add(component);
        }

        @Override
        public ComponentGroup build() {
            return new ComponentGroup(this);
        }
    }

}
