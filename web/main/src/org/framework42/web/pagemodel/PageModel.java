package org.framework42.web.pagemodel;

import java.util.HashMap;
import java.util.Map;

public abstract class PageModel {

    protected boolean error;
    protected String errorMessage;

    protected Map<Class,ComponentModel> componentModels;

    protected PageModel() {
        error = false;
        componentModels = new HashMap<Class,ComponentModel>();
    }

    public boolean isError() {
        return error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setError(String errorMessage) {
        this.error = true;
        this.errorMessage = errorMessage;
    }

    public void revertError() {
        this.error = false;
        this.errorMessage = null;
    }

    public Map<Class, ComponentModel> getComponentModels() {
        return componentModels;
    }
        
}
