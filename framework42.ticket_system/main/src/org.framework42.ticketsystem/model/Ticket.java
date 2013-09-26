package org.framework42.ticketsystem.model;

import java.util.Date;

public interface Ticket {

    public int getId();

    public int getParentId();

    public String getTitle();

    public TicketStatus getStatus();

    public TicketPriority getPriority();

    public Milestone getMilestone();

    public Date getAddedDate();

    public Date getStatusChangedDate();

    public String getDescription();

}
