package org.framework42.web.components.extensions;

import org.framework42.web.components.HtmlComponent;

public abstract class MenuItem extends HtmlComponent {

    protected final boolean active;

    protected final String pageURL;

    protected final int padding;

    protected String backgroundPicture;

    protected String backgroundPictureChosen;

    protected MenuItem(String pageURL, String backgroundPicture, String backgroundPictureChosen) {
        this.pageURL = pageURL;
        this.padding = 30;
        this.backgroundPicture = backgroundPicture;
        this.backgroundPictureChosen = backgroundPictureChosen;
        this.active = false;
    }

    protected MenuItem(String pageURL, int padding, String backgroundPicture, String backgroundPictureChosen) {
        this.pageURL = pageURL;
        this.padding = padding;
        this.backgroundPicture = backgroundPicture;
        this.backgroundPictureChosen = backgroundPictureChosen;
        this.active = false;
    }

    protected MenuItem(String pageURL, int padding, boolean active, String backgroundPicture, String backgroundPictureChosen) {
        this.pageURL = pageURL;
        this.padding = padding;
        this.backgroundPicture = backgroundPicture;
        this.backgroundPictureChosen = backgroundPictureChosen;
        this.active = active;
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
}
