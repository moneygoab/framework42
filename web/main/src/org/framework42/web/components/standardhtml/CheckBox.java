package org.framework42.web.components.standardhtml;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.InputComponentBuilder;
import org.framework42.web.pages.WebPage;

public class CheckBox extends HtmlComponent {

    private Builder builder;

    public CheckBox(String name, String value, Label label) {

        this.builder = new Builder(name, value, label);
    }

    public CheckBox(String name, String value, Label label, boolean checked) {

        this.builder = new Builder(name, value, label).checked(checked);
    }

    private CheckBox(Builder builder){

        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<input type=\"checkbox\"");

        htmlBuilder.append(builder.addGeneralComponents());

        if(builder.style!=null) {

            htmlBuilder.append(" style=\"");
            htmlBuilder.append(builder.style);
            htmlBuilder.append("\"");
        }

        if(builder.checked!=null){
            htmlBuilder.append(" ");
            htmlBuilder.append(builder.checked);
        }

        htmlBuilder.append("> ");
        htmlBuilder.append(builder.label.getHtml(page, this, true));
        htmlBuilder.append("\n");

    }

    public static class Builder extends InputComponentBuilder<CheckBox> {

        private final Label label;

        private String checked = null;

        private String style = "width: 15px;";

        public Builder(String name, String value, Label label) {
            super(name, value);
            this.label = label;
        }

        public Builder checked(boolean checked) {

            if(checked){
                this.checked = "checked";
            }else{
                this.checked = null;
            }
            return this;
        }

        public Builder style(String style) {

            this.style = style;
            return this;
        }

        @Override
        public CheckBox build() {
            return new CheckBox(this);
        }



    }


}
