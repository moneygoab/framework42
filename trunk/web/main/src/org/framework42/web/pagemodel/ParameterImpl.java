package org.framework42.web.pagemodel;

import org.framework42.model.ParameterType;

public class ParameterImpl<T> implements Parameter<T> {

    private final String parameterName;

    private final ParameterType parameterType;

    private final boolean required;

    private T parameterValue;

    public ParameterImpl(String parameterName, ParameterType parameterType, T parameterValue) {
        this.parameterName = parameterName;
        this.parameterType = parameterType;
        this.parameterValue = parameterValue;
        this.required = false;
    }

    public ParameterImpl(String parameterName, ParameterType parameterType, boolean required, T parameterValue) {
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
    public T getValue() {
        return parameterValue;
    }

    @Override
    public String asString() {
        return parameterValue.toString();
    }

    @Override
    public int asInt() {
        return Integer.parseInt(parameterValue.toString());

    }

    @Override
    public boolean asBoolean() {
        return Boolean.parseBoolean(parameterValue.toString());
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
