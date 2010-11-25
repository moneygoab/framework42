package org.framework42.web.components.js_components;

import org.framework42.web.components.ComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.standardhtml.Div;
import org.framework42.web.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class JS_Dialog extends HtmlComponent {

    private final Builder builder;

    public final static String CLOSE_ACTION = "$(this).dialog(\"close\");";

    private JS_Dialog(Builder builder) {
        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponent parent, boolean onSameRow) {

        StringBuilder htmlBuilder = new StringBuilder();

        htmlBuilder.append(builder.content.getHtml(this, false));
        htmlBuilder.append("\n");

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("<script language=\"JavaScript\" type=\"text/javascript\">\n");

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("// ");
        htmlBuilder.append(builder.id);
        htmlBuilder.append("\n");

        htmlBuilder.append(Util.tab(tabs));

        htmlBuilder.append("var $");
        htmlBuilder.append(builder.id);
        htmlBuilder.append(" = ");
        
        htmlBuilder.append("$('#");
        htmlBuilder.append(builder.content.getId());
        htmlBuilder.append("').");
        htmlBuilder.append("dialog({\n");

        htmlBuilder.append(Util.tab(tabs + 1));
        htmlBuilder.append(" title: '");
        htmlBuilder.append(builder.title);
        htmlBuilder.append("',\n");

        htmlBuilder.append(Util.tab(tabs + 1));
        htmlBuilder.append(" autoOpen:");
        htmlBuilder.append(builder.autoOpen);
        htmlBuilder.append(",\n");

        htmlBuilder.append(Util.tab(tabs + 1));
        htmlBuilder.append(" width: ");
        htmlBuilder.append(builder.width);
        htmlBuilder.append(",\n");

        htmlBuilder.append(Util.tab(tabs + 1));
        htmlBuilder.append(" modal: true,\n");

        htmlBuilder.append(Util.tab(tabs + 1));
        htmlBuilder.append(" buttons: {\n");

        int i = 1;
        for (JS_DialogButton dialogButton : builder.dialogButtons) {
            htmlBuilder.append(dialogButton.getHtml(this, false));
            if (builder.dialogButtons.size() != i) {
                htmlBuilder.append(",\n");
            } else {
                htmlBuilder.append("\n");
            }
            i++;
        }

        htmlBuilder.append(Util.tab(tabs + 1));
        htmlBuilder.append(" }\n");
        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("});\n\n");

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</script>\n");

        html = htmlBuilder.toString();

    }

    private String generateAutoOpen(boolean autoOpen) {

        if (autoOpen) {
            return "\t autoOpen: true,\n";
        } else {
            return "\t autoOpen: false,\n";
        }

    }

    public static class Builder implements ComponentBuilder<JS_Dialog> {

        private final String id;

        private final String title;

        private final Div content;

        private String autoOpen;

        private final String width;

        private List<JS_DialogButton> dialogButtons;

        public Builder(String id, String title, Div content, int width) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.width = width + "";
            autoOpen = "false";
            dialogButtons = new ArrayList<JS_DialogButton>();
        }

        private Builder autoOpen(boolean autoOpen) {
            this.autoOpen = Boolean.toString(autoOpen);
            return this;
        }

        @Override
        public JS_Dialog build() {
            return new JS_Dialog(this);
        }

        public void add(JS_DialogButton dialogButton) {
            this.dialogButtons.add(dialogButton);
        }
    }

}
