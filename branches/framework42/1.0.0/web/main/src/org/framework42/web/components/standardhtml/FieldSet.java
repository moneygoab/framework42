package org.framework42.web.components.standardhtml;

import org.framework42.web.components.EventComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.HtmlComponentStorage;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class FieldSet extends HtmlComponent {

    private Builder builder;

    private FieldSet(Builder builder) {
        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<fieldset");

        if(builder.disabled) {

            htmlBuilder.append(" disabled=\"disabled\"");
        }

        if(builder.className!=null) {

            htmlBuilder.append(" className=\"");
            htmlBuilder.append(builder.className);
            htmlBuilder.append("\"");
        }

        if(builder.form!=null) {

            htmlBuilder.append(" form=\"");
            htmlBuilder.append(builder.form);
            htmlBuilder.append("\"");
        }

        if(builder.name!=null) {

            htmlBuilder.append(" name=\"");
            htmlBuilder.append(builder.name);
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(builder.addGeneralComponents());

        htmlBuilder.append(">\n");

        if(builder.legend!=null) {
            htmlBuilder.append(Util.tab(tabs+1));
            htmlBuilder.append("<legend>");
            htmlBuilder.append(builder.legend);
            htmlBuilder.append("</legend>\n");
        }

        for(HtmlComponent comp: builder.childComponents){

            htmlBuilder.append(comp.getHtml(page, this, false));
        }

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</fieldset>\n");

    }

    public static class Builder extends EventComponentBuilder<FieldSet> implements HtmlComponentStorage<HtmlComponent> {

        private String legend = null;

        private final List<HtmlComponent> childComponents;

        private boolean disabled = false;

        private String className = null;

        private String form = null;

        private String name = null;

        public Builder() {
            childComponents = new ArrayList<HtmlComponent>();
        }

        public Builder legend(String legend) {

            this.legend = legend;
            return this;
        }

        /**
         * Specifies that a field set should be disabled
         * @param disabled      True if disabled and false else.
         * @return The builder
         * */
        public Builder disabled(boolean disabled) {

            this.disabled = disabled;
            return this;
        }

        public Builder className(String className) {

            this.className = className;
            return this;
        }

        /**
         * Specifies the name of a form that this filed set is connected to.
         * @param form      The name of the form.
         * @return The builder
         * */
        public Builder form(String form) {

            this.form = form;
            return this;
        }

        /**
         * Specifies the name of this filed set.
         * @param name      The name of the field set.
         * @return The builder
         * */
        public Builder name(String name) {

            this.name = name;
            return this;
        }

        @Override
        public FieldSet build() {
            return new FieldSet(this);
        }

        @Override
        public void add(HtmlComponent component) {
            childComponents.add(component);
        }
    }

}
