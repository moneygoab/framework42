package org.framework42.useragent_detection.services.impl;

import org.apache.log4j.Logger;
import org.framework42.useragent_detection.model.*;
import org.framework42.useragent_detection.model.impl.ParsedUserAgentImpl;
import org.framework42.useragent_detection.services.UserAgentEngineParser;

public class GoogleBotParserImpl implements UserAgentEngineParser {

    public GoogleBotParserImpl() {
    }

    @Override
    public boolean matchesUserAgent(String userAgent) {

        return userAgent.contains("Googlebot/");
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

        return "Googlebot";
    }

    private float parseVersion(String name, String userAgent, Logger logger) {

        String[] cut = userAgent.split("Googlebot/");

        float version = 0;

        if(cut.length>1) {

            try {

                return new Float(cut[1].split(";")[0]);

            } catch(NumberFormatException e) {

                logger.error("[useragent_detection] GoogleBotParserImpl couldn't parse version from UserAgent: "+userAgent);
            }

        } else {

            logger.error("[useragent_detection] GoogleBotParserImpl couldn't parse version from UserAgent: "+userAgent);
        }

        return version;
    }



}
