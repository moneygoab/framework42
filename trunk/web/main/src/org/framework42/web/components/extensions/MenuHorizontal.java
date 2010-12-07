package org.framework42.web.components.extensions;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.HtmlComponentStorage;
import org.framework42.web.pagemodel.extensions.MenuHorizontalModel;

import java.util.ArrayList;
import java.util.List;

public class MenuHorizontal extends HtmlComponent implements HtmlComponentStorage<MenuItem> {

    private List<MenuItem> menuItemList;

    private MenuHorizontalModel model;

    public MenuHorizontal(MenuHorizontalModel model) {
        this.model = model;
        this.menuItemList = new ArrayList<MenuItem>();
    }

    public MenuHorizontal(List<MenuItem> menuItemList, MenuHorizontalModel model) {
        this.menuItemList = menuItemList;
        this.model = model;
    }

    @Override
    public void add(MenuItem menuItem) {

        menuItemList.add(menuItem);

    }

    @Override
    protected void generateHtmlSpecific(HtmlComponent parent, boolean onSameRow) {

        for(MenuItem menuItem : menuItemList) {

            htmlBuilder.append(menuItem.getHtml(this, false));

        }

    }

}
