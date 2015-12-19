package org.framework42.http.server.html.head;

import org.framework42.http.server.html.ComponentBuilder;
import org.framework42.http.server.html.HtmlComponentImpl;
import org.framework42.http.server.html.CrossOrigin;
import org.framework42.model.MimeType;

public class Link extends HtmlComponentImpl implements HeadComponent {

    //TODO: Implement hreflang

    private Builder builder;

    public Link(Builder builder) {
        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<link");

        if(builder.crossOrigin!=null) {

            htmlBuilder.append(" crossorigin=\"");
            htmlBuilder.append(builder.crossOrigin.getValue());
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(" href=\"");
        htmlBuilder.append(builder.href);
        htmlBuilder.append("\"");

        if(builder.media!=null) {

            htmlBuilder.append(" media=\"");
            htmlBuilder.append(builder.media);
            htmlBuilder.append("\"");
        }

        if(builder.rel!=null) {

            htmlBuilder.append(" rel=\"");
            htmlBuilder.append(builder.rel.getValue());
            htmlBuilder.append("\"");

            if(builder.rel==Rel.ICON && builder.sizes!=null) {

                htmlBuilder.append(" sizes=\"");
                htmlBuilder.append(builder.sizes);
                htmlBuilder.append("\"");
            }
        }

        if(builder.mimeType!=null) {

            htmlBuilder.append(" type=\"");
            htmlBuilder.append(builder.mimeType.toString());
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">\n");
    }

    public static class Builder implements ComponentBuilder<Link> {

        private CrossOrigin crossOrigin = null;

        private String href = "";

        private String media = null;

        private Rel rel = null;

        private String sizes = null;

        private MimeType mimeType = null;

        public Builder(String href, Rel rel) {
            this.href = href;
            this.rel = rel;
        }

        @Override
        public Link build() {
            return new Link(this);
        }

        public Link.Builder crossOrigin(CrossOrigin crossOrigin) {

            this.crossOrigin = crossOrigin;
            return this;
        }

        public Builder href(String href) {

            this.href = href;
            return this;
        }

        public Builder media(String media) {

            this.media = media;
            return this;
        }

        public Builder rel(Rel rel) {

            this.rel = rel;
            return this;
        }

        public Builder sizes(String sizes) {

            this.sizes = sizes;
            return this;
        }

        public Builder mimeType(MimeType mimeType) {

            this.mimeType = mimeType;
            return this;
        }

    }

}
