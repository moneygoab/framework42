package org.framework42.web.components.js_components;

import org.framework42.web.components.ComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class JavaScript extends HtmlComponent {

    private final Builder builder;

    public JavaScript(Builder builder) {
        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<script language=\"JavaScript\" type=\"text/javascript\">\n");
        for(String row:builder.scriptLines){
            htmlBuilder.append(Util.tab(tabs+1));
            htmlBuilder.append(row);
            htmlBuilder.append("\n");
        }
        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</script>\n");

    }

    public static class Builder implements ComponentBuilder<JavaScript> {

        private List<String> scriptLines;

        public Builder() {
            scriptLines = new ArrayList<String>();
        }

        public Builder addScriptLine(String... scriptLine){

            for(String s: scriptLine) {
                scriptLines.add(s);
            }

            return this;
        }

        @Override
        public JavaScript build() {
            return new JavaScript(this);
        }
    }

}
