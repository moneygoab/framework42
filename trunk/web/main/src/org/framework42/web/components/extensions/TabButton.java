package org.framework42.web.components.extensions;

import org.framework42.web.components.ComponentGroup;
import org.framework42.web.components.EventComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.standardhtml.Image;
import org.framework42.web.components.standardhtml.Link;
import org.framework42.web.components.standardhtml.Span;
import org.framework42.web.pagemodel.BasePageAction;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

import java.util.HashMap;
import java.util.Map;

public class TabButton extends HtmlComponent {

    private final Builder builder;

    public TabButton(Builder builder) {
        this.builder = builder;
    }

    @Override
    public void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append(builder.span.getHtml(page, parent, false));
        
        /*htmlBuilder.append(builder.tabLink.getHtml(page, this, true));

        if(builder.closeButton != null) {

            htmlBuilder.append(builder.closeButton.getHtml(page, this, false));
        } */

        htmlBuilder.append(Util.spacer(3));

    }

    public Builder getBuilder() {

        return builder;
    }

    public static class Builder extends EventComponentBuilder<TabButton> {

        private Span span;

        private Link tabLink;

        private Link closeButton = null;

        public Builder(Link tabLink, String closeButtonURL) {
            this.tabLink = tabLink;

            ComponentGroup.Builder components = new ComponentGroup.Builder();


            components.add(tabLink);

            if(closeButtonURL != null) {

                String tabId = tabLink.getBuilder().getParameters().get("tabId");

                Map<String, String> closeParameters = new HashMap<String,String>();
                closeParameters.put("tabId",tabId);
                closeButton = new Link.Builder(
                        "close_button",
                        tabLink.getBuilder().getHref(),
                        closeParameters,
                        new Image(closeButtonURL, "Close")
                ).linkedPageAction(BasePageAction.REMOVE_TAB).build();

                components.add(closeButton);

            }

            this.span = new Span.Builder(components.build())
                    //TODO: Move to CSS!
                    .style("border-width: 1px; border-style: solid; border-color: #e4e4e4; padding: 2px;")
                    .build();

        }

        public Link getTabLink() {
            return tabLink;
        }

        @Override
        public TabButton build() {
            return new TabButton(this);
        }
    }

}
