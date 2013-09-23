package org.framework42.useragent_detection.services.impl;

import org.apache.log4j.Logger;
import org.framework42.useragent_detection.model.*;
import org.framework42.useragent_detection.model.impl.ParsedUserAgentImpl;
import org.framework42.useragent_detection.services.UserAgentEngineParser;

public class MSNBotMediaParserImpl implements UserAgentEngineParser {

    public MSNBotMediaParserImpl() {
    }

    @Override
    public boolean matchesUserAgent(String userAgent) {

        return userAgent.contains("msnbot-media/");
    }

    @Override
    public ParsedUserAgent parse(String userAgent, Logger logger) {

        String name = parserName(userAgent, logger);

        float version = parseVersion(name, userAgent, logger);

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

    private String parserName(String userAgent, Logger logger) {

        return "MSN Bot Media";
    }

    private float parseVersion(String name, String userAgent, Logger logger) {

        String[] cut = userAgent.split("msnbot-media/");

        float version = 0;

        try {

            return new Float(cut[0].split(" ")[0]);

        } catch(NumberFormatException e) {

            logger.error("[useragent_detection] MSNBotMediaParserImpl couldn't parse version from UserAgent: "+userAgent);
        }

        return version;
    }


}
