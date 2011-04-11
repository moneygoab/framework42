package org.framework42.web.pagemodel;

public class PageActionImpl implements PageAction {

    private final int id;

    private final String identifier;

    public PageActionImpl(int id, String identifier) {
        this.id = id;
        this.identifier = identifier;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    
}
