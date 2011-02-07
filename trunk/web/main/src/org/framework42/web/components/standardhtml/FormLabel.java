package org.framework42.web.components.standardhtml;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

public class FormLabel extends HtmlComponent {

    private Builder builder;

    private FormLabel(Builder builder) {

        this.builder = builder;

    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<label for=\"");
        htmlBuilder.append(builder.labelForComponentName);
        htmlBuilder.append("\">");
        htmlBuilder.append(builder.labelText);
        htmlBuilder.append("</label>");

    }

    public static class Builder  {

        private String labelText;

        private String labelForComponentName;

        public Builder(String labelText, String labelForComponentName) {

            this.labelText = labelText;
            this.labelForComponentName = labelForComponentName;
        }

        public Builder labelText(String labelText) {

            this.labelText = labelText;
            return this;
        }

        public Builder labelForComponentName(String formName) {

            this.labelForComponentName = formName;
            return this;
        }

        public FormLabel build() {
            return new FormLabel(this);
        }

    }

}
