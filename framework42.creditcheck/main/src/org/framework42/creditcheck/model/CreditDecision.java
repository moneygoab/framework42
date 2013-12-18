package org.framework42.creditcheck.model;

public enum CreditDecision {

    NOT_DECIDED_YET(0), APPROVED(1), REJECTED(2), REVIEW_REQUIRED(3), SUSPECTED_FRAUD(4), CANCELED(5);

    private final int id;

    CreditDecision(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static CreditDecision getByUCCode(String ucCode) {

        if("J".equals(ucCode)) {
            return APPROVED;
        } else if("N".equals(ucCode)) {
            return REJECTED;
        } else if("P".equals(ucCode)) {
            return REVIEW_REQUIRED;
        }

        throw new IllegalArgumentException("No uc code '"+ucCode+"' exists!");
    }

    public static CreditDecision getById(int id) {

        for(CreditDecision creditDecision: CreditDecision.values()) {

            if(id == creditDecision.getId()) {

                return creditDecision;
            }
        }

        throw new IllegalArgumentException("No credit decision with id "+id+" exists!");

    }

}
