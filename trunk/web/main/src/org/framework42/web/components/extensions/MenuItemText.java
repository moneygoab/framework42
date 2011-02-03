package org.framework42.web.components.extensions;

import org.framework42.i18n.I18N;
import org.framework42.web.components.EventComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.standardhtml.Label;
import org.framework42.web.pages.ChildPage;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

import java.util.Locale;

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
                builder.active = true;
            }
        }

        htmlBuilder.append("<span id=\"");
        htmlBuilder.append(builder.id);
        htmlBuilder.append("\" style=\"padding: ");
        htmlBuilder.append(builder.paddingTop);
        htmlBuilder.append("px ");
        htmlBuilder.append(builder.paddingRight);
        htmlBuilder.append("px ");
        htmlBuilder.append(builder.paddingBottom);
        htmlBuilder.append("px ");
        htmlBuilder.append(builder.paddingLeft);
        htmlBuilder.append("px; ");
        if(builder.active) {
            htmlBuilder.append("background-image: url('");
            htmlBuilder.append(builder.backgroundPictureChosen);
            htmlBuilder.append("');");
            htmlBuilder.append("\" ");

            htmlBuilder.append("class=\"main_menu_item_active_");
            htmlBuilder.append(builder.level);
            htmlBuilder.append("\"");

        } else{
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">");

        htmlBuilder.append("<a href=\"");
        htmlBuilder.append(builder.pageURL);
        htmlBuilder.append("\"");
        if(builder.active) {
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

        htmlBuilder.append(Util.spacer((builder.level-1)*4));
        
        htmlBuilder.append(builder.text.getHtml(page, parent, true));

        htmlBuilder.append("</a>");

        htmlBuilder.append("</span>");

        html = htmlBuilder.toString();

    }

    public static class Builder extends EventComponentBuilder {

        private boolean active;

        private String pageURL;

        private int paddingLeft;

        private int paddingTop;

        private int paddingRight;

        private int paddingBottom;

        private String id;

        private int level;

        private Locale locale;

        private String backgroundPicture;

        private String backgroundPictureChosen;

        private String backgroundPictureMouseOver;

        private Label text;

        public Builder(String id, Label text, String pageURL, Locale locale) {
            this.pageURL = pageURL;
            this.id = id;
            this.locale = locale;
            this.text = text;

            this.active = true;
            this.paddingLeft = 5;
            this.paddingTop = 5;
            this.paddingRight = 5;
            this.paddingBottom = 5;
            this.level = 1;
            this.backgroundPicture = "";
            this.backgroundPictureChosen = "";
            this.backgroundPictureMouseOver = "";
        }

        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

        public Builder pageURL(String pageURL) {
            this.pageURL = pageURL;
            return this;
        }

        public Builder paddingLeft(int paddingLeft) {
            this.paddingLeft = paddingLeft;
            return this;
        }

        public Builder paddingTop(int paddingTop) {
            this.paddingTop = paddingTop;
            return this;
        }

        public Builder paddingRight(int paddingRight) {
            this.paddingRight = paddingRight;
            return this;
        }

        public Builder paddingBottom(int paddingBottom) {
            this.paddingBottom = paddingBottom;
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

        public Builder text(Label text) {
            this.text = text;
            return this;
        }

        @Override
        public MenuItem build() {

            return new MenuItemText(this);
        }

    }



}
