package org.framework42.web.pagemodel;

import java.util.Map;

public class CMSSiteConfiguration {

    private Map<Integer,String> headEntries;

    public CMSSiteConfiguration() {

    }

    public Map<Integer, String> getHeadEntries() {
        return headEntries;
    }

    public void setHeadEntries(Map<Integer, String> headEntries) {
        this.headEntries = headEntries;
    }

}
