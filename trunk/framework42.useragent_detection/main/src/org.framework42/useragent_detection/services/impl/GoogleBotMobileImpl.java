package org.framework42.useragent_detection.services.impl;

import org.apache.log4j.Logger;
import org.framework42.useragent_detection.model.*;
import org.framework42.useragent_detection.model.impl.ParsedUserAgentImpl;
import org.framework42.useragent_detection.services.UserAgentEngineParser;

public class GoogleBotMobileImpl implements UserAgentEngineParser {

    public GoogleBotMobileImpl() {
    }

    @Override
    public boolean matchesUserAgent(String userAgent) {
        //DoCoMo/2.0 N905i(c100;TB;W24H16) (compatible; Googlebot-Mobile/2.1; +http://www.google.com/bot.html)
        return userAgent.contains("Googlebot-Mobile");
    }

    @Override
    public ParsedUserAgent parse(String userAgent, Logger logger) {

        String name = "Googlebot-Mobile";

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
