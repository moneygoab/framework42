package org.framework42.web.pagemodel;

import org.framework42.web.components.extensions.TabButton;

import static org.framework42.utils.NullChecker.notNull;

public class TabEnvironment {

    private final long id;

    private final TabButton tabButton;

    private PageModel pageModel;

    public TabEnvironment(long id, TabButton tabButton, PageModel pageModel) {
        this.id = id;
        this.tabButton = notNull(tabButton, "Tab button can't be null!");
        this.pageModel = notNull(pageModel, "Page model can't be null!");
    }

    public long getId() {
        return id;
    }

    public TabButton getTabButton() {
        return tabButton;
    }

    public PageModel getPageModel() {
        return pageModel;
    }

    public void setPageModel(PageModel pageModel) {
        this.pageModel = pageModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TabEnvironment that = (TabEnvironment) o;

        if (id != that.id) return false;
        if (!pageModel.equals(that.pageModel)) return false;
        if (!tabButton.equals(that.tabButton)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + tabButton.hashCode();
        result = 31 * result + pageModel.hashCode();
        return result;
    }

}
