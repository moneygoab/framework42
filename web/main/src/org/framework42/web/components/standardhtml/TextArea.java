package org.framework42.web.components.standardhtml;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.InputComponentBuilder;
import org.framework42.web.pages.WebPage;

public class TextArea extends HtmlComponent {

    private Builder builder;

    public TextArea(String name, String value) {

        this.builder = new Builder(name, value);
    }

    private TextArea(Builder builder) {

        this.builder = builder;

    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<textarea");

        htmlBuilder.append(builder.addGeneralComponents());

        if(builder.columns != 0) {
            htmlBuilder.append(" cols=\"");
            htmlBuilder.append(builder.columns);
            htmlBuilder.append("\"");
        }

        if(builder.rows != 0) {
            htmlBuilder.append(" rows=\"");
            htmlBuilder.append(builder.rows);
            htmlBuilder.append("\"");
        }

        if(builder.readonly!=null) {
            htmlBuilder.append(" readonly");
        }

        if(builder.style!=null) {
            htmlBuilder.append(" style=\"");
            htmlBuilder.append(builder.style);
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">");

        htmlBuilder.append(builder.value);

        htmlBuilder.append("</textarea>");
        
    }

    public static class Builder extends InputComponentBuilder<TextArea> {

        private String readonly = null;

        private int columns;

        private int rows;

        private String value;

        private String style;

        public Builder(String name, String value) {
            super(name, "");
            this.value = value;
        }

        public Builder readonly(boolean readonly){

            if(readonly){
                this.readonly = "readonly";
            }else{
                this.readonly = null;
            }
            return this;

        }

        public Builder columns(int columns) {

            this.columns = columns;

            return this;
        }

        public Builder rows(int rows) {
            
            this.rows = rows;

            return this;
        }

        public Builder style(String style) {

            this.style = style;

            return this;
        }

        @Override
        public TextArea build() {
            return new TextArea(this);
        }
    }
}
