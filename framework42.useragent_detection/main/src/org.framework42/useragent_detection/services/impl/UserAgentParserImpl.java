package org.framework42.useragent_detection.services.impl;

import org.framework42.model.users.User;
import org.framework42.services.ProxyService;
import org.framework42.useragent_detection.model.*;
import org.framework42.useragent_detection.model.impl.OperatingSystemImpl;
import org.framework42.useragent_detection.model.impl.ParsedUserAgentImpl;
import org.framework42.useragent_detection.services.UserAgentEngineParser;
import org.framework42.useragent_detection.services.UserAgentParser;

public class UserAgentParserImpl extends ProxyService<UserAgentParserImpl> implements UserAgentParser {

    private final UserAgentEngineParser internetExplorerParser;

    private final UserAgentEngineParser firefoxParser;

    private final UserAgentEngineParser chromeParser;

    private final UserAgentEngineParser safariParser;

    private final UserAgentEngineParser googleBotParser;

    private final UserAgentEngineParser iPhoneParser;

    private final UserAgentEngineParser operaParser;

    public UserAgentParserImpl() {
        super("com.framework42.useragent_detection.services");

        this.internetExplorerParser = new MSInternetExplorerParserImpl();
        this.firefoxParser= new FirefoxParserImpl();
        this.chromeParser = new ChromeParserImpl();
        this.safariParser = new SafariParserImpl();
        this.googleBotParser = new GoogleBotParserImpl();
        this.iPhoneParser = new IPhoneParserImpl();
        this.operaParser = new OperaParserImpl();

    }

    @Override
    public ParsedUserAgent parse(User invocationUser, String userAgent) {

        if(userAgent.contains("MSIE")) {

            return internetExplorerParser.parse(userAgent, logger);

        } else if(userAgent.contains("Firefox")) {

            return firefoxParser.parse(userAgent, logger);

        } else if(userAgent.contains("Chrome")) {

            return chromeParser.parse(userAgent, logger);

        }  else if(userAgent.contains("Safari/")) {

            return safariParser.parse(userAgent, logger);

        } else if(userAgent.contains("Googlebot/")) {

            return googleBotParser.parse(userAgent, logger);

        } else if(userAgent.contains("(iPhone;")) {

            return iPhoneParser.parse(userAgent, logger);

        }  else if(userAgent.contains("Opera/")) {

            return operaParser.parse(userAgent, logger);

        }

        logger.error("[useragent_detection] UserAgentParserImpl couldn't parse UserAgent: "+userAgent);
        return new ParsedUserAgentImpl("", 0, UserAgentType.UNKNOWN, DeviceType.UNKNOWN, RenderingEngine.UNKNOWN, new OperatingSystemImpl(OperatingSystemBranch.UNKNOWN, "", 0, ""));
    }
}
