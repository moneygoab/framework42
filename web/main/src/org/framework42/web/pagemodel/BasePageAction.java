package org.framework42.web.pagemodel;

public enum BasePageAction implements PageAction {
    NONE(1, ""), ADD_TAB(2, "createNewTab"), SUBMIT_FORM(3, "submitForm");

    private final int id;

    private final String identifier;

    BasePageAction(int id, String identifier) {
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

    public static PageAction getById(int id) {

        for(BasePageAction action: BasePageAction.values()) {

            if(id == action.getId()) {

                return action;
            }
        }

        throw new IllegalArgumentException("No base page action with id "+id+" exists!");
    }

    public static PageAction getByIdentifier(String identifier) {

        for(BasePageAction action: BasePageAction.values()) {

            if(action.getIdentifier().equals(identifier)) {

                return action;
            }
        }

        throw new IllegalArgumentException("No base page action with identifier "+identifier+" exists!");
    }


}
