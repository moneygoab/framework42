package org.framework42.web.pagemodel;

import org.framework42.web.components.extensions.TabButton;

public class TabEnvironment {

    private final TabButton tabButton;

    private final PageModel pageModel;

    public TabEnvironment(TabButton tabButton, PageModel pageModel) {
        this.tabButton = tabButton;
        this.pageModel = pageModel;
    }

    public TabButton getTabButton() {
        return tabButton;
    }

    public PageModel getPageModel() {
        return pageModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TabEnvironment that = (TabEnvironment) o;

        if (pageModel != null ? !pageModel.equals(that.pageModel) : that.pageModel != null) return false;
        if (tabButton != null ? !tabButton.equals(that.tabButton) : that.tabButton != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tabButton != null ? tabButton.hashCode() : 0;
        result = 31 * result + (pageModel != null ? pageModel.hashCode() : 0);
        return result;
    }
    
}
