package org.framework42.useragent_detection.services;

import org.framework42.model.users.User;
import org.framework42.useragent_detection.model.ParsedUserAgent;

public interface UserAgentParser {

    public ParsedUserAgent parse(User invocationUser, String userAgent);

}
