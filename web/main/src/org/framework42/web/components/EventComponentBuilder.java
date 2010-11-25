package org.framework42.web.components;

public abstract class EventComponentBuilder<T extends EventComponentBuilder> implements ComponentBuilder {

   protected String onClick = null;

   protected String onDoubleClick = null;

   protected String onMouseDown = null;

   protected String onMouseMove = null;

   protected String onMouseOut = null;

   protected String onMouseOver = null;

   protected String onMouseUp = null;

   protected String onKeyDown = null;

   protected String onKeyPress = null;

   protected String onKeyUp = null;

   /**
    * This method should be called from the html generation in the extending class constructor.
    * @return String The generated html code to be included in the extending class constructor.
    * */
   public String addGeneralComponents(){

      String html = "";

      if(onClick != null){
         html += " onclick=\""+onClick+"\"";
      }

      if(onDoubleClick != null){
         html += " ondblclick=\""+onDoubleClick+"\"";
      }

      if(onMouseDown != null){
         html += " onmousedown=\""+onMouseDown+"\"";
      }

      if(onMouseMove != null){
         html += " onmousemove=\""+onMouseMove+"\"";
      }

      if(onMouseOut != null){
         html += " onmouseout=\""+onMouseOut+"\"";
      }

      if(onMouseOver != null){
         html += " onmouseover=\""+onMouseOver+"\"";
      }

      if(onMouseUp != null){
         html += " onmouseup=\""+onMouseUp+"\"";
      }

      if(onKeyDown != null){
         html += " onkeydown=\""+onKeyDown+"\"";
      }

      if(onKeyPress != null){
         html += " onkeypress=\""+onKeyPress+"\"";
      }

      if(onKeyUp != null){
         html += " onkeyup=\""+onKeyUp+"\"";
      }


      return html;

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

}
