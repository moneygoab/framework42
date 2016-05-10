package org.framework42.web.components.standardhtml;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.js_components.JavaScript;
import org.framework42.web.pages.WebPage;

/**
 * Created by marcus on 2016-05-10.
 */
public class RegExField  extends TextField{
    public static String NUMBER_LETTER_REGEX = "0-9a-zA-Z";

    private final String regEx;

    public static RegExField NumberLetterField(String idAndName){
        return new RegExField(idAndName,NUMBER_LETTER_REGEX);
    }

    public RegExField(String idAndName, String regEx) {
        super(idAndName);
        this.regEx = regEx;
    }

    public RegExField(String idAndName, String value, String regEx) {
        super(idAndName,value);
        this.regEx = regEx;
    }

    public RegExField(String idAndName, int value, String regEx) {
        super(idAndName,value);
        this.regEx = regEx;
    }

    public RegExField(String id, String name, String value, String regEx) {
        super(id,name,value);
        this.regEx = regEx;
    }

    private RegExField(Builder builder,String regEx) {
        super(builder);
        this.regEx = regEx;
    }


    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {
        super.generateHtmlSpecific(page, parent, onSameRow);
        htmlBuilder.append(new JavaScript.Builder().addScriptLine("function regExCheck(e){return/^["+ regEx +"]+$/.test(e)}document.getElementById('"+getId()+"').addEventListener(\"input\",function(e){regExCheck(e.target.value)||(e.target.value=e.target.value.substring(0,e.target.value.length-1))});").build().getHtml(page,parent,onSameRow));
    }

    public static class Builder extends TextField.Builder{
        String regEx;
        public Builder(String id, String value,String regEx) {
            super(id, value);
            this.regEx = regEx;
        }

        public Builder(RegExField field) {
            super(field.getId());
            this.regEx = field.regEx;
        }

        @Override
        public RegExField build() {
            return new RegExField(this,regEx);
        }
    }

}
