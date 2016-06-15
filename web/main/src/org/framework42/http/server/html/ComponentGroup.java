package org.framework42.http.server.html;

import org.json.RESTJSONResponse;

import java.util.ArrayList;
import java.util.List;

public class ComponentGroup extends HtmlComponentImpl {

    private final List<HtmlComponent> components;

    public ComponentGroup(HtmlComponent component) {

        List<HtmlComponent> list = new ArrayList<>();

        list.add(component);

        components = list;

    }

    public ComponentGroup(List<HtmlComponent> components) {

        this.components = components;

        this.tabOffset = 0;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        for(HtmlComponent comp: components){
            htmlBuilder.append(comp.getHtml(this, false));
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
            return new ComponentGroup(components);
        }
    }

}
