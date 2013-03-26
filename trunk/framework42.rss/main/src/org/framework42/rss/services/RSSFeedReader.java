package org.framework42.rss.services;

import org.framework42.model.users.User;
import org.framework42.rss.model.RSSFeed;

import java.util.Date;

public interface RSSFeedReader {

    public RSSFeed findNewItems(User invocationUser, String url, Date lastCheckDate);

}
