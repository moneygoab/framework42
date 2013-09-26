package org.framework42.ticketsystem.dao;

import org.framework42.ticketsystem.model.Milestone;

import java.util.List;

public interface MilestoneDAO {

    public Milestone getById(int id);

    public List<Milestone> findAll();

}
