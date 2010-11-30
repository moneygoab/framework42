package org.framework42.web.components.standardhtml;

import org.framework42.web.components.ComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.utils.Util;

public class Label extends HtmlComponent {

    private final String label;

    public Label(String label) {
        this.label = label;
    }

    private Label(Builder builder) {

        this.label = builder.label;

    }

    @Override
    protected void generateHtmlSpecific(HtmlComponent parent, boolean onSameRow) {

        StringBuilder htmlBuilder = new StringBuilder();

        if(!onSameRow){
            htmlBuilder.append(Util.tab(tabs));
        }
        htmlBuilder.append(label);
        if(!onSameRow){
            htmlBuilder.append("\n");
        }

        html = htmlBuilder.toString();

    }

    public static class Builder implements ComponentBuilder<Label> {

        private final String label;

        public Builder(String label) {
            this.label = label;
        }

        @Override
        public Label build() {
            return new Label(this);
        }
    }


}
