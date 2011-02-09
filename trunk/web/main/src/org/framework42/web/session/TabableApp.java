package org.framework42.web.session;

import org.framework42.web.components.extensions.TabButton;
import org.framework42.web.pagemodel.TabEnvironment;

import java.util.List;

public interface TabableApp {

    public void initTabEnvironment();

    public List<TabEnvironment> getTabEnvironments();

    public TabEnvironment getActiveTabEnvironment();

    public void setActiveTabEnvironment(TabEnvironment activeTabEnvironment);

}
