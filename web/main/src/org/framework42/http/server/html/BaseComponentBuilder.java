package org.framework42.http.server.html;

public abstract class BaseComponentBuilder<T> implements ComponentBuilder<T> {

    protected Character accessKey = null;

    protected String cssClass = null;

    protected Boolean contentEditable = null;

    //TODO: Implement contextmenu

    //TODO: Implement data-*

    protected TextDirection dir = null;

    //TODO: Implement draggable

    //TODO: Implement dropzone

    protected boolean hidden = false;

    protected String id = null;

    protected String lang = null;

    protected Boolean spellCheck = null;

    protected String style = null;

    protected int tabIndex = 0;

    protected String title = null;

    //TODO: Implement translate

    /**
     * This method should be called from the html generation in the extending class constructor.
     * @return String The generated html code to be included in the extending class constructor.
     * */
    public String addGeneralComponents() {

        StringBuilder html = new StringBuilder();

        if(accessKey!=null) {
            html.append(" accesskey=\"");
            html.append(accessKey);
            html.append("\"");
        }

        if(cssClass!=null) {
            html.append(" class=\"");
            html.append(cssClass);
            html.append("\"");
        }

        if(contentEditable!=null) {
            html.append(" contenteditable=\"");
            html.append(contentEditable.toString());
            html.append("\"");
        }

        if(dir!=null) {
            html.append(" dir=\"");
            html.append(dir.getValue());
            html.append("\"");
        }

        if(hidden) {

            html.append(" hidden");
        }

        if(id!=null) {
            html.append(" id=\"");
            html.append(id);
            html.append("\"");
        }

        if(lang!=null) {
            html.append(" lang=\"");
            html.append(lang);
            html.append("\"");
        }

        if(spellCheck !=null) {
            html.append(" spellcheck=\"");
            html.append(spellCheck.toString());
            html.append("\"");
        }

        if(style!=null) {
            html.append(" style=\"");
            html.append(style);
            html.append("\"");
        }

        if(tabIndex>0) {
            html.append(" tabindex=\"");
            html.append(tabIndex);
            html.append("\"");
        }

        if(title!=null) {
            html.append(" title=\"");
            html.append(title);
            html.append("\"");
        }

        return html.toString();
    }

    public BaseComponentBuilder<T> accessKey(Character accessKey) {
        this.accessKey = accessKey;
        return this;
    }

    public BaseComponentBuilder<T> cssClass(String cssClass) {
        this.cssClass = cssClass;
        return this;
    }

    public BaseComponentBuilder<T> contentEditable(Boolean contentEditable) {
        this.contentEditable = contentEditable;
        return this;
    }

    public BaseComponentBuilder<T> dir(TextDirection dir) {
        this.dir = dir;
        return this;
    }

    public BaseComponentBuilder<T> hidden(boolean hidden) {
        this.hidden = hidden;
        return this;
    }

    public BaseComponentBuilder<T> id(String id) {
        this.id = id;
        return this;
    }

    public BaseComponentBuilder<T> lang(String lang) {
        this.lang = lang;
        return this;
    }

    public BaseComponentBuilder<T> spellCheck(Boolean spellCheck) {
        this.spellCheck = spellCheck;
        return this;
    }

    public BaseComponentBuilder<T> style(String style) {
        this.style = style;
        return this;
    }

    public BaseComponentBuilder<T> tabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
        return this;
    }

    public BaseComponentBuilder<T> title(String title) {
        this.title = title;
        return this;
    }

}
