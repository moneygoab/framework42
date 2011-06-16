package org.framework42.web.components;

import org.framework42.model.ParameterType;
import org.framework42.web.pagemodel.Parameter;
import org.framework42.web.pagemodel.ParameterImpl;

public abstract class InputComponentBuilder<T> extends EventComponentBuilder<T> {

    protected Parameter parameter = new ParameterImpl<String>("", ParameterType.STRING, false, "");

    protected String onBlur = null;

    protected String onChange = null;

    protected String onFocus = null;

    protected String onSelect = null;

    protected String disabled = null;

    protected String className = null;

    protected String id = null;

    protected final String name;

    protected final String value;

    protected String placeholder = null;

    protected boolean autoFocus;

    protected InputComponentBuilder(String name, String value) {

        this.name = name;
        this.value = value;

        this.autoFocus = false;
    }

    /**
     * This method should be called from the html generation in the extending class constructor.
     * @return String The generated html code to be included in the extending class constructor.
     * */
    public String addGeneralComponents() {

        StringBuilder html = new StringBuilder();

        html.append(" name=\"");
        html.append(name);
        html.append("\"");

        if(value.length()>0){
            html.append(" value=\"");
            html.append(value);
            html.append("\"");
        }

        html.append(super.addGeneralComponents());

        if(className!=null){
            html.append(" class=\"");
            html.append(className);
            html.append("\"");
        }

        if(disabled!=null){
            html.append(" ");
            html.append(disabled);
        }

        if(autoFocus){
            html.append(" autofocus");
        }

        if(placeholder!=null){
            html.append(" placeholder=\"");
            html.append(placeholder);
            html.append("\"");
        }

        if(onBlur!=null){
            html.append(" onblur=\"");
            html.append(onBlur);
            html.append("\"");
        }

        if(onChange!=null){
            html.append(" onchange=\"");
            html.append(onChange);
            html.append("\"");
        }

        if(onFocus!=null){
            html.append(" onfocus=\"");
            html.append(onFocus);
            html.append("\"");
        }

        if(onSelect!=null){
            html.append(" onselect=\"");
            html.append(onSelect);
            html.append("\"");
        }

        if(id!=null){
            html.append(" id=\"");
            html.append(id);
            html.append("\"");
        }

        return html.toString();

    }

    public Parameter getParameter() {
        return parameter;
    }

    public InputComponentBuilder<T> parameter(Parameter parameter) {

        this.parameter = parameter;

        return this;
    }

    public InputComponentBuilder<T> onBlur(String onBlurEvent){
        this.onBlur = onBlurEvent;
        return this;
    }

    public InputComponentBuilder<T> onChange(String onChangeEvent){
        this.onChange = onChangeEvent;
        return this;
    }

    public InputComponentBuilder<T> onFocus(String onFocusEvent){
        this.onFocus = onFocusEvent;
        return this;
    }

    public InputComponentBuilder<T> onSelect(String onSelectEvent){
        this.onSelect = onSelectEvent;
        return this;
    }

    public InputComponentBuilder<T> disabled(boolean disabled){
        if(disabled){
            this.disabled = "disabled";
        }else{
            this.disabled = null;
        }
        return this;
    }

    public InputComponentBuilder<T> className(String className){
        this.className = className;
        return this;
    }

    public InputComponentBuilder<T> id(String id){
        this.id = id;
        return this;
    }

    public InputComponentBuilder<T> autoFocus(boolean autoFocus){
        this.autoFocus = autoFocus;
        return this;
    }

    public InputComponentBuilder<T> placeholder(String placeholder){
        this.placeholder = placeholder;
        return this;
    }

}
