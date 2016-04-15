package org.framework42.web.components.standardhtml;

import org.framework42.utils.services.StringManipulator;
import org.framework42.utils.services.impl.StringManipulatorImpl;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.js_components.JavaScript;
import org.framework42.web.pages.WebPage;

/**
 * Created by marcus on 2016-04-15.
 */
public class NumberField extends TextField {

    private final String id;
    private final int step;
    private final int maxValue;

    public NumberField(String idAndName) {
        super(idAndName);
        this.step = 0;
        this.maxValue = 0;
        this.id = idAndName;
    }

    public NumberField(String idAndName, String value) {
        super(idAndName,value);
        this.step = 0;
        this.maxValue = 0;
        this.id = idAndName;
    }

    public NumberField(String idAndName, int value) {
        super(idAndName,value);
        this.step = 0;
        this.maxValue = 0;
        this.id = idAndName;
    }

    public NumberField(String id, String name, String value) {
        super(id,name,value);
        this.step = 0;
        this.maxValue = 0;
        this.id = id;
    }

    public NumberField(String idAndName, String value,int maxValue, int step) {
        super(idAndName,value);
        this.step = step;
        this.maxValue = maxValue;
        this.id = idAndName;
    }


    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        if(step != 0 && maxValue != 0){
            JavaScript.Builder js = new JavaScript.Builder();
            js.addScriptLine("$(function() { var definedValues = [ ");
            StringManipulator mani = new StringManipulatorImpl();
            for(int i = step; i < maxValue; i+=step ){
                if( i == maxValue-step){
                    js.addScriptLine("'"+mani.formatMoney(i)+"'");
                }else{
                    js.addScriptLine("'"+mani.formatMoney(i)+"'" +",");
                }

            }
            js.addScriptLine("]; $('#"+id+"').autocomplete({ source: definedValues }); });");
            htmlBuilder.append(js.build().getHtml(page,parent,onSameRow));
        }

        super.generateHtmlSpecific(page, parent, onSameRow);
        htmlBuilder.append(new JavaScript.Builder().addScriptLine("function isNumber(n) { return /^[0-9 ]+$/.test(n); } function formatMoney(n){var tal = n.replace(/\\s/g, '');if(tal.length > 5){tal = tal.substring(0,3) + ' ' + tal.substring(3,tal.length);}else if(tal.length > 4){tal = tal.substring(0,2) + ' ' + tal.substring(2,tal.length);}return tal;}document.getElementById('"+id+"').addEventListener('input', function (e) {if(isNumber(e.target.value)){e.target.value = formatMoney(e.target.value);}else{e.target.value = e.target.value.substring(0, e.target.value.length - 1);}});").build().getHtml(page,parent,onSameRow));
    }
}
