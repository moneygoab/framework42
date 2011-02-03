package org.framework42.web.components.extensions;

import org.framework42.web.components.EventComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.HtmlComponentStorage;
import org.framework42.web.pagemodel.extensions.MenuVerticalModel;
import org.framework42.web.pages.WebPage;

import java.util.ArrayList;
import java.util.List;

public class MenuVertical extends HtmlComponent {

    private Builder builder;

    public MenuVertical(Builder builder) {
        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        for(MenuItem menuItem : builder.menuItemList) {

            htmlBuilder.append(menuItem.getHtml(page, this, false));
            htmlBuilder.append("<br>");
            
        }

        html = htmlBuilder.toString();

    }

    public static class Builder extends EventComponentBuilder implements HtmlComponentStorage<MenuItem> {

        private List<MenuItem> menuItemList;

        private MenuVerticalModel model;

        public Builder(MenuVerticalModel model) {
            this.model = model;
            menuItemList = new ArrayList<MenuItem>();
        }

        @Override
        public MenuVertical build() {
            return new MenuVertical(this);
        }

        @Override
        public void add(MenuItem menuItem) {
            
            menuItemList.add(menuItem);

        }
    }


}
