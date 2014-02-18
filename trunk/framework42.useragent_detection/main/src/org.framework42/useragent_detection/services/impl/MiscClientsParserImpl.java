package org.framework42.useragent_detection.services.impl;

import org.apache.log4j.Logger;
import org.framework42.useragent_detection.model.*;
import org.framework42.useragent_detection.model.impl.ParsedUserAgentImpl;
import org.framework42.useragent_detection.services.UserAgentEngineParser;

import java.util.ArrayList;
import java.util.List;

public class MiscClientsParserImpl implements UserAgentEngineParser {

    private final List<String> idList;

    public MiscClientsParserImpl() {

        idList = new ArrayList<String>();

        idList.add("Microsoft Office Mobile");
        idList.add("Dolphin http client");

    }

    @Override
    public boolean matchesUserAgent(String userAgent) {

        for(String s: idList) {

            if(userAgent.contains(s)) {

                return true;
            }
        }

        return false;
    }

    @Override
    public ParsedUserAgent parse(String userAgent, Logger logger) {

        String name = "Misc Client";

        for(String s: idList) {

            if(userAgent.contains(s)) {

                name = s;
            }
        }

        float version = 0;

        DeviceType deviceType = DeviceType.COMPUTER;

        OperatingSystem operatingSystem = null;

        return new ParsedUserAgentImpl(
                name,
                version,
                UserAgentType.WEB_BROWSER,
                deviceType,
                RenderingEngine.UNKNOWN,
                operatingSystem
        );
    }
}
