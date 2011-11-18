package org.framework42.web.components.standardhtml;

import org.framework42.model.MimeType;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.InputComponentBuilder;
import org.framework42.web.pages.WebPage;

import java.util.ArrayList;
import java.util.List;

public class FileUpload extends HtmlComponent {

    private Builder builder;

    public FileUpload(String name, String value, List<MimeType> allowedMimeTypes) {

        this.builder = new Builder(name, value, allowedMimeTypes);
    }

    private FileUpload(Builder builder) {

        this.builder = builder;

    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<input type=\"file\"");

        htmlBuilder.append(builder.addGeneralComponents());

        if(builder.style != null) {

            htmlBuilder.append(" style=\"");
            htmlBuilder.append(builder.style);
            htmlBuilder.append("\"");
        }

        if(builder.allowedMimeTypes.size()>0) {
            htmlBuilder.append(" accept=\"");
            for(int i=0; i<builder.allowedMimeTypes.size();i++) {
                MimeType mimeType = builder.allowedMimeTypes.get(i);
                htmlBuilder.append(mimeType.toString());
                if(i+1<builder.allowedMimeTypes.size()) {
                    htmlBuilder.append(",");
                }
            }
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">\n");

    }

    public static class Builder extends InputComponentBuilder<FileUpload> {

        private List<MimeType> allowedMimeTypes;

        private String style = null;

        public Builder(String name, String value) {

            super(name, value);

            this.allowedMimeTypes = new ArrayList<MimeType>();
        }

        public Builder(String name, String value, List<MimeType> allowedMimeTypes) {
            super(name, value);

            this.allowedMimeTypes = allowedMimeTypes;
        }

        public Builder addAllowedMimeType(MimeType mimeType) {

            allowedMimeTypes.add(mimeType);

            return this;
        }

        public Builder style(String style) {

            this.style = style;
            return this;
        }

        @Override
        public FileUpload build() {
            return new FileUpload(this);
        }
    }


}
