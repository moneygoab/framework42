package org.framework42.creditcheck.model;

public enum ApplicationProductCategory {

    LOAN(1),PAYMENT_SOLUTION(2);

    private final int id;

    ApplicationProductCategory(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ApplicationProductCategory getById(int id ){
        for(ApplicationProductCategory type:values()){
            if(type.getId() == id){
                return type;
            }
        }
        throw new RuntimeException("ApplicationProductCategory.Couldn't parse id " + id );
    }

}
