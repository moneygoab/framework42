package org.framework42.creditcheck.model;

public enum ApplicationStatus {

    INFORMATION_GATHERING_PROCESS(1), APPROVAL_PROCESS(2), AWAITING_OFFER_ANSWER(7), AGREEMENT_PROCESS(3), REGISTRATION_PROCESS(4), COMPLETED(5), CANCELLED(6);

    private final int id;

    ApplicationStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ApplicationStatus getById(int id) {

        for(ApplicationStatus status: ApplicationStatus.values()) {

            if(id == status.getId()) {

                return status;
            }
        }

        throw new IllegalArgumentException("No applications status with getId "+id+" exists!");
    }

}
