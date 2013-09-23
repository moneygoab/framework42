package org.framework42.useragent_detection.services.impl;

import org.apache.log4j.Logger;
import org.framework42.useragent_detection.model.*;
import org.framework42.useragent_detection.model.impl.ParsedUserAgentImpl;
import org.framework42.useragent_detection.services.UserAgentEngineParser;

public class FacebookExternalHitBotParserImpl implements UserAgentEngineParser {

    public FacebookExternalHitBotParserImpl() {
    }

    @Override
    public boolean matchesUserAgent(String userAgent) {

        return userAgent.contains("facebookexternalhit/");
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

        return "Facebook External Hit";
    }

    private float parseVersion(String name, String userAgent, Logger logger) {

        float version = 0;

        try {

            String versionString = name.split("facebookexternalhit/")[1].split(" ")[0];

            try {

                return new Float(versionString);

            } catch(NumberFormatException e) {

                logger.error("[useragent_detection] FacebookExternalHitBotParserImpl couldn't parse version from UserAgent: "+userAgent);
            }

        } catch(ArrayIndexOutOfBoundsException e) {

            String versionString = name.split("facebookexternalhit/")[0].split(" ")[0];

            try {

                return new Float(versionString);

            } catch(NumberFormatException ex) {

                logger.error("[useragent_detection] FacebookExternalHitBotParserImpl couldn't parse version from UserAgent: "+userAgent);
            }

        }

        return version;
    }

}
