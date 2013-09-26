package org.framework42.ticketsystem.dao;

import org.framework42.ticketsystem.model.Ticket;
import org.framework42.ticketsystem.model.TicketStatus;

import java.util.List;

public interface TicketDAO {

    public Ticket add(Ticket ticket);

    public void update(Ticket ticket);

    public void changeStatus(int ticketId, TicketStatus newStatus);

    public Ticket getById(int id);

    public List<Ticket> getAllInStatus(TicketStatus status);

    public List<Ticket> getAllInStatuses(List<TicketStatus> statusList);

    public List<Ticket> getAllInMilestone(int milestoneId);

    public List<Ticket> getAllInMilestoneWithStatues(int milestoneId, List<TicketStatus> statusList);

}
