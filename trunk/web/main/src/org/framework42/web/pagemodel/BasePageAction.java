package org.framework42.web.pagemodel;

public enum BasePageAction implements PageAction {
    NONE(1, ""), ADD_TAB(2, "createNewTab"), REMOVE_TAB(3, "removeTab"), ACTIVATE_TAB(4, "activateTab"), SUBMIT_FORM(10, "submitForm"),
    CREATE_USER(20, "createUser"), UPDATE_USER(21, "updateUser"), DELETE_USER(22, "deleteUser"),
    CHANGE_PASSWORD(30, "changePassword")
    ;
    
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
