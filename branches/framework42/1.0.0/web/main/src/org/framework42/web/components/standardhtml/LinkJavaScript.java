package org.framework42.web.components.standardhtml;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.InputComponentBuilder;
import org.framework42.web.pages.WebPage;

import java.util.List;

public class LinkJavaScript extends HtmlComponent {

    protected Builder builder;

    private LinkJavaScript(Builder builder) {
        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<a href=\"javascript:");
        htmlBuilder.append(builder.javaScript);
        htmlBuilder.append("\"");

        htmlBuilder.append(builder.addGeneralComponents());

        if(builder.accessKey != null){
            htmlBuilder.append(" accesskey=\"");
            htmlBuilder.append(builder.accessKey);
            htmlBuilder.append("\"");
        }

        if(builder.style != null) {
            htmlBuilder.append(" style=\"");
            htmlBuilder.append(builder.style);
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">");
        htmlBuilder.append(builder.linkedComponent.getHtml(page, this, true));
        //htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</a>");

        htmlBuilder.append("\n");

    }

    public static class Builder extends InputComponentBuilder<LinkJavaScript> {

        private String javaScript;

        private List<String> parameters;

        private HtmlComponent linkedComponent;

        private String accessKey = null;

        private String style = null;

        public Builder(String name, String javaScript, List<String> parameters, HtmlComponent linkedComponent) {
            super(name, "");
            this.javaScript = javaScript;
            this.parameters = parameters;
            this.linkedComponent = linkedComponent;
        }

        public Builder javaScript(String javaScript) {
            this.javaScript = javaScript;
            return this;
        }

        public Builder parameters(List<String> parameters) {
            this.parameters = parameters;
            return this;
        }

        public Builder linkedComponent(HtmlComponent linkedComponent) {
            this.linkedComponent = linkedComponent;
            return this;
        }

        public Builder accessKey(String accessKey){
            this.accessKey = accessKey;
            return this;
        }

        public Builder style(String style){
            this.style = style;
            return this;
        }

        @Override
        public LinkJavaScript build() {
            return new LinkJavaScript(this);
        }
    }

}
