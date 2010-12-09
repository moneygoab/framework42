package org.framework42.web.components.extensions;

import org.framework42.web.components.HtmlComponent;

import java.util.Locale;

public abstract class MenuItem extends HtmlComponent {

    protected boolean active;

    protected final String pageURL;

    protected final int padding;

    protected final String id;

    protected Locale locale;

    protected String backgroundPicture;

    protected String backgroundPictureChosen;

    protected String backgroundPictureMouseOver;

    protected MenuItem(String id, Locale locale, String pageURL, String backgroundPicture, String backgroundPictureChosen, String backgroundPictureMouseOver) {
        this.id = id;
        this.locale = locale;
        this.pageURL = pageURL;
        this.padding = 30;
        this.backgroundPicture = backgroundPicture;
        this.backgroundPictureChosen = backgroundPictureChosen;
        this.backgroundPictureMouseOver = backgroundPictureMouseOver;
        this.active = false;
    }

    protected MenuItem(String id, Locale locale, String pageURL, int padding, String backgroundPicture, String backgroundPictureChosen, String backgroundPictureMouseOver) {
        this.id = id;
        this.locale = locale;
        this.pageURL = pageURL;
        this.padding = padding;
        this.backgroundPicture = backgroundPicture;
        this.backgroundPictureChosen = backgroundPictureChosen;
        this.backgroundPictureMouseOver = backgroundPictureMouseOver;
        this.active = false;
    }

    protected MenuItem(String id, Locale locale, String pageURL, int padding, boolean active, String backgroundPicture, String backgroundPictureChosen, String backgroundPictureMouseOver) {
        this.id = id;
        this.locale = locale;
        this.pageURL = pageURL;
        this.padding = padding;
        this.backgroundPicture = backgroundPicture;
        this.backgroundPictureChosen = backgroundPictureChosen;
        this.backgroundPictureMouseOver = backgroundPictureMouseOver;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public String getPageURL() {
        return pageURL;
    }

    public String getBackgroundPicture() {
        return backgroundPicture;
    }

    public String getBackgroundPictureChosen() {
        return backgroundPictureChosen;
    }

    public boolean isActive() {
        return active;
    }

    public int getPadding() {
        return padding;
    }

    public String getBackgroundPictureMouseOver() {
        return backgroundPictureMouseOver;
    }
}
