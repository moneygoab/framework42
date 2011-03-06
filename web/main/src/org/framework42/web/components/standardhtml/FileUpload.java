package org.framework42.web.components.standardhtml;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.InputComponentBuilder;
import org.framework42.web.pages.WebPage;

public class FileUpload extends HtmlComponent {

    private Builder builder;

    public FileUpload(String name, String value) {

        this.builder = new Builder(name, value);
    }

    private FileUpload(Builder builder) {

        this.builder = builder;

    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<input type=\"file\"");

        htmlBuilder.append(builder.addGeneralComponents());

        htmlBuilder.append(">\n");

    }

    public static class Builder extends InputComponentBuilder<FileUpload> {

        public Builder(String name, String value) {
            super(name, value);
        }

        @Override
        public FileUpload build() {
            return new FileUpload(this);
        }
    }


}
