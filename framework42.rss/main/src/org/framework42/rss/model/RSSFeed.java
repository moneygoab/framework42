package org.framework42.rss.model;

import java.util.List;

public interface RSSFeed {

    public String getTitle();

    public String getLink();

    public String getDescription();

    public List<RSSItem> getItemList();

}
