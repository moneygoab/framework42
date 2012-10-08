package org.framework42.useragent_detection.services.impl;

import org.apache.log4j.Logger;
import org.framework42.useragent_detection.model.*;
import org.framework42.useragent_detection.model.impl.ParsedUserAgentImpl;
import org.framework42.useragent_detection.services.UserAgentEngineParser;

public class ChromeParserImpl implements UserAgentEngineParser {

    public ChromeParserImpl() {
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
                RenderingEngine.WEBKIT,
                operatingSystem
        );
    }

    private String parserName(String userAgent, Logger logger) {

        String name = "";

        String[] parts = userAgent.split("like Gecko\\)");

        if(parts.length>1) {

            parts = parts[1].split(" ");

            if(parts.length>1) {

                for(String part: parts) {

                    if(part.contains("Chrome")) {

                        return part;
                    }
                }
            }
        }

        logger.error("[useragent_detection] ChromeParserImpl couldn't parse name from UserAgent: "+userAgent);
        return name;
    }

    private float parseVersion(String name, String userAgent, Logger logger) {

        String[] cut = name.split("/");

        float version = 0;

        if(cut.length>1) {

            try {

                return new Float(cut[1].split("\\.")[0]);

            } catch(NumberFormatException e) {

                logger.error("[useragent_detection] FirefoxParserImpl couldn't parse version from UserAgent: "+userAgent);
            }

        } else {

            logger.error("[useragent_detection] ChromeParserImpl couldn't parse version from UserAgent: "+userAgent);
        }

        return version;
    }

}
