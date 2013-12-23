package org.framework42.web.components.standardhtml;

import org.framework42.web.components.ComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

public class Label extends HtmlComponent {

    public final static Label space = new Label("&nbsp;");

    private final String label;

    private final boolean bold;

    private final boolean italic;

    public Label(String label) {

        this.label = label;
        this.bold = false;
        this.italic = false;
    }

    public Label(String label, boolean bold) {

        this.label = label;
        this.bold = bold;
        this.italic = false;
    }

    public Label(String label, boolean bold, boolean italic) {

        this.label = label;
        this.bold = bold;
        this.italic = italic;
    }

    public Label(int label) {

        this.label = label+"";
        this.bold = false;
        this.italic = false;
    }

    public Label(int label, boolean bold) {

        this.label = label+"";
        this.bold = bold;
        this.italic = false;
    }

    private Label(Builder builder) {

        this.label = builder.label;
        this.bold = builder.bold;
        this.italic = builder.italic;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        if(italic) {

            htmlBuilder.append("<span style=\"font-style: italic;\">");
        }

        if(bold) {
            htmlBuilder.append("<b>");
        }
        htmlBuilder.append(label);
        if(bold) {
            htmlBuilder.append("</b>");
        }
        if(!onSameRow){
            htmlBuilder.append("\n");
        }

        if(italic) {

            htmlBuilder.append("</span>");
        }
    }

    public static class Builder implements ComponentBuilder<Label> {

        private final String label;

        private boolean bold;

        private boolean italic;

        public Builder(String label) {

            this.label = label;
            this.bold = false;
            this.italic = false;
        }

        public Builder bold(boolean bold) {

            this.bold = bold;
            return this;
        }

        public Builder italic(boolean italic) {

            this.italic = italic;
            return this;
        }

        @Override
        public Label build() {

            return new Label(this);
        }
    }


}
