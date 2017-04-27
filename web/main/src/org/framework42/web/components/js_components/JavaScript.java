package org.framework42.web.components.js_components;

import org.framework42.web.components.ComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JavaScript extends HtmlComponent {

    private final Builder builder;
    private final String scriptFile;

    public JavaScript(Builder builder) {
        scriptFile = "";
        this.builder = builder;
    }

    public JavaScript(String script){
        builder= null;
        scriptFile = script;
    }

    public JavaScript(String path,Map<String,String> populate)throws IOException {
        builder= null;
        byte[] encode = Files.readAllBytes(Paths.get(path));
        String script = new String(encode, StandardCharsets.UTF_8);

        for (Map.Entry<String,String> entry:populate.entrySet()) {
            script = script.replace(entry.getKey(),entry.getValue());
        }

        scriptFile = script;

    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<script language=\"JavaScript\" type=\"text/javascript\">\n");
        if(builder != null){
            for(String row:builder.scriptLines){
                htmlBuilder.append(Util.tab(tabs+1));
                htmlBuilder.append(row);
                htmlBuilder.append("\n");
            }
            htmlBuilder.append(Util.tab(tabs));
        }else{
            htmlBuilder.append(scriptFile);
        }

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
