package org.framework42.ticketsystem.web;

import org.framework42.exceptions.ManageableException;
import org.framework42.exceptions.NotAuthorizedException;
import org.framework42.ticketsystem.dao.MilestoneDAO;
import org.framework42.ticketsystem.dao.TicketDAO;
import org.framework42.ticketsystem.model.TicketPriority;
import org.framework42.ticketsystem.model.TicketStatus;
import org.framework42.ticketsystem.model.impl.TicketImpl;
import org.framework42.web.exceptions.StopServletExecutionException;
import org.framework42.web.pagelogic.FormListener;
import org.framework42.web.pagemodel.PageModel;
import org.framework42.web.session.UserSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class AddTicketFormListener extends FormListener<UserSession, PageModel> {

    private final MilestoneDAO milestoneDAO;

    private final TicketDAO ticketDAO;

    public AddTicketFormListener(TicketDAO ticketDAO, MilestoneDAO milestoneDAO) {
        super("add_ticket", "org.framework42.ticketsystem.web");

        this.ticketDAO = ticketDAO;
        this.milestoneDAO = milestoneDAO;
    }

    @Override
    protected void handleFormEvent(HttpServletRequest req, HttpServletResponse resp, UserSession session, PageModel pageModel) throws NotAuthorizedException, ManageableException, StopServletExecutionException {

        ticketDAO.add(
                new TicketImpl(
                        0,
                        0,
                        pageModel.getInParameterAsString("title_field"),
                        TicketStatus.NEW,
                        TicketPriority.getById(pageModel.getInParameterAsInt("priority_field")),
                        milestoneDAO.getById(pageModel.getInParameterAsInt("milestone_field")),
                        new Date(),
                        new Date(),
                        pageModel.getInParameterAsString("description_area")
                )
        );
    }
}
