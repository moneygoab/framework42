package org.framework42.address.model;

public enum ContactMethod {

    HOME_PHONE(1), MOBILE_PHONE(2), WORK_PHONE(3), EMAIL(4), EMPLOYER_PHONE(5), FAX(6), WEP_PAGE(7), INSTANT_MESSAGING(8), SOCIAL_MEDIA(9);

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

        throw new IllegalArgumentException("No contact method with getLevel "+id+" exists!");
        
    }

}
