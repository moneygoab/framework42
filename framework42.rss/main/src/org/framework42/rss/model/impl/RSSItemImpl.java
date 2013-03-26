package org.framework42.rss.model.impl;

import org.framework42.rss.model.RSSItem;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RSSItemImpl implements RSSItem {

    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private final String title;

    private final String link;

    private final String description;

    private final String author;

    private final long pubDate;

    public RSSItemImpl(String title, String link, String description, String author, Date pubDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.author = author;
        this.pubDate = pubDate.getTime();
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
    public String getAuthor() {
        return author;
    }

    @Override
    public Date getPubDate() {
        return new Date(pubDate);
    }

    @Override
    public String toString() {
        return "\tRSSItemImpl{\n" +
                "\ttitle='" + title + "\'" +
                ", \n\tlink='" + link + "\'" +
                ", \n\tdescription='" + description + "\'" +
                ", \n\tauthor='" + author + "\'" +
                ", \n\tpubDate=" + dateFormat.format(pubDate) +
                "\t}\n";
    }
}
