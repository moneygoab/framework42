package org.framework42.web.components.advanced;

import org.apache.commons.io.IOUtils;
import org.framework42.web.components.*;
import org.framework42.web.components.js_components.JavaScript;
import org.framework42.web.components.standardhtml.Break;
import org.framework42.web.components.standardhtml.Div;
import org.framework42.web.components.standardhtml.SubmitButton;
import org.framework42.web.pages.WebPage;

import java.io.IOException;
import java.util.HashMap;


/**
 * Created by ling on 2017-04-26.
 */
public class PopupDiv extends HtmlComponent {

    public static final String VERTICAL_POSITION_TOP = "top";
    public static final String VERTICAL_POSITION_BOTTOM = "bottom";
    public static final String HORIZONTAL_POSITION_LEFT = "left";
    public static final String HORIZONTAL_POSITION_RIGHT = "right";

    private Builder builder;

    public PopupDiv(Builder builder) {
        this.builder = builder;
    }

    public PopupDiv(String text, HtmlComponent content) {
        Builder builder = new Builder();
        builder.content = content;
        builder.text = text;
        this.builder = builder;
    }

    @Override
    protected void generateHtmlSpecific(WebPage webPage, HtmlComponent htmlComponent, boolean b) {

        ComponentGroup.Builder components = new ComponentGroup.Builder();

        JavaScript js = new JavaScript(getDependency());
        components.add(js);

        StringBuilder customHtml = new StringBuilder();

        for (HashMap.Entry<String, String> entry : builder.custom.entrySet()) {
            customHtml.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
        }

        RawHtml html = new RawHtml("<button id='" + builder.id + "_id' class='" + builder.id + "_open " + builder.className + "' onclick=\"" + builder.onclick + "\"  " + (builder.className.isEmpty() ? "style='background:none!important; color:inherit; border:none; padding:0!important; font: inherit;border-bottom:1px solid #444; cursor: pointer;'" : "") + " " + customHtml.toString() + " >" + builder.text + "</button>");

        components.add(html);

        Div.Builder popupDiv = new Div.Builder(builder.id);
        popupDiv.style("padding:10px;min-width:" + (builder.width_mini) + "px;max-width:" + builder.width_max + "px;min-height:" + builder.height + "px;box-shadow: 0 0 10px rgba(0,0,0,0.3);border: 1px solid #e3e3e3;background-color: #f5f5f5; border-radius: 8px;");
        popupDiv.add(builder.content);
        popupDiv.add(new SubmitButton.Builder("" + builder.id + "_close", "X").className("" + builder.id + "_close")
                .style("right:5px;width:20px;height:20px; position: absolute;top: 5px;padding: 0;border: none;background: #65c829;").build());
        components.add(popupDiv.build());
        JavaScript.Builder jsBuild = new JavaScript.Builder();

        jsBuild.addScriptLine("$(document).ready(function() {");

        jsBuild.addScriptLine("$('#" + builder.id + "').popup({" +
                "offsetleft:" + builder.offsetLeft + "," +
                "offsettop:" + builder.offsetTop + "," +
                "vertical:'" + builder.vertical + "'," +
                "horizontal:'" + builder.horizontal + "'," +
                "backgroundactive:true," +
                "type:'tooltip'," +
                "tooltipanchor: $('#" + builder.id + "_id')});");
        jsBuild.addScriptLine(" });");

        components.add(jsBuild.build());

        htmlBuilder.append(components.build().getHtml(webPage, htmlComponent, false));

    }

    private String getDependency() {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            return IOUtils.toString(classLoader.getResourceAsStream("jquery.popupoverlay.js"), "UTF-8");
        } catch (IOException ex) {
        } catch (NullPointerException ex) {

            logger.error("PopupDiv.getDependency() didn't find jquery.popupoverlay.js" + ex);
            return "";
        }

        throw new RuntimeException("Could not load web lib javascript file -- jquery.popupoverlay.js");
    }

    public static class Builder implements ComponentBuilder<PopupDiv> {

        private HtmlComponent content;

        private String onclick = "";

        private String text = "";

        private int offsetLeft = 0;

        private int offsetTop = 0;

        private int width_mini = 300;

        private int width_max = 320;

        private int height = 150;

        private String className = "";

        private String horizontal = "center";

        private String vertical = "center";

        private String id = "my_popup";

        private HashMap<String, String> custom = new HashMap<>();

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setContent(HtmlComponent content) {
            this.content = content;
            return this;
        }

        public Builder setOnclick(String onclick) {
            this.onclick = onclick;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setOffsetLeft(int offsetLeft) {
            this.offsetLeft = offsetLeft;
            return this;
        }

        public Builder setOffsetTop(int offsetTop) {
            this.offsetTop = offsetTop;
            return this;
        }

        public Builder setHorizontal(String horizontal) {
            this.horizontal = horizontal;
            return this;
        }

        public Builder setVertical(String vertical) {
            this.vertical = vertical;
            return this;
        }

        public Builder setClass(String className) {
            this.className = className;
            return this;
        }

        public Builder setWidth(int width_mini, int width_max) {
            this.width_mini = width_mini;
            this.width_max = width_max;
            return this;
        }

        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }

        public Builder onCustom(String key, String value) {
            if (custom == null) {
                custom = new HashMap<>();
            }
            custom.put(key, value);
            return this;
        }

        public Builder onCustom(HashMap<String, String> custom) {
            this.custom = custom;
            return this;
        }


        @Override
        public PopupDiv build() {
            return new PopupDiv(this);
        }
    }


}
