package org.framework42.web.plugins.TinyMCEImageHandler;

import org.framework42.web.pagemodel.PageModel;

public class TinyMCEImageHandlerPageModel extends PageModel {

    private boolean imageUploaded;

    private String fileUrl;

    public TinyMCEImageHandlerPageModel() {

        imageUploaded = false;
    }

    public boolean isImageUploaded() {
        return imageUploaded;
    }

    public void setImageUploaded(boolean imageUploaded) {
        this.imageUploaded = imageUploaded;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    protected void setInParametersSpecific() {

    }
}
