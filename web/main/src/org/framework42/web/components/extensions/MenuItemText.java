package org.framework42.web.components.extensions;

import org.framework42.i18n.I18N;
import org.framework42.web.components.EventComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.standardhtml.Break;
import org.framework42.web.components.standardhtml.Label;
import org.framework42.web.pagemodel.BasePageAction;
import org.framework42.web.pagemodel.PageAction;
import org.framework42.web.pages.ChildPage;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MenuItemText extends MenuItem {

    private Builder builder;

    private MenuItemText(Builder builder) {
        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        if(page instanceof ChildPage) {
            String parentURL = I18N.INSTANCE.getURL(((ChildPage)page).getParentPageURLKey(), builder.locale);
            if(parentURL.equalsIgnoreCase(builder.pageURL)) {
                builder.chosen = true;
            }
        }

        if(builder.level==1 && parent.getClass() != MenuHorizontal.class) {
            htmlBuilder.append(new Break().getHtml(page,parent,false));
        }

        htmlBuilder.append("<span id=\"");
        htmlBuilder.append(builder.id);
        htmlBuilder.append("\" ");

        // Background pic and width
        htmlBuilder.append("style=\"");
        htmlBuilder.append("z-index: 10;");
        htmlBuilder.append("background-image: url('");
        if(builder.chosen) {
            htmlBuilder.append(builder.backgroundPictureChosen);
        } else {
            htmlBuilder.append(builder.backgroundPicture);
        }
        htmlBuilder.append("');");
        if(builder.width>0) {
            htmlBuilder.append("padding-right: ");
            htmlBuilder.append(builder.width);
            htmlBuilder.append("px;");
        }
        htmlBuilder.append("\" ");

        if(builder.active) {

            htmlBuilder.append("class=\"main_menu_item_active_");
            htmlBuilder.append(builder.level);
            htmlBuilder.append("\"");

        } else{
            //htmlBuilder.append("\"");
        }

        htmlBuilder.append(">");

        htmlBuilder.append(Util.spacer((builder.level-1)*4));

        htmlBuilder.append("<a href=\"");
        htmlBuilder.append(builder.pageURL);
        htmlBuilder.append("?action=");
        htmlBuilder.append(builder.linkPageAction.getIdentifier());
        for(String key: builder.linkParameters.keySet()) {
            htmlBuilder.append("&");
            htmlBuilder.append(key);
            htmlBuilder.append("=");
            htmlBuilder.append(builder.linkParameters.get(key));
        }
        htmlBuilder.append("\"");
        if(builder.notLink) {
            htmlBuilder.append(" class=\"main_menu_item_not_link_");
        } else if(builder.chosen) {
            htmlBuilder.append(" class=\"main_menu_item_active_link_");
        } else {
            htmlBuilder.append(" class=\"main_menu_item_inactive_link_");
        }
        htmlBuilder.append(builder.level);
        htmlBuilder.append("\"");

        if(!builder.active) {
            //htmlBuilder.append(" onMouseOver=\"changeMenuBackground('"+getId+"','"+backgroundPictureMouseOver+"');\"");
            //htmlBuilder.append(" onMouseOut=\"removeMenuBackground('"+getId+"');\"");
        }

        htmlBuilder.append(">");

        htmlBuilder.append(builder.text.getHtml(page, parent, true));

        htmlBuilder.append("</a>");

        htmlBuilder.append("</span>");

    }

    public static class Builder extends EventComponentBuilder<MenuItem> {

        private boolean active;

        private boolean chosen;

        private boolean notLink;

        private String pageURL;

        private String id;

        private int level;

        private Locale locale;

        private String backgroundPicture;

        private String backgroundPictureChosen;

        private String backgroundPictureMouseOver;

        private int width;

        private Label text;

        private Map<String, String> linkParameters;

        private PageAction linkPageAction;

        public Builder(String id, Label text, String pageURL, Locale locale) {
            this.pageURL = pageURL;
            this.id = id;
            this.locale = locale;
            this.text = text;
            this.linkParameters = new HashMap<String, String>();

            this.linkPageAction = BasePageAction.NONE;

            this.active = true;
            this.chosen = false;
            this.notLink = false;
            this.level = 1;
            this.backgroundPicture = "";
            this.backgroundPictureChosen = "";
            this.backgroundPictureMouseOver = "";
        }

        public Builder(String id, Label text, String pageURL, Map<String, String> linkParameters, Locale locale) {
            this.pageURL = pageURL;
            this.id = id;
            this.locale = locale;
            this.text = text;
            this.linkParameters = linkParameters;

            this.linkPageAction = BasePageAction.NONE;

            this.active = true;
            this.chosen = false;
            this.notLink = false;
            this.level = 1;
            this.backgroundPicture = "";
            this.backgroundPictureChosen = "";
            this.backgroundPictureMouseOver = "";
        }

        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

        public Builder chosen(boolean chosen){
            this.chosen = chosen;
            return this;
        }

        public Builder notLink(boolean notLink) {
            this.notLink = notLink;
            return this;
        }

        public Builder linkParameters(Map<String, String> linkParameters) {
            this.linkParameters = linkParameters;
            return this;
        }

        public Builder pageURL(String pageURL) {
            this.pageURL = pageURL;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder level(int level) {
            this.level = level;
            return this;
        }

        public Builder locale(Locale locale) {
            this.locale = locale;
            return this;
        }

        public Builder backgroundPicture(String backgroundPicture) {
            this.backgroundPicture = backgroundPicture;
            return this;
        }

        public Builder backgroundPictureChosen(String backgroundPictureChosen) {
            this.backgroundPictureChosen = backgroundPictureChosen;
            return this;
        }

        public Builder backgroundPictureMouseOver(String backgroundPictureMouseOver) {
            this.backgroundPictureMouseOver = backgroundPictureMouseOver;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder text(Label text) {
            this.text = text;
            return this;
        }

        public Builder linkPageAction(PageAction linkPageAction) {
            this.linkPageAction = linkPageAction;
            return this;
        }

        @Override
        public MenuItem build() {

            return new MenuItemText(this);
        }

    }



}
