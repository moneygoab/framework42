package org.framework42.web.pagemodel;

import org.apache.commons.fileupload.FileItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that represents the data model of an page, the model part in the MVC concept.
 * */
public abstract class PageModel {

    protected boolean error;
    protected String errorMessage;

    protected String pageTitleKey;
    protected String pageKeywordsKey;
    protected String pageDescriptionKey;
    protected String pageCharacterSet;

    protected PageAction currentPageAction;

    protected Map<String, Parameter> inParameters;

    protected Map<String, Parameter> pageParameters;

    protected List<FileItem> fileItemList;

    protected Map<String, String> environmentInformation;

    /**
     * The constructor
     * */
    protected PageModel() {
        error = false;
        inParameters = new HashMap<String, Parameter>();
        pageParameters = new HashMap<String, Parameter>();
        fileItemList = new ArrayList<FileItem>();
        environmentInformation = new HashMap<String, String>();
        pageTitleKey = "page_title";
        pageKeywordsKey = "page_keywords";
        pageDescriptionKey = "page_description";
        pageCharacterSet = "utf-8";
    }
        
    public boolean isError() {
        return error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setError(String errorMessage) {
        this.error = true;
        this.errorMessage = errorMessage;
    }

    public void revertError() {
        this.error = false;
        this.errorMessage = null;
    }

    public String getPageTitleKey() {
        return pageTitleKey;
    }

    public String getPageKeywordsKey() {
        return pageKeywordsKey;
    }

    public String getPageDescriptionKey() {
        return pageDescriptionKey;
    }

    public String getPageCharacterSet() {
        return pageCharacterSet;
    }

    public PageAction getCurrentPageAction() {
        return currentPageAction;
    }

    public void setCurrentPageAction(PageAction currentPageAction) {
        this.currentPageAction = currentPageAction;
    }

    public boolean isCurrentPageAction(PageAction pageAction) {

        if(currentPageAction.getIdentifier().equals(pageAction.getIdentifier())) {
            return true;
        }
        return false;
    }

    public String getInParameterAsString(String key) {
        return inParameters.get(key).getAsString();
    }

    public Integer getInParameterAsInt(String key) {
        return inParameters.get(key).getAsInt();
    }

    /**
     * Returns the html parameters that was sent to the page.
     * @return Returns the html parameters that was sent to the page.
     * */
    public Map<String, Parameter> getInParameters() {
        return inParameters;
    }

    /**
     * Sets the html parameters that was sent to the page.
     * @param inParameters      The html parameters that was sent to the page.
     * */
    public void setInParameters(Map<String, Parameter> inParameters) {

        this.inParameters = inParameters;

        setInParametersSpecific();
    }

    protected abstract void setInParametersSpecific();

    /**
     * Returns the html parameters that is defined as page parameters. They don't contain any values only definition of parameters,
     * @return Returns the html parameters that is defined as page parameters.
     * */
    public Map<String, Parameter> getPageParameters() {
        return pageParameters;
    }

    /**
     * Adds a page parameter to the model.
     * @param parameter     The parameter definition to add to the page.
     * */
    public void addPageParameter(Parameter parameter) {
        pageParameters.put(parameter.getParameterName(), parameter);
    }

    public List<FileItem> getFileItemList() {
        return fileItemList;
    }

    public Map<String, String> getEnvironmentInformation() {
        return environmentInformation;
    }
}
