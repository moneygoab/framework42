package org.framework42.web.pagemodel;

import org.framework42.model.ParameterType;

public class ParameterImpl<T> implements Parameter<T> {

    private final String parameterName;

    private final ParameterType parameterType;

    private final boolean required;

    private final boolean htmlFiltered;

    private T parameterValue;

    public ParameterImpl(String parameterName, ParameterType parameterType, T parameterValue) {
        this.parameterName = parameterName;
        this.parameterType = parameterType;
        this.parameterValue = parameterValue;
        this.required = false;
        this.htmlFiltered = true;
    }

    public ParameterImpl(String parameterName, ParameterType parameterType, boolean required, T parameterValue) {
        this.parameterName = parameterName;
        this.parameterType = parameterType;
        this.required = required;
        this.parameterValue = parameterValue;
        this.htmlFiltered = true;
    }

    public ParameterImpl(String parameterName, ParameterType parameterType, boolean required, boolean htmlFiltered, T parameterValue) {
        this.parameterName = parameterName;
        this.parameterType = parameterType;
        this.required = required;
        this.htmlFiltered = htmlFiltered;
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
    public boolean isHtmlFiltered() {

        return htmlFiltered;
    }

    @Override
    public T getValue() {
        return parameterValue;
    }

    @Override
    public String asString() {
        if(parameterValue instanceof String[]) {
            return ((String[])parameterValue)[0].toString();
        } else {
            return parameterValue.toString();
        }
    }

    @Override
    public int asInt() {
        if(parameterValue instanceof String[]) {
            return Integer.parseInt(((String[])parameterValue)[0].toString());
        } else {
            return Integer.parseInt(parameterValue.toString());
        }
    }

    @Override
    public Integer[] asIntArray() {

        if(parameterValue instanceof String[]) {

            String[] stringArray = ((String[])parameterValue);
            Integer[] returnArray = new Integer[stringArray.length];

            for(int i=0;i<stringArray.length;i++) {

                returnArray[i] = Integer.parseInt(stringArray[i]);
            }

            return returnArray;

        } else if(parameterValue instanceof Integer[]) {

            return (Integer[])parameterValue;

        }

        return new Integer[] {Integer.parseInt(parameterValue.toString())};
    }

    @Override
    public long asLong() {
        if(parameterValue instanceof String[]) {
            return Long.parseLong(((String[])parameterValue)[0].toString());
        } else {
            return Long.parseLong(parameterValue.toString());
        }
    }

    @Override
    public boolean asBoolean() {
        if(parameterValue instanceof String[]) {
            return Boolean.parseBoolean(((String[])parameterValue)[0].toString());
        } else {
            return Boolean.parseBoolean(parameterValue.toString());
        }
    }

    @Override
    public String toString() {
        return "ParameterImpl{" +
                "parameterName='" + parameterName + '\'' +
                ", parameterType=" + parameterType +
                ", required=" + required +
                ", htmlFiltered=" + htmlFiltered +
                ", parameterValue=" + parameterValue +
                '}';
    }
}
