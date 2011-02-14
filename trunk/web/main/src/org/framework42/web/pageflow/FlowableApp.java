package org.framework42.web.pageflow;

import org.framework42.web.pagemodel.PageAction;

public interface FlowableApp {

    public PageAction getLastPageAction();

    public void setLastPageAction(PageAction pageAction);

    public String getLastPageClassName();

    public void setLastPageClassName(String lastPageClassName);

}

