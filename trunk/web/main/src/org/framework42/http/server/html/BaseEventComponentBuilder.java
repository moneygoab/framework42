package org.framework42.http.server.html;

public abstract class BaseEventComponentBuilder<T> extends BaseComponentBuilder<T> implements ComponentBuilder<T> {

    protected String onClick = null;

    protected String onDoubleClick = null;

    protected String onDrag = null;

    protected String onDragEnd = null;

    protected String onDragEnter = null;

    protected String onDragLeave = null;

    protected String onDragOver = null;

    protected String onDragStart = null;

    protected String onDrop = null;

    protected String onMouseDown = null;

    protected String onMouseMove = null;

    protected String onMouseOut = null;

    protected String onMouseOver = null;

    protected String onMouseUp = null;

    protected String onScroll = null;

    protected String onWheel = null;

    /**
     * This method should be called from the html generation in the extending class constructor.
     * @return String The generated html code to be included in the extending class constructor.
     * */
    public String addGeneralComponents(){

        StringBuilder html = new StringBuilder();

        html.append(super.addGeneralComponents());

        if(onClick != null){
            html.append(" onclick=\"");
            html.append(onClick);
            html.append("\"");
        }

        if(onDoubleClick != null){
            html.append(" ondblclick=\"");
            html.append(onDoubleClick);
            html.append("\"");
        }

        if(onDrag != null){
            html.append(" ondrag=\"");
            html.append(onDrag);
            html.append("\"");
        }

        if(onDragEnd != null){
            html.append(" ondragend=\"");
            html.append(onDragEnd);
            html.append("\"");
        }

        if(onDragEnter != null){
            html.append(" ondragenter=\"");
            html.append(onDragEnter);
            html.append("\"");
        }

        if(onDragLeave != null){
            html.append(" ondragleave=\"");
            html.append(onDragLeave);
            html.append("\"");
        }

        if(onDragOver != null){
            html.append(" ondragover=\"");
            html.append(onDragOver);
            html.append("\"");
        }

        if(onDragStart != null){
            html.append(" ondragstart=\"");
            html.append(onDragStart);
            html.append("\"");
        }

        if(onDrop != null){
            html.append(" ondrop=\"");
            html.append(onDrop);
            html.append("\"");
        }

        if(onMouseDown != null){
            html.append(" onmousedown=\"");
            html.append(onMouseDown);
            html.append("\"");
        }

        if(onMouseMove != null){
            html.append(" onmousemove=\"");
            html.append(onMouseMove);
            html.append("\"");
        }

        if(onMouseOut != null){
            html.append(" onmouseout=\"");
            html.append(onMouseOut);
            html.append("\"");
        }

        if(onMouseOver != null){
            html.append(" onmouseover=\"");
            html.append(onMouseOver);
            html.append("\"");
        }

        if(onMouseUp != null){
            html.append(" onmouseup=\"");
            html.append(onMouseUp);
            html.append("\"");
        }

        if(onScroll != null){
            html.append(" onscroll=\"");
            html.append(onScroll);
            html.append("\"");
        }

        if(onWheel != null){
            html.append(" onwheel=\"");
            html.append(onWheel);
            html.append("\"");
        }

        return html.toString();
    }

    public BaseEventComponentBuilder<T> onClick(String onClickEvent){
        this.onClick = onClickEvent;
        return this;
    }

    public BaseEventComponentBuilder<T> onDoubleClick(String onDoubleClickEvent){
        this.onDoubleClick = onDoubleClickEvent;
        return this;
    }

    public BaseEventComponentBuilder<T> onDrag(String onDragEvent){
        this.onDrag = onDragEvent;
        return this;
    }

    public BaseEventComponentBuilder<T> onDragEnd(String onDragEndEvent){
        this.onDragEnd = onDragEndEvent;
        return this;
    }

    public BaseEventComponentBuilder<T> onDragEnter(String onDragEnterEvent){
        this.onDragEnter = onDragEnterEvent;
        return this;
    }

    public BaseEventComponentBuilder<T> onDragLeave(String onDragLeaveEvent){
        this.onDragLeave = onDragLeaveEvent;
        return this;
    }

    public BaseEventComponentBuilder<T> onDragOver(String onDragOverEvent){
        this.onDragOver = onDragOverEvent;
        return this;
    }

    public BaseEventComponentBuilder<T> onDragStart(String onDragStartEvent){
        this.onDragStart = onDragStartEvent;
        return this;
    }

    public BaseEventComponentBuilder<T> onDrop(String onDropEvent){
        this.onDrop = onDropEvent;
        return this;
    }

    public BaseEventComponentBuilder<T> onMouseDown(String onMouseDownEvent){
        this.onMouseDown = onMouseDownEvent;
        return this;
    }

    public BaseEventComponentBuilder<T> onMouseMove(String onMouseMoveEvent){
        this.onMouseMove = onMouseMoveEvent;
        return this;
    }

    public BaseEventComponentBuilder<T> onMouseOut(String onMouseOutEvent){
        this.onMouseOut = onMouseOutEvent;
        return this;
    }

    public BaseEventComponentBuilder<T> onMouseOver(String onMouseOverEvent){
        this.onMouseOver = onMouseOverEvent;
        return this;
    }

    public BaseEventComponentBuilder<T> onMouseUp(String onMouseUpEvent){
        this.onMouseUp = onMouseUpEvent;
        return this;
    }

    public BaseEventComponentBuilder<T> onScroll(String onScrollEvent){
        this.onScroll = onScrollEvent;
        return this;
    }

    public BaseEventComponentBuilder<T> onWheel(String onWheelEvent){
        this.onWheel = onWheelEvent;
        return this;
    }


}
