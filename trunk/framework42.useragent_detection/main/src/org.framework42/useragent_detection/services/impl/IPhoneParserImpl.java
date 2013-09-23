package org.framework42.useragent_detection.services.impl;

import org.apache.log4j.Logger;
import org.framework42.useragent_detection.model.*;
import org.framework42.useragent_detection.model.impl.ParsedUserAgentImpl;
import org.framework42.useragent_detection.services.UserAgentEngineParser;

public class IPhoneParserImpl implements UserAgentEngineParser {

    public IPhoneParserImpl() {
    }

    @Override
    public boolean matchesUserAgent(String userAgent) {

        return userAgent.contains("(iPhone;");
    }

    @Override
    public ParsedUserAgent parse(String userAgent, Logger logger) {

        String name = parserName(userAgent, logger);

        float version = parseVersion(name, userAgent, logger);

        DeviceType deviceType = DeviceType.MOBILE_PHONE;

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

        return "Safari Mobile";
    }

    private float parseVersion(String name, String userAgent, Logger logger) {

        String[] cut = userAgent.split("iPhone OS");

        float version = 0;

        if(cut.length>1) {

            try {

                String s = cut[1].split("like")[0].trim();
                return new Float(s.split("_")[0]);

            } catch(NumberFormatException e) {

                logger.error("[useragent_detection] IPhoneParserImpl couldn't parse version from UserAgent: "+userAgent);
            }

        } else {

            logger.error("[useragent_detection] IPhoneParserImpl couldn't parse version from UserAgent: "+userAgent);
        }

        return version;
    }
}
