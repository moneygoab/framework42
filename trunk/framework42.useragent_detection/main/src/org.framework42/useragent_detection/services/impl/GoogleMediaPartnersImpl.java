package org.framework42.useragent_detection.services.impl;

import org.apache.log4j.Logger;
import org.framework42.useragent_detection.model.*;
import org.framework42.useragent_detection.model.impl.ParsedUserAgentImpl;
import org.framework42.useragent_detection.services.UserAgentEngineParser;

public class GoogleMediaPartnersImpl implements UserAgentEngineParser {

    public GoogleMediaPartnersImpl() {
    }

    @Override
    public boolean matchesUserAgent(String userAgent) {

        return userAgent.contains("Mediapartners-Google");
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

        return "Google Media Partners";
    }

    private float parseVersion(String name, String userAgent, Logger logger) {

        float version = 0;



        return version;
    }
}
