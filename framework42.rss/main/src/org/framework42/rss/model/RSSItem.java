package org.framework42.rss.model;

import java.util.Date;

public interface RSSItem {

    public String getTitle();

    public String getLink();

    public String getDescription();

    public String getAuthor();

    public Date getPubDate();

}
