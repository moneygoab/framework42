package org.framework42.web.components;

import java.util.HashMap;

public abstract class EventComponentBuilder<T> implements ComponentBuilder<T> {

    protected String onClick = null;

    protected String onDoubleClick = null;

    protected String onMouseDown = null;

    protected String onMouseMove = null;

    protected String onMouseDrag = null;

    protected String onMouseOut = null;

    protected String onMouseOver = null;

    protected String onMouseUp = null;

    protected String onKeyDown = null;

    protected String onKeyPress = null;

    protected String onKeyUp = null;

    protected HashMap<String,String> custom = null;

    /**
     * This method should be called from the html generation in the extending class constructor.
     * @return String The generated html code to be included in the extending class constructor.
     * */
    public String addGeneralComponents(){

        StringBuilder html = new StringBuilder();

        if(onClick != null){
            html.append(" onclick=\""+onClick+"\"");
        }

        if(onDoubleClick != null){
            html.append(" ondblclick=\""+onDoubleClick+"\"");
        }

        if(onMouseDown != null){
            html.append(" onmousedown=\""+onMouseDown+"\"");
        }

        if(onMouseMove != null){
            html.append(" onmousemove=\""+onMouseMove+"\"");
        }

        if(onMouseDrag != null){
            html.append(" onmousedrag=\""+onMouseDrag+"\"");
        }

        if(onMouseOut != null){
            html.append(" onmouseout=\""+onMouseOut+"\"");
        }

        if(onMouseOver != null){
            html.append(" onmouseover=\""+onMouseOver+"\"");
        }

        if(onMouseUp != null){
            html.append(" onmouseup=\""+onMouseUp+"\"");
        }

        if(onKeyDown != null){
            html.append(" onkeydown=\""+onKeyDown+"\"");
        }

        if(onKeyPress != null){
            html.append(" onkeypress=\""+onKeyPress+"\"");
        }

        if(onKeyUp != null){
            html.append(" onkeyup=\"").append(onKeyUp).append("\"");
        }


        if(custom != null){
            for(HashMap.Entry<String,String> entry: custom.entrySet()){
                html.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
            }
        }

        return html.toString();

    }

    public EventComponentBuilder<T> onClick(String onClickEvent){
        this.onClick = onClickEvent;
        return this;
    }

    public EventComponentBuilder<T> onDoubleClick(String onDoubleClickEvent){
        this.onDoubleClick = onDoubleClickEvent;
        return this;
    }

    public EventComponentBuilder<T> onMouseDown(String onMouseDownEvent){
        this.onMouseDown = onMouseDownEvent;
        return this;
    }

    public EventComponentBuilder<T> onMouseDrag(String onMouseDragEvent){
        this.onMouseDrag = onMouseDragEvent;
        return this;
    }

    public EventComponentBuilder<T> onMouseMove(String onMouseMoveEvent){
        this.onMouseMove = onMouseMoveEvent;
        return this;
    }

    public EventComponentBuilder<T> onMouseOut(String onMouseOutEvent){
        this.onMouseOut = onMouseOutEvent;
        return this;
    }

    public EventComponentBuilder<T> onMouseOver(String onMouseOverEvent){
        this.onMouseOver = onMouseOverEvent;
        return this;
    }

    public EventComponentBuilder<T> onMouseUp(String onMouseUpEvent){
        this.onMouseUp = onMouseUpEvent;
        return this;
    }

    public EventComponentBuilder<T> onKeyDown(String onKeyDownEvent){
        this.onKeyDown = onKeyDownEvent;
        return this;
    }

    public EventComponentBuilder<T> onKeyPress(String onKeyPressEvent){
        this.onKeyPress = onKeyPressEvent;
        return this;
    }

    public EventComponentBuilder<T> onKeyUp(String onKeyUpEvent){
        this.onKeyUp = onKeyUpEvent;
        return this;
    }

    public void onCustom(String key, String value) {
        if(custom == null){
            custom = new HashMap<>();
        }
        custom.put(key,value);
    }

    public void onCustom(HashMap<String, String> custom) {
        this.custom = custom;
    }
}
