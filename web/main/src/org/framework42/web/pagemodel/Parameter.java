package org.framework42.web.pagemodel;

public interface Parameter {

    public String getParameterName();

    public ParameterType getParameterType();

    public boolean isRequired();
    
    public Object getParameterValue();

    public String getValueAsString();

}
