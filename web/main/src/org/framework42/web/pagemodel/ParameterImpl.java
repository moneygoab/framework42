package org.framework42.web.pagemodel;

public class ParameterImpl implements Parameter {

    private final String parameterName;

    private final ParameterType parameterType;

    private final boolean required;

    private Object parameterValue;

    public ParameterImpl(String parameterName, ParameterType parameterType, Object parameterValue) {
        this.parameterName = parameterName;
        this.parameterType = parameterType;
        this.parameterValue = parameterValue;
        this.required = false;
    }

    public ParameterImpl(String parameterName, ParameterType parameterType, boolean required, Object parameterValue) {
        this.parameterName = parameterName;
        this.parameterType = parameterType;
        this.required = required;
        this.parameterValue = parameterValue;
    }

    @Override
    public String getParameterName() {
        return parameterName;
    }

    @Override
    public ParameterType getParameterType() {
        return parameterType;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    @Override
    public Object getParameterValue() {
        return parameterValue;
    }

    @Override
    public String getValueAsString() {
        return parameterValue.toString();
    }

    @Override
    public String toString() {
        return "ParameterImpl{" +
                "parameterName='" + parameterName + '\'' +
                ", parameterType=" + parameterType +
                ", required=" + required +
                ", parameterValue=" + parameterValue +
                '}';
    }
}
