package org.framework42.web.components.standardhtml;

import org.framework42.web.components.EventComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.framework42.utils.NullChecker.notNull;

public class Picture extends HtmlComponent {

    private Builder builder;

    private final Image fallBackImage;

    public Picture(Image fallBackImage) {

        this.fallBackImage = notNull(fallBackImage);
    }

    public Picture(Builder builder) {
        this.builder = builder;

        this.fallBackImage = builder.fallBackImage;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<picture");

        htmlBuilder.append(builder.addGeneralComponents());

        htmlBuilder.append(">\n");

        for(Source source: builder.sources) {

            htmlBuilder.append(source.getHtml(page, parent, false));
        }

        htmlBuilder.append(fallBackImage.getHtml(page, parent, false));

        htmlBuilder.append("</picture>\n");

        if(!onSameRow){
            htmlBuilder.append("\n");
        }
    }

    public static class Builder extends EventComponentBuilder<Picture> {

        private final Image fallBackImage;

        private List<Source> sources = new ArrayList<>();

        public Builder(Image fallBackImage) {
            this.fallBackImage = fallBackImage;
        }

        public Builder addSource(Source... sources) {

            this.sources.addAll(Arrays.asList(sources));
            return this;
        }

        @Override
        public Picture build() {
            return new Picture(this);
        }
    }

}
