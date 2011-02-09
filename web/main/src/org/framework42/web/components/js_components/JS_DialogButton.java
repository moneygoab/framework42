package org.framework42.web.components.js_components;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;
import org.framework42.web.components.ComponentBuilder;

public class JS_DialogButton extends HtmlComponent {

    private Builder builder;

    public JS_DialogButton(Builder builder) {

        this.builder = builder;

    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append(" \"");
        htmlBuilder.append(builder.label);
        htmlBuilder.append("\": function() { \n");
        htmlBuilder.append(Util.tab(tabs+1));
        htmlBuilder.append("  ");
        htmlBuilder.append(builder.action);
        htmlBuilder.append(" \n");
        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append(" }\n");

    }

    public static class Builder implements ComponentBuilder<JS_DialogButton> {

        private final String label;

        private final String action;

        public Builder(String label, String action) {
            this.label = label;
            this.action = action;
        }

        @Override
        public JS_DialogButton build() {
            return new JS_DialogButton(this);
        }
    }

}
