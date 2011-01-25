package org.framework42.web.components.extensions;

import org.framework42.i18n.I18N;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.standardhtml.Label;
import org.framework42.web.pages.ChildPage;
import org.framework42.web.pages.WebPage;

import java.util.Locale;

public class MenuItemText extends MenuItem {

    private Label text;

    public MenuItemText(String id, Locale locale, Label text, String pageURL, String backgroundPicture, String backgroundPictureChosen, String backgroundPictureMouseOver) {
        super(id, locale, pageURL, backgroundPicture, backgroundPictureChosen, backgroundPictureMouseOver);
        this.text = text;
    }

    public MenuItemText(String id, Locale locale, Label text, String pageURL, int padding, String backgroundPicture, String backgroundPictureChosen, String backgroundPictureMouseOver) {
        super(id, locale, pageURL, padding, backgroundPicture, backgroundPictureChosen, backgroundPictureMouseOver);
        this.text = text;
    }

    public MenuItemText(String id, Locale locale, Label text, String pageURL, int padding, boolean active, String backgroundPicture, String backgroundPictureChosen, String backgroundPictureMouseOver) {
        super(id, locale, pageURL, padding, active, backgroundPicture, backgroundPictureChosen, backgroundPictureMouseOver);
        this.text = text;
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        if(page instanceof ChildPage) {
            String parentURL = I18N.INSTANCE.getURL(((ChildPage)page).getParentPageURLKey(), locale);
            if(parentURL.equalsIgnoreCase(pageURL)) {
                active = true;
            }
        }

        htmlBuilder.append("<span getId=\"");
        htmlBuilder.append(id);
        htmlBuilder.append("\" style=\"padding-right: ");
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

        if(!active) {
            //htmlBuilder.append(" onMouseOver=\"changeMenuBackground('"+getId+"','"+backgroundPictureMouseOver+"');\"");
            //htmlBuilder.append(" onMouseOut=\"removeMenuBackground('"+getId+"');\"");
        }

        htmlBuilder.append(">");

        htmlBuilder.append(text.getHtml(page, parent, true));

        htmlBuilder.append("</a>");

        htmlBuilder.append("</span>");

        html = htmlBuilder.toString();

    }
}
