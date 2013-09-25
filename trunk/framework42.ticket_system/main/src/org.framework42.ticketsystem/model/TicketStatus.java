package org.framework42.ticketsystem.model;

public enum TicketStatus {

    NEW(1);

    private final int id;

    private TicketStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static TicketStatus getById(int id) {

        for(TicketStatus status: TicketStatus.values()) {

            if(status.getId()==id) {

                return status;
            }
        }

        throw new IllegalArgumentException("No ticket status with id "+id+" exists!");
    }

}
