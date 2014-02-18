package org.framework42.useragent_detection.services.impl;

import org.apache.log4j.Logger;
import org.framework42.useragent_detection.model.*;
import org.framework42.useragent_detection.model.impl.ParsedUserAgentImpl;
import org.framework42.useragent_detection.services.UserAgentEngineParser;

public class YahooSlurpBotImpl implements UserAgentEngineParser {


    public YahooSlurpBotImpl() {
    }

    @Override
    public boolean matchesUserAgent(String userAgent) {

        //Mozilla/5.0 (compatible; Yahoo! Slurp; http://help.yahoo.com/help/us/ysearch/slurp)

        return userAgent.contains("Yahoo! Slurp");
    }

    @Override
    public ParsedUserAgent parse(String userAgent, Logger logger) {

        String name = "Yahoo Slurp";

        float version = 0;

        DeviceType deviceType = DeviceType.SERVER;

        OperatingSystem operatingSystem = null;

        return new ParsedUserAgentImpl(
                name,
                version,
                UserAgentType.ROBOT,
                deviceType,
                RenderingEngine.UNKNOWN,
                operatingSystem
        );
    }
}
