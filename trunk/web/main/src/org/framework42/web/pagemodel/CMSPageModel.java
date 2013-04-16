package org.framework42.web.pagemodel;

public class CMSPageModel extends PageModel {

    protected int pageId;

    protected CMSDataProvider dataProvider;

    protected CMSSiteConfiguration siteConfiguration;

    public CMSPageModel() {
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public CMSDataProvider getDataProvider() {
        return dataProvider;
    }

    public void setDataProvider(CMSDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public CMSSiteConfiguration getSiteConfiguration() {
        return siteConfiguration;
    }

    public void setSiteConfiguration(CMSSiteConfiguration siteConfiguration) {
        this.siteConfiguration = siteConfiguration;
    }

    @Override
    protected void setInParametersSpecific() {

    }
}
