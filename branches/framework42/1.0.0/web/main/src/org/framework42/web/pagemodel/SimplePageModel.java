package org.framework42.web.pagemodel;

import java.util.HashMap;
import java.util.Map;

public class SimplePageModel extends PageModel {

    private Map<String,String> dataStore;

    public SimplePageModel() {
        super();

        dataStore = new HashMap<String,String>();
    }

    @Override
    protected void setInParametersSpecific() {

    }

    public Map<String, String> getDataStore() {

        return dataStore;
    }
}
