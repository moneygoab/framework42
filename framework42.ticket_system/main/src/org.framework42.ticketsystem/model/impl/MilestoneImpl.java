package org.framework42.ticketsystem.model.impl;

import org.framework42.ticketsystem.model.Milestone;

public class MilestoneImpl implements Milestone {

    private final int id;

    private final String title;

    public MilestoneImpl(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
