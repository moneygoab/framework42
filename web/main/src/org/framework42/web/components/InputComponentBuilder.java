package org.framework42.web.components;

public abstract class InputComponentBuilder<T extends InputComponentBuilder> extends EventComponentBuilder {

   protected String onBlur = null;

   protected String onChange = null;

   protected String onFocus = null;

   protected String onSelect = null;

   protected String disabled = null;

   protected String className = null;

   protected String id = null;

   protected final String name;

   protected final String value;

   protected InputComponentBuilder(String name, String value) {

      this.name = name;
      this.value = value;

   }

   /**
    * This method should be called from the html generation in the extending class constructor.
    * @return String The generated html code to be included in the extending class constructor.
    * */
   public String addGeneralComponents(){

      String html =  " name=\""+name+"\"";

      if(value.length()>0){
         html += " value=\""+value+"\"";
      }

      html += super.addGeneralComponents();

      if(className!=null){
         html += " class=\""+className+"\"";
      }

      if(disabled!=null){
         html += " "+disabled;
      }

      if(onBlur!=null){
         html += " onblur=\""+onBlur+"\"";
      }

      if(onChange!=null){
         html += " onchange=\""+onChange+"\"";
      }

      if(onFocus!=null){
         html += " onfocus=\""+onFocus+"\"";
      }

      if(onSelect!=null){
         html += " onselect=\""+onSelect+"\"";
      }

      if(id!=null){
         html += " getId=\""+id+"\"";
      }

      return html;

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

}
