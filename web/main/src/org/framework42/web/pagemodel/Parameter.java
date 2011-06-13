package org.framework42.web.pagemodel;

import org.framework42.model.ParameterType;

public interface Parameter <T> {

    public String getParameterName();

    public ParameterType getParameterType();

    public boolean isRequired();
    
    public T getValue();

    public String getValueAsString();

}
