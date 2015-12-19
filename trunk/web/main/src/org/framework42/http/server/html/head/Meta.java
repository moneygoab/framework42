package org.framework42.http.server.html.head;

import org.framework42.http.server.html.ComponentBuilder;
import org.framework42.http.server.html.HtmlComponentImpl;

public class Meta extends HtmlComponentImpl implements HeadComponent {

    private final MetaName metaName;

    private final String content;

    public Meta(MetaName metaName, String content) {
        this.metaName = metaName;
        this.content = content;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponentImpl parent, boolean onSameRow) {

        htmlBuilder.append("<meta");

        if(MetaName.CHARSET == metaName) {

            htmlBuilder.append(" charset=\"");
            htmlBuilder.append(content);
            htmlBuilder.append("\"");

        } else {

            htmlBuilder.append(" name=\"");
            htmlBuilder.append(metaName.getId());
            htmlBuilder.append("\"");

            htmlBuilder.append(" content=\"");
            htmlBuilder.append(content);
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">\n");
    }

    public static class Builder implements ComponentBuilder<Meta> {

        private final MetaName metaName;

        private final String content;

        public Builder(MetaName metaName, String content) {
            this.metaName = metaName;
            this.content = content;
        }

        @Override
        public Meta build() {
            return new Meta(metaName, content);
        }
    }

}
