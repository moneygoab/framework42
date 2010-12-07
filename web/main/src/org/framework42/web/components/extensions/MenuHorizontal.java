package org.framework42.web.components.extensions;

import org.framework42.web.components.EventComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.HtmlComponentStorage;
import org.framework42.web.pagemodel.extensions.MenuHorizontalModel;

import java.util.ArrayList;
import java.util.List;

public class MenuHorizontal extends HtmlComponent {

    private Builder builder;

    private MenuHorizontal(Builder builder) {
        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponent parent, boolean onSameRow) {

        for(MenuItem menuItem : builder.menuItemList) {

            htmlBuilder.append(menuItem.getHtml(this, false));

        }

        html = htmlBuilder.toString();

    }

    public static class Builder extends EventComponentBuilder implements HtmlComponentStorage<MenuItem> {

        private List<MenuItem> menuItemList;

        private MenuHorizontalModel model;

        public Builder(MenuHorizontalModel model) {
            this.model = model;
            menuItemList = new ArrayList<MenuItem>();
        }

        @Override
        public void add(MenuItem menuItem) {

            menuItemList.add(menuItem);

        }

        @Override
        public MenuHorizontal build() {
            return new MenuHorizontal(this);
        }
    }



}
