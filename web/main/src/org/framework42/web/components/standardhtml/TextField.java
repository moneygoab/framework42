package org.framework42.web.components.standardhtml;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.HtmlComponentInput;
import org.framework42.web.components.InputComponentBuilder;
import org.framework42.web.pagemodel.Parameter;
import org.framework42.web.pages.WebPage;

public class TextField extends HtmlComponent implements HtmlComponentInput {

    private Builder builder;

    public TextField(String idAndName) {
        this.builder = new Builder(idAndName);
    }

    public TextField(String idAndName, String value) {
        this.builder = new Builder(idAndName, value);
    }

    public TextField(String idAndName, int value) {
        this.builder = new Builder(idAndName, value+"");
    }

    public TextField(String id, String name, String value) {
        this.builder = new Builder(id, name, value);
    }

    protected TextField(Builder builder) {
        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<input type=\"text\"");

        htmlBuilder.append(builder.addGeneralComponents());

        if(builder.readonly!=null){
            htmlBuilder.append(" readonly");
        }

        if(builder.maxLength!=null){
            htmlBuilder.append(" maxlength=\"");
            htmlBuilder.append(builder.maxLength);
            htmlBuilder.append("\"");
        }

        if(builder.style!=null){
            htmlBuilder.append(" style=\"");
            htmlBuilder.append(builder.style);
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">\n");

    }

    @Override
    public String getId() {
        return builder.getId();
    }

    @Override
    public Parameter getParameter() {
        return builder.getParameter();
    }

    public static class Builder extends InputComponentBuilder<TextField> {

        private String readonly = null;

        private String maxLength = null;

        private String style = null;

        public Builder(String idAndName) {
            this(idAndName, idAndName, "");
        }

        public Builder(String idAndName, String value) {
            this(idAndName, idAndName, value);
        }

        public Builder(String idAndName, int value) {
            this(idAndName, idAndName, value+"");
        }

        public Builder(String id, String name, String value) {
            super(name, value);
            this.id = id;
        }

        public Builder readonly(boolean readonly){

            if(readonly){
                this.readonly = "readonly";
            }else{
                this.readonly = null;
            }
            return this;

        }

        public Builder maxLength(int maxLength){

            if(maxLength>0){
                this.maxLength = Integer.toString(maxLength);
            }else{
                this.maxLength = null;
            }
            return this;
        }

        public Builder style(String style) {

            this.style = style;
            return this;
        }

        public String getId(){
            return id;
        }

        @Override
        public TextField build() {


            return new TextField(this);
        }
    }


}
