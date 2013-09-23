package org.framework42.useragent_detection.services;

import org.apache.log4j.Logger;
import org.framework42.useragent_detection.model.ParsedUserAgent;

public interface UserAgentEngineParser {

    public boolean matchesUserAgent(String userAgent);

    public ParsedUserAgent parse(String userAgent, Logger logger);

}
