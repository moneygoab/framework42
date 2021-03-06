package org.framework42.web.components.standardhtml.head;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;
import org.framework42.web.components.ComponentBuilder;

public class Meta extends HtmlComponent {

    private Builder builder;

    private Meta(Builder builder) {

        this.builder = builder;

    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<meta");

        if(MetaName.CHARSET == builder.metaName) {

            htmlBuilder.append(" charset=\"");
            htmlBuilder.append(builder.content);
            htmlBuilder.append("\"");

        } else {

            if(builder.httpEquivalent != null) {

                htmlBuilder.append(" http-equiv=\"");
                htmlBuilder.append(builder.httpEquivalent.getId());
                htmlBuilder.append("\"");

            } else {

                htmlBuilder.append(" name=\"");
                htmlBuilder.append(builder.metaName.getId());
                htmlBuilder.append("\"");
            }

            htmlBuilder.append(" content=\"");
            htmlBuilder.append(builder.content);
            htmlBuilder.append("\"");

        }

        htmlBuilder.append(">\n");

    }

    public static class Builder implements ComponentBuilder<Meta> {

        private final MetaName metaName;

        private final String content;

        private HttpEquivalent httpEquivalent = null;

        public Builder(MetaName metaName, String content) {
            this.metaName = metaName;
            this.content = content;
        }

        public Builder httpEquivalent(HttpEquivalent httpEquivalent){
            this.httpEquivalent = httpEquivalent;
            return this;
        }

        @Override
        public Meta build() {
            return new Meta(this);
        }
    }

}
