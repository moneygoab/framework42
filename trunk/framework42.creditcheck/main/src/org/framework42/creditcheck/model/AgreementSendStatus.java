package org.framework42.creditcheck.model;

public enum AgreementSendStatus {

    UNKNOWN(99), NOT_SENT(0), PRINTED_BY_CUSTOMER(2), SENT_BY_MONEYPAL(1), SENT_BY_PRINTSHOP_MAILIT(20);

    private final int id;

    AgreementSendStatus(int id) {

        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static AgreementSendStatus getById(int id) {

        for(AgreementSendStatus status: AgreementSendStatus.values()) {

            if(status.getId() == id) {

                return status;
            }
        }

        throw new IllegalArgumentException("No agreement send status with id "+id+" exists!");
    }

}
