package org.framework42.ticketsystem.model;

import java.sql.SQLException;

public enum TicketPriority {

    LOWEST(1), LOW(2), NORMAL(3), HIGH(4), HIGHEST(5);

    private final int id;

    private TicketPriority(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static TicketPriority getById(int id) {

        for(TicketPriority priority: TicketPriority.values()) {

            if(priority.getId()==id) {

                return priority;
            }
        }

        throw new IllegalArgumentException("No ticket priority with id "+id+" exists!");
    }

}
