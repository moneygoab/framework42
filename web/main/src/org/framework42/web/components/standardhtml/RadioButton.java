package org.framework42.web.components.standardhtml;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.InputComponentBuilder;
import org.framework42.web.pages.WebPage;

public class RadioButton extends HtmlComponent {

    private Builder builder;

    private RadioButton(Builder builder) {
        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<input type=\"radio\"");

        htmlBuilder.append(builder.addGeneralComponents());

        if(builder.style!=null) {

            htmlBuilder.append(" style=\"");
            htmlBuilder.append(builder.style);
            htmlBuilder.append("\"");
        }

        if(builder.checked){
            htmlBuilder.append(" checked");
        }

        htmlBuilder.append("> ");
        htmlBuilder.append(builder.label);
        htmlBuilder.append("\n");

    }

    public static class Builder extends InputComponentBuilder<RadioButton> {

        private boolean checked;

        private String label;

        private String style = "width: 15px;";

        public Builder(String name, String value, String label) {
            super(name, value);

            this.label = label;
            checked = false;
        }

        public Builder checked(boolean checked) {
            this.checked = checked;
            return this;
        }

        public Builder label(String label) {
            this.label = label;
            return this;
        }

        public Builder style(String style) {
            this.style = style;
            return this;
        }

        @Override
        public RadioButton build() {
            return new RadioButton(this);
        }
    }

}
