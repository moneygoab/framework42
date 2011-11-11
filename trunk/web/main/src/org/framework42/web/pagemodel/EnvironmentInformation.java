package org.framework42.web.pagemodel;

public enum EnvironmentInformation {

    CURRENT_PAGE_URI("currentPageURI"), LOCAL_ROOT_DIR("localRootDir"), USER_AGENT_ID("userAgentId"), CLIENT_IP("clientIP");

    private final String id;

    private EnvironmentInformation(String id) {

        this.id = id;
    }

    public String getId() {

        return id;
    }
}
