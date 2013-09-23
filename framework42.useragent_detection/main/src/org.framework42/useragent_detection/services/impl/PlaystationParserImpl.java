package org.framework42.useragent_detection.services.impl;

import org.apache.log4j.Logger;
import org.framework42.useragent_detection.model.*;
import org.framework42.useragent_detection.model.impl.ParsedUserAgentImpl;
import org.framework42.useragent_detection.services.UserAgentEngineParser;

public class PlaystationParserImpl implements UserAgentEngineParser {

    public PlaystationParserImpl() {
    }

    @Override
    public boolean matchesUserAgent(String userAgent) {

        return userAgent.contains("PLAYSTATION");
    }

    @Override
    public ParsedUserAgent parse(String userAgent, Logger logger) {

        String name = parserName(userAgent, logger);

        float version = parseVersion(name, userAgent, logger);

        DeviceType deviceType = DeviceType.GAME_CONSOLE;

        OperatingSystem operatingSystem = null;

        return new ParsedUserAgentImpl(
                name,
                version,
                UserAgentType.WEB_BROWSER,
                deviceType,
                RenderingEngine.WEBKIT,
                operatingSystem
        );
    }

    private String parserName(String userAgent, Logger logger) {

        try {

            return userAgent.split("\\(")[1].split("\\)")[0];

        } catch(IndexOutOfBoundsException e) {

            logger.error("[useragent_detection] ChromeParserImpl couldn't parse name from UserAgent: "+userAgent);
            return "";
        }
    }

    private float parseVersion(String name, String userAgent, Logger logger) {

        String versionString = name.split("PLAYSTATION 3 ")[1].split("\\)")[0];

        float version = 0;

        try {

            return new Float(versionString);

        } catch(NumberFormatException e) {

            logger.error("[useragent_detection] PlaystationParserImpl couldn't parse version from UserAgent: "+userAgent);
        }

        return version;
    }

}
