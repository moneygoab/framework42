package org.framework42.useragent_detection.services.impl;

import org.framework42.model.users.User;
import org.framework42.services.ProxyService;
import org.framework42.useragent_detection.model.*;
import org.framework42.useragent_detection.model.impl.OperatingSystemImpl;
import org.framework42.useragent_detection.model.impl.ParsedUserAgentImpl;
import org.framework42.useragent_detection.services.UserAgentEngineParser;
import org.framework42.useragent_detection.services.UserAgentParser;

import java.util.ArrayList;
import java.util.List;

public class UserAgentParserImpl extends ProxyService<UserAgentParserImpl> implements UserAgentParser {

    private final List<UserAgentEngineParser> parsersList;

    public UserAgentParserImpl() {
        super("com.framework42.useragent_detection.services");

        parsersList = new ArrayList<UserAgentEngineParser>();

        parsersList.add(new MSInternetExplorerParserImpl());
        parsersList.add(new FirefoxParserImpl());
        parsersList.add(new IPhoneParserImpl());
        parsersList.add(new IPadParserImpl());
        parsersList.add(new ChromeParserImpl());
        parsersList.add(new SafariParserImpl());
        parsersList.add(new OperaParserImpl());
        parsersList.add(new PlaystationParserImpl());

        // Bots
        parsersList.add(new GoogleBotParserImpl());
        parsersList.add(new GoogleBotImageParserImpl());
        parsersList.add(new GoogleMediaPartnersImpl());
        parsersList.add(new PingdomBotParserImpl());
        parsersList.add(new BingBotParserImpl());
        parsersList.add(new MSNBotMediaParserImpl());
        parsersList.add(new FacebookExternalHitBotParserImpl());
        parsersList.add(new Majestic12BotParserImpl());
        parsersList.add(new AhrefsBotImpl());
        parsersList.add(new BaiduBotParserImpl());

    }

    @Override
    public ParsedUserAgent parse(User invocationUser, String userAgent) {

        if(userAgent.length()==0) {

            return new ParsedUserAgentImpl("", 0, UserAgentType.UNKNOWN, DeviceType.UNKNOWN, RenderingEngine.UNKNOWN, new OperatingSystemImpl(OperatingSystemBranch.UNKNOWN, "", 0, ""));
        }

        for(UserAgentEngineParser parser: parsersList) {

            if(parser.matchesUserAgent(userAgent)) {

                return parser.parse(userAgent, logger);
            }
        }

        logger.info("[useragent_detection] UserAgentParserImpl couldn't parse UserAgent: " + userAgent);
        return new ParsedUserAgentImpl("", 0, UserAgentType.UNKNOWN, DeviceType.UNKNOWN, RenderingEngine.UNKNOWN, new OperatingSystemImpl(OperatingSystemBranch.UNKNOWN, "", 0, ""));
    }
}
