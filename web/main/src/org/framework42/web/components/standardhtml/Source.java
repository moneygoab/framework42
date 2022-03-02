package org.framework42.web.components.standardhtml;

import org.framework42.model.MimeType;
import org.framework42.web.components.EventComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;

public class Source extends HtmlComponent {

    private Builder builder;

    public Source(Builder builder) {
        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<source");

        if(builder.srcAudioVideo!=null) {

            htmlBuilder.append(" src=\"");
            htmlBuilder.append(builder.srcAudioVideo);
            htmlBuilder.append("\"");
        }

        if(builder.srcImage!=null) {

            htmlBuilder.append(" srcset=\"");
            htmlBuilder.append(builder.srcImage);
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(builder.addGeneralComponents());

        if(builder.mimeType!=null) {

            htmlBuilder.append(" type=\"");
            htmlBuilder.append(builder.mimeType);
            htmlBuilder.append("\"");
        }

        if(builder.sizes!=null) {

            htmlBuilder.append(" sizes=\"");
            htmlBuilder.append(builder.sizes);
            htmlBuilder.append("\"");
        }

        if(builder.media!=null) {

            htmlBuilder.append(" media=\"");
            htmlBuilder.append(builder.media);
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">");

        if(!onSameRow){
            htmlBuilder.append("\n");
        }
    }

    public final static class Builder extends EventComponentBuilder<Source> {

        private String srcAudioVideo = null;

        private String srcImage = null;

        private MimeType mimeType = null;

        private String sizes = null;

        private String media = null;

        public Builder() {
        }

        public Builder srcAudioVideo(String srcAudioVideo) {

            this.srcAudioVideo = srcAudioVideo;
            return this;
        }

        public Builder srcImage(String srcImage) {

            this.srcImage = srcImage;
            return this;
        }

        public Builder mimeType(MimeType mimeType) {

            this.mimeType = mimeType;
            return this;
        }

        public Builder sizes(String sizes) {

            this.sizes = sizes;
            return this;
        }

        public Builder media(String media) {

            this.media = media;
            return this;
        }

        @Override
        public Source build() {
            return new Source(this);
        }
    }
}
