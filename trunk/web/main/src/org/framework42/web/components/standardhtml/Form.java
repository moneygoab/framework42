package org.framework42.web.components.standardhtml;

import org.framework42.model.MimeType;
import org.framework42.web.components.*;
import org.framework42.web.pagemodel.BasePageAction;
import org.framework42.web.pagemodel.PageAction;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

import java.util.ArrayList;
import java.util.List;

import static org.framework42.utils.NullChecker.notNull;

public class Form extends HtmlComponent {

    private Builder builder;

    private Form(Builder builder) {

        this.builder = builder;

    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<form name=\"");
        htmlBuilder.append(builder.name);
        htmlBuilder.append("\" action=\"");
        htmlBuilder.append(builder.action);
        htmlBuilder.append("\" ");
        htmlBuilder.append("method=\"");
        htmlBuilder.append(builder.postMethod.getName());
        htmlBuilder.append("\"");

        htmlBuilder.append(builder.addGeneralComponents());

        if(builder.onReset!=null){
            htmlBuilder.append(" onreset=\"");
            htmlBuilder.append(builder.onReset);
            htmlBuilder.append("\"");
        }

        if(builder.accept!=null){
            htmlBuilder.append(" accept=\"");
            htmlBuilder.append(builder.accept);
            htmlBuilder.append("\"");
        }

        if(builder.encodingType!=null){
            htmlBuilder.append(" enctype=\"");
            htmlBuilder.append(builder.encodingType);
            htmlBuilder.append("\"");
        }

        if(builder.style!=null){
            htmlBuilder.append(" style=\"");
            htmlBuilder.append(builder.style);
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">\n");

        htmlBuilder.append(new Hidden("form_action", builder.actionId).getHtml(page, parent, false));

        for(HtmlComponent component:builder.formComponents){
            htmlBuilder.append(component.getHtml(page, this, false));
            htmlBuilder.append("\n");
        }

        htmlBuilder.append(new Hidden.Builder("action", builder.submitPageAction.getIdentifier()).build().getHtml(page, this, false));
        
        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</form>\n");

    }

    public static class Builder extends EventComponentBuilder<Form> implements HtmlComponentStorage<HtmlComponent> {

        private final String name;
        private final String action;
        private final String actionId;
        private final HtmlPostMethod postMethod;
        private final List<HtmlComponent> formComponents;

        private String onReset = null;

        private String accept = null;

        private String encodingType = null;

        private String style = null;

        private PageAction submitPageAction = BasePageAction.SUBMIT_FORM;

        public Builder(String name, String action, String actionId, HtmlPostMethod postMethod) {
            this.name = notNull(name, "Name can't be null!");
            this.action = notNull(action, "Action can't be null!");
            this.actionId = notNull(actionId, "Action id can't be null!");
            this.postMethod = notNull(postMethod, "Post method can't be null!");
            this.formComponents = new ArrayList<HtmlComponent>();
        }

        public Builder onReset(String onReset){
            this.onReset = onReset;
            return this;
        }

        public Builder accept(MimeType accept){
            this.accept = accept.toString();
            return this;
        }

        public Builder encodingType(EncodingType encodingType){
            this.encodingType = encodingType.getEncodingType();
            return this;
        }

        public Builder style(String style){
            this.style = style;
            return this;
        }

        public Builder submitPageAction(PageAction submitPageAction) {
            this.submitPageAction = submitPageAction;
            return this;
        }

        @Override
        public void add(HtmlComponent htmlComponent) {
            formComponents.add(htmlComponent);
        }

        @Override
        public Form build() {
            return new Form(this);
        }

    }

}