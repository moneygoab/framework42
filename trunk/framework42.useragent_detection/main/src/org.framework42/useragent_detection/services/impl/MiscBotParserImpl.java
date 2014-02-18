package org.framework42.useragent_detection.services.impl;

import org.apache.log4j.Logger;
import org.framework42.useragent_detection.model.*;
import org.framework42.useragent_detection.model.impl.ParsedUserAgentImpl;
import org.framework42.useragent_detection.services.UserAgentEngineParser;

import java.util.ArrayList;
import java.util.List;

public class MiscBotParserImpl implements UserAgentEngineParser {

    private final List<String> idList;

    public MiscBotParserImpl() {

        idList = new ArrayList<String>();

        idList.add("YandexBot");
        idList.add("LINGustic research");
        idList.add("Whitevector Crawler");
        idList.add("BOT for JCE");
        idList.add("compatible; Kraken");
        idList.add("Apple-PubSub");
        idList.add("Feedreader");
        idList.add("www.integromedb.org/Crawler");
        idList.add("Feedfetcher-Google");
        idList.add("Ezooms/1.0");
        idList.add("proximic");
        idList.add("Yahoo:LinkExpander:Slingstone");
        idList.add("NING/");
        idList.add("Twitterbot");
        idList.add("GrapeshotCrawler");
        idList.add("Feedreader");
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

        String name = "Misc Bot";

        for(String s: idList) {

            if(userAgent.contains(s)) {

                name = s;
            }
        }

        float version = 0;

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
}
