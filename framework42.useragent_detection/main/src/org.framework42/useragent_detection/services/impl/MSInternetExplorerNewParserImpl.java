package org.framework42.useragent_detection.services.impl;

import org.apache.log4j.Logger;
import org.framework42.useragent_detection.model.*;
import org.framework42.useragent_detection.model.impl.ParsedUserAgentImpl;
import org.framework42.useragent_detection.services.UserAgentEngineParser;

public class MSInternetExplorerNewParserImpl implements UserAgentEngineParser {


    @Override
    public boolean matchesUserAgent(String userAgent) {

        return userAgent.contains("Trident/");
    }

    @Override
    public ParsedUserAgent parse(String userAgent, Logger logger) {
        //Mozilla/5.0 (Windows NT 6.1; Trident/7.0; rv:11.0) like Gecko

        String name = "MSIE";

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

    private float parseVersion(String name, String userAgent, Logger logger) {

        float version = 11;

        try {

            String cut = userAgent.split("rv:")[1].split("\\)")[0];

            if(cut.split("\\.").length>2) {

                try {

                    version = new Float(cut.split("\\.")[0]+"."+cut.split("\\.")[1]);

                } catch(NumberFormatException e) {

                    logger.error("[useragent_detection] MSInternetExplorerNewParserImpl couldn't parse version from UserAgent (Fail subversion handling): "+userAgent);
                }

            } else {

                try {
                    version = Float.parseFloat(cut);
                } catch(NumberFormatException e) {

                    logger.error("[useragent_detection] MSInternetExplorerNewParserImpl couldn't parse version from UserAgent (NumberFormatException): "+userAgent);
                }
            }

        } catch(ArrayIndexOutOfBoundsException e) {

            logger.error("[useragent_detection] MSInternetExplorerNewParserImpl couldn't parse version from UserAgent (Fail initial string cut): "+userAgent);
        }

        return version;

    }
}
