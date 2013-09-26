package org.framework42.ticketsystem.dao.impl;

import org.framework42.dao.ProxyDAO;
import org.framework42.database.CommitType;
import org.framework42.database.DatabaseConnector;
import org.framework42.ticketsystem.dao.MilestoneDAO;
import org.framework42.ticketsystem.dao.TicketDAO;
import org.framework42.ticketsystem.model.Ticket;
import org.framework42.ticketsystem.model.TicketPriority;
import org.framework42.ticketsystem.model.TicketStatus;
import org.framework42.ticketsystem.model.impl.TicketImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOImpl extends ProxyDAO<TicketDAOImpl> implements TicketDAO {

    private final MilestoneDAO milestoneDAO;

    public TicketDAOImpl(DatabaseConnector databaseConnector, MilestoneDAO milestoneDAO) {
        super("org.framework42.ticketsystem.dao", databaseConnector);

        this.milestoneDAO = milestoneDAO;
    }

    @Override
    public Ticket add(Ticket ticket) {

        Connection con = databaseConnector.getPooledConnection(CommitType.AUTO);
        PreparedStatement ps = null;
        try {

            ps = prepareAdd(con, ticket);

            return executeAdd(ps, ticket);

        } catch (SQLException e) {
            throw new RuntimeException(generateSQLErrorMessage(e, getClass(), getClass().getEnclosingMethod()));
        } finally {
            databaseConnector.releasePooledConnection(con, ps);
        }
    }

    private PreparedStatement prepareAdd(Connection con, Ticket ticket) throws SQLException {

        String query = "INSERT INTO ticket(parent_id, title, status, priority, milestone, added_date, status_changed_date, description) VALUES(?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, ticket.getParentId());
        ps.setString(2, ticket.getTitle());
        ps.setInt(3, ticket.getStatus().getId());
        ps.setInt(4, ticket.getPriority().getId());
        ps.setInt(5, ticket.getMilestone().getId());
        ps.setTimestamp(6, new Timestamp(ticket.getAddedDate().getTime()));
        ps.setTimestamp(7, new Timestamp(ticket.getStatusChangedDate().getTime()));
        ps.setString(8, ticket.getDescription());

        return ps;
    }

    private Ticket executeAdd(PreparedStatement ps, Ticket ticket) throws SQLException {

        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        return new TicketImpl(
                rs.getInt(1),
                ticket.getParentId(),
                ticket.getTitle(),
                ticket.getStatus(),
                ticket.getPriority(),
                ticket.getMilestone(),
                ticket.getAddedDate(),
                ticket.getStatusChangedDate(),
                ticket.getDescription()
        );
    }

    @Override
    public void update(Ticket ticket) {

        Connection con = databaseConnector.getPooledConnection(CommitType.AUTO);
        PreparedStatement ps = null;
        try {

            ps = prepareUpdate(con, ticket);

            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(generateSQLErrorMessage(e, getClass(), getClass().getEnclosingMethod()));
        } finally {
            databaseConnector.releasePooledConnection(con, ps);
        }
    }

    private PreparedStatement prepareUpdate(Connection con, Ticket ticket) throws SQLException {

        String query = "UPDATE ticket SET parent_id = ?, title = ?, status = ?, priority = ?, milestone = ?, added_date = ?, status_changed_date = ?, description = ? WHERE id = ?";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, ticket.getParentId());
        ps.setString(2, ticket.getTitle());
        ps.setInt(3, ticket.getStatus().getId());
        ps.setInt(4, ticket.getPriority().getId());
        ps.setInt(5, ticket.getMilestone().getId());
        ps.setTimestamp(6, new Timestamp(ticket.getAddedDate().getTime()));
        ps.setTimestamp(7, new Timestamp(ticket.getStatusChangedDate().getTime()));
        ps.setString(8, ticket.getDescription());

        ps.setInt(9, ticket.getId());

        return ps;
    }

    @Override
    public void changeStatus(int ticketId, TicketStatus newStatus) {

        Connection con = databaseConnector.getPooledConnection(CommitType.AUTO);
        PreparedStatement ps = null;
        try {

            ps = prepareChangeStatus(con, ticketId, newStatus);

            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(generateSQLErrorMessage(e, getClass(), getClass().getEnclosingMethod()));
        } finally {
            databaseConnector.releasePooledConnection(con, ps);
        }
    }

    private PreparedStatement prepareChangeStatus(Connection con, int ticketId, TicketStatus newStatus) throws SQLException {

        String query = "UPDATE ticket SET status = ?, status_changed_date = now() WHERE id = ?";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, newStatus.getId());
        ps.setInt(2, ticketId);

        return ps;
    }

    @Override
    public Ticket getById(int id) {

        Connection con = databaseConnector.getPooledConnection(CommitType.AUTO);
        PreparedStatement ps = null;
        try {

            ps = prepareGetById(con, id);

            return executeGetById(ps);

        } catch (SQLException e) {
            throw new RuntimeException(generateSQLErrorMessage(e, getClass(), getClass().getEnclosingMethod()));
        } finally {
            databaseConnector.releasePooledConnection(con, ps);
        }
    }

    private PreparedStatement prepareGetById(Connection con, int id) throws SQLException {

        String query = "SELECT * FROM ticket WHERE id = ?";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, id);

        return ps;
    }

    private Ticket executeGetById(PreparedStatement ps) throws SQLException {

        ResultSet rs = ps.executeQuery();
        rs.next();

        return createFromResultSet(rs);
    }

    @Override
    public List<Ticket> getAllInStatus(TicketStatus status) {

        Connection con = databaseConnector.getPooledConnection(CommitType.AUTO);
        PreparedStatement ps = null;
        try {

            ps = prepareGetAllInStatus(con, status);

            return executeGetAll(ps);

        } catch (SQLException e) {
            throw new RuntimeException(generateSQLErrorMessage(e, getClass(), getClass().getEnclosingMethod()));
        } finally {
            databaseConnector.releasePooledConnection(con, ps);
        }
    }

    private PreparedStatement prepareGetAllInStatus(Connection con, TicketStatus status) throws SQLException {

        String query = "SELECT * FROM ticket WHERE status = ? ORDER BY title";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, status.getId());

        return ps;
    }

    @Override
    public List<Ticket> getAllInStatuses(List<TicketStatus> statusList) {

        Connection con = databaseConnector.getPooledConnection(CommitType.AUTO);
        PreparedStatement ps = null;
        try {

            ps = prepareGetAllInStatuses(con, statusList);

            return executeGetAll(ps);

        } catch (SQLException e) {
            throw new RuntimeException(generateSQLErrorMessage(e, getClass(), getClass().getEnclosingMethod()));
        } finally {
            databaseConnector.releasePooledConnection(con, ps);
        }
    }

    private PreparedStatement prepareGetAllInStatuses(Connection con, List<TicketStatus> statusList) throws SQLException {

        String query = "SELECT * FROM ticket WHERE ";

        for(int i=0;i<statusList.size();i++) {

            query = "status = ?";

            if(i<statusList.size()-1) {

                query += " OR ";
            }
        }

        query   += " ORDER BY milestone, title";

        PreparedStatement ps = con.prepareStatement(query);

        int i = 1;
        for(TicketStatus status: statusList) {

            ps.setInt(i, status.getId());

            i++;
        }

        return ps;
    }

    @Override
    public List<Ticket> getAllInMilestone(int milestoneId) {

        Connection con = databaseConnector.getPooledConnection(CommitType.AUTO);
        PreparedStatement ps = null;
        try {

            ps = prepareGetAllInMilestone(con, milestoneId);

            return executeGetAll(ps);

        } catch (SQLException e) {
            throw new RuntimeException(generateSQLErrorMessage(e, getClass(), getClass().getEnclosingMethod()));
        } finally {
            databaseConnector.releasePooledConnection(con, ps);
        }
    }

    private PreparedStatement prepareGetAllInMilestone(Connection con, int milestoneId) throws SQLException {

        String query = "SELECT * FROM ticket WHERE milestone = ? ORDER BY title";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, milestoneId);

        return ps;
    }

    @Override
    public List<Ticket> getAllInMilestoneWithStatues(int milestoneId, List<TicketStatus> statusList) {

        Connection con = databaseConnector.getPooledConnection(CommitType.AUTO);
        PreparedStatement ps = null;
        try {

            ps = prepareGetAllInMilestoneWithStatus(con, milestoneId, statusList);

            return executeGetAll(ps);

        } catch (SQLException e) {
            throw new RuntimeException(generateSQLErrorMessage(e, getClass(), getClass().getEnclosingMethod()));
        } finally {
            databaseConnector.releasePooledConnection(con, ps);
        }
    }

    private PreparedStatement prepareGetAllInMilestoneWithStatus(Connection con, int milestoneId, List<TicketStatus> statusList) throws SQLException {

        String query = "SELECT * FROM ticket WHERE milestone = ? AND (";

        for(int i=0;i<statusList.size();i++) {

            query = "status = ?";

            if(i<statusList.size()-1) {

                query += " OR ";
            }
        }

        query   += ") ORDER BY title";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, milestoneId);

        int i = 2;
        for(TicketStatus status: statusList) {

            ps.setInt(i, status.getId());

            i++;
        }

        return ps;
    }

    private List<Ticket> executeGetAll(PreparedStatement ps) throws SQLException {

        List<Ticket> foundList = new ArrayList<Ticket>();

        ResultSet rs = ps.executeQuery();

        while(rs.next()) {

            foundList.add(createFromResultSet(rs));
        }

        return foundList;
    }

    private Ticket createFromResultSet(ResultSet rs) throws SQLException {

        return new TicketImpl(
                rs.getInt("id"),
                rs.getInt("parent_id"),
                rs.getString("title"),
                TicketStatus.getById(rs.getInt("status")),
                TicketPriority.getById(rs.getInt("priority")),
                milestoneDAO.getById(rs.getInt("milestone")),
                new Date(rs.getTimestamp("added_date").getTime()),
                new Date(rs.getTimestamp("status_changed_date").getTime()),
                rs.getString("description")
        );
    }

}
