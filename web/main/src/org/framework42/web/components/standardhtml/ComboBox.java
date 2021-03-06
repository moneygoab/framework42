package org.framework42.web.components.standardhtml;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.HtmlComponentInput;
import org.framework42.web.components.InputComponentBuilder;
import org.framework42.web.components.js_components.JavaScript;
import org.framework42.web.pagemodel.Parameter;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

import java.net.URLDecoder;
import java.util.List;

public class ComboBox extends HtmlComponent implements HtmlComponentInput {

    private Builder builder;

    public ComboBox(String name, String value, List<Option> options) {

        this.builder = new Builder(name, value, options);
    }

    private ComboBox(Builder builder) {

        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<select");

        htmlBuilder.append(builder.addGeneralComponents());

        if(builder.multiple!=null){
            htmlBuilder.append(" multiple");
        }

        if(builder.size!=null){
            htmlBuilder.append(" size=\"");
            htmlBuilder.append(builder.size);
            htmlBuilder.append("\"");
        }

        if(builder.style!=null){
            htmlBuilder.append(" style=\"");
            htmlBuilder.append(builder.style);
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">\n");

        for(Option option: builder.options){
            htmlBuilder.append(option.getHtml(page, this, false));
            htmlBuilder.append("\n");
        }

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</select>\n");

    }

    @Override
    public String getId() {
        return builder.getId();
    }

    @Override
    public Parameter getParameter() {
        return builder.getParameter();
    }

    public static class Builder extends InputComponentBuilder<ComboBox> {

        private final List<Option> options;

        private String multiple = null;

        private String size = null;

        private String style = null;

        public Builder(String name, String value, List<Option> options) {
            super(name, value);
            this.options = options;
        }

        public Builder multiple(boolean multiple){
            if(multiple){
                this.multiple = "multiple";
            }else{
                this.multiple = null;
            }
            return this;
        }

        public Builder size(int size){
            if(size>0){
                this.size = Integer.toString(size);
            }else{
                this.size = null;
            }
            return this;
        }

        public Builder style(String style) {
            this.style = style;
            return this;
        }

        public String getId() {

            return id;
        }

        @Override
        public ComboBox build() {


            return new ComboBox(this);
        }
    }

}
