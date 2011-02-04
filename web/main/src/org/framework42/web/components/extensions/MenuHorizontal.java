package org.framework42.web.components.extensions;

import org.framework42.web.components.EventComponentBuilder;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.HtmlComponentStorage;
import org.framework42.web.pagemodel.extensions.MenuHorizontalModel;
import org.framework42.web.pages.WebPage;

import java.util.ArrayList;
import java.util.List;

public class MenuHorizontal extends HtmlComponent {

    private Builder builder;

    private MenuHorizontal(Builder builder) {
        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<script language=\"javascript\" type=\"text/javascript\">\n");

        htmlBuilder.append("function changeMenuBackground(itemId, backgroundURI) {\n");

        htmlBuilder.append("document.getElementById(itemId).style.backgroundImage = \"url(\"+backgroundURI+\")\";\n");

        htmlBuilder.append("}\n");

        htmlBuilder.append("function removeMenuBackground(itemId) {\n");

        htmlBuilder.append("document.getElementById(itemId).style.backgroundImage = \"\";\n");

        htmlBuilder.append("}\n");

        htmlBuilder.append("</script>\n");

        for(MenuItem menuItem : builder.menuItemList) {

            htmlBuilder.append(menuItem.getHtml(page, this, false));

        }

    }

    public static class Builder extends EventComponentBuilder<MenuHorizontal> implements HtmlComponentStorage<MenuItem> {

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
