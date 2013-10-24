package org.framework42.useragent_detection.services.impl;

import org.apache.log4j.Logger;
import org.framework42.useragent_detection.model.*;
import org.framework42.useragent_detection.model.impl.ParsedUserAgentImpl;
import org.framework42.useragent_detection.services.UserAgentEngineParser;

class MSInternetExplorerParserImpl implements UserAgentEngineParser {


    MSInternetExplorerParserImpl() {
    }

    @Override
    public boolean matchesUserAgent(String userAgent) {

        return userAgent.contains("MSIE");
    }

    @Override
    public ParsedUserAgent parse(String userAgent, Logger logger) {

        String name = parserName(userAgent, logger);

        float version = parseVersion(name, userAgent, logger);

        DeviceType deviceType = DeviceType.UNKNOWN;

        OperatingSystem operatingSystem = null;

        return new ParsedUserAgentImpl(
                name,
                version,
                UserAgentType.WEB_BROWSER,
                deviceType,
                RenderingEngine.TRIDENT,
                operatingSystem
        );
    }

    private String parserName(String userAgent, Logger logger) {

        String name = "";

        String[] parts = userAgent.split(";");

        for(String part: parts) {

            if(part.contains("MSIE")) {

                return part.trim();
            }
        }

        logger.error("[useragent_detection] MSInternetExplorerParserImpl couldn't parse name from UserAgent: "+userAgent);
        return name;
    }

    private float parseVersion(String name, String userAgent, Logger logger) {

        String[] cut = name.split(" ");

        float version = 0;

        if(cut.length>1) {

            try {

                version = new Float(cut[1].replaceAll("a", "").replaceAll("b", "").replaceAll("c", ""));

            } catch(NumberFormatException e) {

                logger.error("[useragent_detection] MSInternetExplorerParserImpl couldn't parse version from UserAgent: "+userAgent);
            }

        } else {

            logger.error("[useragent_detection] MSInternetExplorerParserImpl couldn't parse version from UserAgent: "+userAgent);
        }

        if(version==7 && userAgent.contains("NT 6.")) {

            version = 8;
        }

        return version;
    }

}
