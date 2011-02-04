package org.framework42.web.components.standardhtml;

import org.framework42.web.components.ComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

public class Label extends HtmlComponent {

    private final String label;

    private final boolean bold;

    public Label(String label) {
        this.label = label;
        this.bold = false;
    }

    public Label(String label, boolean bold) {
        this.label = label;
        this.bold = bold;
    }

    private Label(Builder builder) {

        this.label = builder.label;
        this.bold = builder.bold;

    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        if(!onSameRow){
            htmlBuilder.append(Util.tab(tabs));
        }
        if(bold) {
            htmlBuilder.append("<b>");
        }
        htmlBuilder.append(label);
        if(bold) {
            htmlBuilder.append("</b>");
        }
        if(!onSameRow){
            htmlBuilder.append("\n");
        }

    }

    public static class Builder implements ComponentBuilder<Label> {

        private final String label;

        private boolean bold;

        public Builder(String label) {
            this.label = label;
            bold = false;
        }

        public Builder bold(boolean bold) {
            this.bold = bold;
            return this;
        }

        @Override
        public Label build() {
            return new Label(this);
        }
    }


}
