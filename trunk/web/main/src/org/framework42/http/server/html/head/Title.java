package org.framework42.http.server.html.head;

import org.framework42.http.server.html.HtmlComponentImpl;
import org.framework42.web.components.ComponentBuilder;

public class Title extends HtmlComponentImpl implements HeadComponent {

    private final String label;

    public Title(String label) {
        this.label = label;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<title>");
        htmlBuilder.append(label);
        htmlBuilder.append("</title>\n");
    }

    public static class Builder implements ComponentBuilder<Title> {

        private String label;

        public Builder() {

        }

        public Builder label(String label) {
            this.label = label;

            return this;
        }

        @Override
        public Title build() {
            return new Title(label);
        }
    }

}
