package org.framework42.useragent_detection.services.impl;

import org.apache.log4j.Logger;
import org.framework42.useragent_detection.model.*;
import org.framework42.useragent_detection.model.impl.ParsedUserAgentImpl;
import org.framework42.useragent_detection.services.UserAgentEngineParser;

public class IPadParserImpl implements UserAgentEngineParser {

    public IPadParserImpl() {
    }

    @Override
    public boolean matchesUserAgent(String userAgent) {

        return userAgent.contains("iPad;");
    }

    @Override
    public ParsedUserAgent parse(String userAgent, Logger logger) {

        String name = parserName(userAgent, logger);

        float version = parseVersion(name, userAgent, logger);

        DeviceType deviceType = DeviceType.TABLET;

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

        return "Safari";
    }

    private float parseVersion(String name, String userAgent, Logger logger) {

        //Mozilla/5.0 (iPad; CPU OS 7_0_2 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A501 Safari/9537.53

        String[] cut = userAgent.split("AppleWebKit/");

        float version = 0;

        if(cut.length>1) {

            try {

                String[] versionArray = cut[1].split("\\(")[0].trim().split("\\.");

                if(versionArray.length==1) {

                    return new Float(versionArray[0]);

                } else {

                    return new Float(versionArray[0]+"."+versionArray[1]);
                }

            } catch(NumberFormatException e) {

                logger.error("[useragent_detection] IPadParserImpl couldn't parse version from UserAgent: "+userAgent);
            }

        } else {

            logger.error("[useragent_detection] IPadParserImpl couldn't parse version from UserAgent: "+userAgent);
        }

        return version;
    }



}
