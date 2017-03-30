package org.framework42.web.components.standardhtml;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.js_components.JavaScript;
import org.framework42.web.pages.WebPage;

/**
 * Created by ling on 2017-03-10.
 */
public class DateField extends TextField {

    private final String id;
    private final String format;
    public DateField(String idAndName, String simpleFormat,String value){
        super(idAndName,value);
        this.id = idAndName;
        this.format = simpleFormat;
    }


    public DateField(String idAndName,String value){
        this(idAndName,"yy-mm-dd",value);
    }


    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        JavaScript.Builder js = new JavaScript.Builder();
        js.addScriptLine("$( function() {$( '#"+id+"' ).datepicker({dateFormat: \""+format.toLowerCase()+"\"} )} );");
        htmlBuilder.append(js.build().getHtml(page,parent,onSameRow));


        super.generateHtmlSpecific(page, parent, onSameRow);
    }
}
