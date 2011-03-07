package org.framework42.model;

import org.apache.log4j.Logger;

public enum ImageType {

    PNG(1, "png", new MimeType[]{MimeType.PNG}),
    JPG(2, "jpg", new MimeType[]{MimeType.JPG}),
    GIF(3, "gif", new MimeType[]{MimeType.GIF});

    private final static Logger logger = Logger.getLogger("org.framework42");

    private final int id;

    private final String extension;

    private final MimeType[] mimeTypes;

    ImageType(int id, String extension, MimeType[] mimeTypes) {
        this.id = id;
        this.extension = extension;
        this.mimeTypes = mimeTypes;
    }

    public int getId() {
        return id;
    }

    public String getExtension() {
        return extension;
    }

    public MimeType[] getMimeTypes() {
        return mimeTypes;
    }

    public static ImageType getById(int id) {

        for(ImageType type: ImageType.values()) {

            if(id == type.getId()) {

                return type;
            }
        }

        String mess = "No image type with id "+id+" exists!";
        logger.warn(mess);
        throw new IllegalArgumentException(mess);
    }

    public static ImageType getByMimeType(String mimeType) {

        if("image/jpeg".equalsIgnoreCase(mimeType)) {
            return JPG;
        }

        else if("image/png".equalsIgnoreCase(mimeType)) {
            return PNG;
        }

        else if("image/gif".equalsIgnoreCase(mimeType)) {
            return GIF;
        }

        String mess = "No image type for mime type "+mimeType+" exists!";
        logger.warn(mess);
        throw new IllegalArgumentException(mess);
    }

}
