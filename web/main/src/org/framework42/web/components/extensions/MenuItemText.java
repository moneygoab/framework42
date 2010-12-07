package org.framework42.web.components.extensions;

import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.standardhtml.Label;
import org.framework42.web.utils.Util;

public class MenuItemText extends MenuItem {

    private Label text;

    public MenuItemText(Label text, String pageURL, String backgroundPicture, String backgroundPictureChosen) {
        super(pageURL, backgroundPicture, backgroundPictureChosen);
        this.text = text;
    }

    public MenuItemText(Label text, String pageURL, int padding, String backgroundPicture, String backgroundPictureChosen) {
        super(pageURL, padding, backgroundPicture, backgroundPictureChosen);
        this.text = text;
    }

    public MenuItemText(Label text, String pageURL, int padding, boolean active, String backgroundPicture, String backgroundPictureChosen) {
        super(pageURL, padding, active, backgroundPicture, backgroundPictureChosen);
        this.text = text;
    }

    @Override
    protected void generateHtmlSpecific(HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append("<span style=\"padding-right: ");
        htmlBuilder.append(padding);
        htmlBuilder.append("px; padding-left: 5px; padding-top: 5px; padding-bottom: 10px; ");
        if(active) {
            htmlBuilder.append("background-image: url('");
            htmlBuilder.append(backgroundPictureChosen);
            htmlBuilder.append("');");
            htmlBuilder.append("\" ");

            htmlBuilder.append("class=\"main_menu_item_active\"");

        } else{
            htmlBuilder.append("\"");
        }

        htmlBuilder.append(">");

        htmlBuilder.append("<a href=\"");
        htmlBuilder.append(pageURL);
        htmlBuilder.append("\"");
        if(active) {
            htmlBuilder.append(" class=\"main_menu_item_active_link\"");
        }
        htmlBuilder.append(">");

        htmlBuilder.append(text.getHtml(parent, true));

        htmlBuilder.append("</a>");

        htmlBuilder.append("</span>");

        html = htmlBuilder.toString();

    }
}
