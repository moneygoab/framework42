package org.framework42.creditcheck.model;

public enum EmploymentType {

    UNKNOWN(0), PERMANENT(1), RETIRED(2), VICAR(3), SELF_EMPLOYED(4), STUDENT(5), ON_LEAVE(6), UNEMPLOYED(7), OTHER(8);

    private final int id;

    EmploymentType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EmploymentType getById(int id) {

        for(EmploymentType employmentType: EmploymentType.values()) {

            if(employmentType.getId() == id) {

                return employmentType;
            }
        }

        throw new IllegalArgumentException("No employment type with id "+id+" exists!");
    }

}
