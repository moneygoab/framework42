package org.framework42.rss.model.impl;

import org.framework42.rss.model.RSSFeed;
import org.framework42.rss.model.RSSItem;

import java.util.List;

public class RSSFeedImpl implements RSSFeed {

    private final String title;

    private final String link;

    private final String description;

    private final List<RSSItem> itemList;

    public RSSFeedImpl(String title, String link, String description, List<RSSItem> itemList) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.itemList = itemList;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getLink() {
        return link;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<RSSItem> getItemList() {
        return itemList;
    }

    @Override
    public String toString() {
        return "RSSFeedImpl{\n" +
                "title='" + title + "\'" +
                ", \nlink='" + link + "\'" +
                ", \ndescription='" + description + "\'" +
                ", \nitemList=" + itemList +
                "}\n";
    }
}
