package org.framework42.ticketsystem.model.impl;

import org.framework42.ticketsystem.model.Milestone;
import org.framework42.ticketsystem.model.Ticket;
import org.framework42.ticketsystem.model.TicketStatus;

import java.util.Date;

public class TicketImpl implements Ticket {

    private final int id;

    private final int parentId;

    private final String title;

    private final TicketStatus status;

    private final Milestone milestone;

    private final long addedDate;

    private final long statusChangedDate;

    private final String description;

    public TicketImpl(int id, int parentId, String title, TicketStatus status, Milestone milestone, Date addedDate, Date statusChangedDate, String description) {
        this.id = id;
        this.parentId = parentId;
        this.title = title;
        this.status = status;
        this.milestone = milestone;
        this.addedDate = addedDate.getTime();
        this.statusChangedDate = statusChangedDate.getTime();
        this.description = description;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getParentId() {
        return parentId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public TicketStatus getStatus() {
        return status;
    }

    @Override
    public Milestone getMilestone() {
        return milestone;
    }

    @Override
    public Date getAddedDate() {
        return new Date(addedDate);
    }

    @Override
    public Date getStatusChangedDate() {
        return new Date(statusChangedDate);
    }

    @Override
    public String getDescription() {
        return description;
    }
}
