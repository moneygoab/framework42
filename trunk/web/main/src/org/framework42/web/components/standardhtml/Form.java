package org.framework42.web.components.standardhtml;

import org.framework42.model.MimeType;
import org.framework42.web.components.*;
import org.framework42.web.components.js_components.JavaScript;
import org.framework42.web.pagemodel.BasePageAction;
import org.framework42.web.pagemodel.PageAction;
import org.framework42.web.pagemodel.Parameter;
import org.framework42.web.pages.WebPage;
import org.framework42.web.utils.Util;

import java.util.ArrayList;
import java.util.List;

import static org.framework42.utils.NullChecker.notNull;

public class Form extends HtmlComponent {

    public static String ERROR_BG_COLOR = "#ff7777";

    private Builder builder;

    private Form(Builder builder) {

        this.builder = builder;

    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append(createValidation().getHtml(page, parent, false));

        htmlBuilder.append("<form name=\"");
        htmlBuilder.append(builder.name);
        htmlBuilder.append("\" action=\"");
        htmlBuilder.append(builder.action);
        htmlBuilder.append("\" ");
        htmlBuilder.append("method=\"");
        htmlBuilder.append(builder.postMethod.getName());
        htmlBuilder.append("\"");

        htmlBuilder.append(builder.addGeneralComponents());

        if(builder.id != null ) {
            htmlBuilder.append(" id=\"");
            htmlBuilder.append(builder.id);
            htmlBuilder.append("\"");
        }

        if(builder.onReset!=null){
            htmlBuilder.append(" onreset=\"");
            htmlBuilder.append(builder.onReset);
            htmlBuilder.append("\"");
        }

        if(builder.onSubmit!=null){
            htmlBuilder.append(" onsubmit=\"");
            htmlBuilder.append(builder.onSubmit);
            htmlBuilder.append("\"");
        }

        if(builder.accept!=null){
            htmlBuilder.append(" accept=\"");
            htmlBuilder.append(builder.accept);
            htmlBuilder.append("\"");
        }

        if(builder.encodingType!=null){
            htmlBuilder.append(" enctype=\"");
            htmlBuilder.append(builder.encodingType);
            htmlBuilder.append("\"");
        }

        if(builder.style!=null){
            htmlBuilder.append(" style=\"");
            htmlBuilder.append(builder.style);
            htmlBuilder.append("\"");
        }

        if(builder.target != null) {
            htmlBuilder.append(" target=\"");
            htmlBuilder.append(builder.target.getId());
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">\n");

        htmlBuilder.append(new Hidden("form_action", builder.actionId).getHtml(page, parent, false));

        for(HtmlComponent component:builder.formComponents){
            htmlBuilder.append(component.getHtml(page, this, false));
            htmlBuilder.append("\n");
        }

        htmlBuilder.append(new Hidden.Builder("action", builder.submitPageAction.getIdentifier()).build().getHtml(page, this, false));

        htmlBuilder.append(Util.tab(tabs));
        htmlBuilder.append("</form>\n");

    }

    private JavaScript createValidation() {

        List<HtmlComponentInput> componentList = builder.getInputComponents();

        JavaScript.Builder javaScript = new JavaScript.Builder();

        javaScript.addScriptLine("var oldStyle = {};");

        if(componentList.size()>0) {

            javaScript.addScriptLine("function validateFormComponent(componentId, parameterType, isRequired) {");
            javaScript.addScriptLine("\t");
            javaScript.addScriptLine("\t if(parameterType == 'STRING') {");
            javaScript.addScriptLine("\t\t var whiteString = document.getElementById(componentId).value.replace(/^\\s+|\\s+$/g, '') ;");
            javaScript.addScriptLine("\t\t if((document.getElementById(componentId).value == '' || whiteString.length == 0) && isRequired) {");
            javaScript.addScriptLine("\t\t\t");
            javaScript.addScriptLine("\t\t\t setFormComponentLook(componentId);");
            javaScript.addScriptLine("\t\t\t return false;");
            javaScript.addScriptLine("\t\t }");
            javaScript.addScriptLine("\t } else if(parameterType == 'COMBO_BOX') {");
            javaScript.addScriptLine("\t\t ");
            javaScript.addScriptLine("\t\t if(document.getElementById(componentId).value == '0' && isRequired) {");
            javaScript.addScriptLine("\t\t\t");
            javaScript.addScriptLine("\t\t\t setFormComponentLook(componentId);");
            javaScript.addScriptLine("\t\t\t return false;");
            javaScript.addScriptLine("\t\t }");
            javaScript.addScriptLine("\t }");
            javaScript.addScriptLine("\t");
            javaScript.addScriptLine("\t if(oldStyle[componentId] != null && oldStyle[componentId].background != null) {");
            javaScript.addScriptLine("\t\t document.getElementById(componentId).style.background = oldStyle[componentId].background;");
            javaScript.addScriptLine("\t }");
            //javaScript.addScriptLine("\t }");
            javaScript.addScriptLine("\t");
            javaScript.addScriptLine("\t return true;");

            javaScript.addScriptLine("}");
            javaScript.addScriptLine("");
            javaScript.addScriptLine("function setFormComponentLook(componentId) {");
            javaScript.addScriptLine("\t if(oldStyle[componentId] == null) {");
            javaScript.addScriptLine("\t\t oldBackground = document.getElementById(componentId).style.background;");
            javaScript.addScriptLine("\t\t oldStyle[componentId] = {background:oldBackground};");
            javaScript.addScriptLine("\t }");
            javaScript.addScriptLine("\t document.getElementById(componentId).style.background = '"+builder.errorBackgroundColor+"';");
            javaScript.addScriptLine("}");
            javaScript.addScriptLine("");
            javaScript.addScriptLine("function validateForm() {");
            javaScript.addScriptLine("\t numberOfUnvalidated = 0;");
            for(HtmlComponentInput component: componentList) {

                if(component.getId()!=null) {
                    Parameter param = component.getParameter();
                    javaScript.addScriptLine("\t if(!validateFormComponent('"+component.getId()+"', '"+param.getParameterType().name()+"', "+param.isRequired()+")) {");
                    javaScript.addScriptLine("\t\t numberOfUnvalidated++;");
                    javaScript.addScriptLine("\t }");
                    javaScript.addScriptLine("\t");
                }
            }
            javaScript.addScriptLine("\t if(numberOfUnvalidated>0) {");
            javaScript.addScriptLine("\t\t return false;");
            javaScript.addScriptLine("\t } else {");
            javaScript.addScriptLine("\t\t return true;");
            javaScript.addScriptLine("\t }");
            javaScript.addScriptLine("}");

        }

        return javaScript.build();
    }

    public static class Builder extends EventComponentBuilder<Form> implements HtmlComponentStorage<HtmlComponent> {

        private final String name;
        private final String action;
        private final String actionId;
        private final HtmlPostMethod postMethod;
        private final List<HtmlComponent> formComponents;

        private final List<HtmlComponent> validationComponents;

        private String id = null;

        private String onReset = null;

        private String onSubmit = null;

        private String accept = null;

        private String encodingType = null;

        private LinkTarget target = null;

        private String style = null;

        private String errorBackgroundColor = null;

        private PageAction submitPageAction = BasePageAction.SUBMIT_FORM;

        public Builder(String name, String action, String actionId) {

            this(name, action, actionId, HtmlPostMethod.POST);
        }

        public Builder(String name, String action, PageAction pageAction) {

            this(name, action, pageAction.getIdentifier(), HtmlPostMethod.POST);
        }

        public Builder(String name, String action, String actionId, HtmlPostMethod postMethod) {
            this.name = notNull(name, "Name can't be null!");
            this.action = notNull(action, "Action can't be null!");
            this.actionId = notNull(actionId, "Action id can't be null!");
            this.postMethod = notNull(postMethod, "Post method can't be null!");
            this.formComponents = new ArrayList<HtmlComponent>();
            this.validationComponents = new ArrayList<HtmlComponent>();

            this.onSubmit = "return validateForm();";
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder onReset(String onReset){
            this.onReset = onReset;
            return this;
        }

        public Builder onSubmit(String onSubmit) {
            this.onSubmit = onSubmit;
            return this;
        }

        public Builder accept(MimeType accept){
            this.accept = accept.toString();
            return this;
        }

        public Builder encodingType(EncodingType encodingType){
            this.encodingType = encodingType.getEncodingType();
            return this;
        }

        public Builder style(String style){
            this.style = style;
            return this;
        }

        public Builder target(LinkTarget target) {

            this.target = target;
            return this;
        }

        public Builder submitPageAction(PageAction submitPageAction) {
            this.submitPageAction = submitPageAction;
            return this;
        }

        public Builder addValidationComponent(HtmlComponent component) {

            this.validationComponents.add(component);

            return this;
        }

        public Builder errorBackgroundColor(String errorBackgroundColor) {

            if(!errorBackgroundColor.startsWith("#")) {
                errorBackgroundColor = "#"+errorBackgroundColor;
            }
            this.errorBackgroundColor = errorBackgroundColor;

            return this;
        }

        public List<HtmlComponentInput> getInputComponents() {

            List<HtmlComponentInput> foundComponents = new ArrayList<HtmlComponentInput>();

            for(HtmlComponent component: formComponents) {

                if(component instanceof HtmlComponentInput) {

                    foundComponents.add((HtmlComponentInput)component);

                }
            }
            for(HtmlComponent component: validationComponents) {

                if(component instanceof HtmlComponentInput) {

                    foundComponents.add((HtmlComponentInput)component);

                }
            }

            return foundComponents;
        }

        public void add(HtmlComponent... htmlComponent) {

            for(HtmlComponent comp: htmlComponent) {

                formComponents.add(comp);
            }
        }

        @Override
        public void add(HtmlComponent htmlComponent) {
            formComponents.add(htmlComponent);
        }

        @Override
        public Form build() {

            if(errorBackgroundColor==null) {
                errorBackgroundColor = Form.ERROR_BG_COLOR;
            }

            return new Form(this);
        }

    }

}
