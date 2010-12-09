package org.framework42.web.components;

public enum MimeType {

    // Audio
    MP3(MimeCategory.AUDIO, "mpeg"),

    // Images
    JPG(MimeCategory.IMAGE, "jpeg"),
    GIF(MimeCategory.IMAGE, "gif"),
    PNG(MimeCategory.IMAGE, "png"),
    SHORTCUT_ICON(MimeCategory.IMAGE, "x-icon"),
    TIFF(MimeCategory.IMAGE, "tiff"),

    // Texts
    CSS(MimeCategory.TEXT, "css"),
    JAVA_SCRIPT(MimeCategory.TEXT, "javascript"),
    HTML(MimeCategory.TEXT, "html"),
    PLAIN_TEXT(MimeCategory.TEXT, "plain"),
    RITCH_TEXT(MimeCategory.TEXT, "richtext"),
    V_CARD(MimeCategory.TEXT, "x-vcard");

    private MimeCategory category;

    private String mimeType;

    private MimeType(MimeCategory category, String mimeType) {
        this.category = category;
        this.mimeType = mimeType;
    }

    public MimeCategory getCategory() {
        return category;
    }

    public String getMimeType() {
        return mimeType;
    }

    @Override
    public String toString() {
        return category + "/" + mimeType;
    }
}
