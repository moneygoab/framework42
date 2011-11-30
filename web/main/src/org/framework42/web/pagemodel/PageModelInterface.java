package org.framework42.web.pagemodel;

import java.util.Map;

public interface PageModelInterface {

    public String getInParameterAsString(String key);

    public Integer getInParameterAsInt(String key);

    public Integer[] getInParameterAsIntArray(String key);

    public Long getInParameterAsLong(String key);

    public boolean hasInParameter(String key);

    public Map<String, Parameter> getInParameters();

}
