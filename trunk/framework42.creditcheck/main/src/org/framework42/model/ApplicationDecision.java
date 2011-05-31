package org.framework42.model;

public enum ApplicationDecision {

    APPROVED(1), REVIEW(2), DENIED(3);

    private final int id;

    ApplicationDecision(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public ApplicationDecision getById(int id) {

        for(ApplicationDecision decision: ApplicationDecision.values()) {

            if(id == decision.getId()) {

                return decision;
            }
        }

        throw new IllegalArgumentException("No application decision with getId "+id+" exists!");
    }

}
