package org.framework42.model;

public enum ContactMethod {

    HOME_PHONE(1), MOBILE_PHONE(2), WORK_PHONE(3), EMAIL(4), EMPLOYER_PHONE(5);

    private final int id;

    ContactMethod(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ContactMethod getById(int id) {

        for(ContactMethod contactMethod : ContactMethod.values()) {

            if(id == contactMethod.getId()) {
                return contactMethod;
            }

        }

        throw new IllegalArgumentException("No contact method with getId "+id+" exists!");
        
    }

}
