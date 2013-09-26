package org.framework42.ticketsystem.dao.impl;

import org.framework42.dao.ProxyDAO;
import org.framework42.database.CommitType;
import org.framework42.database.DatabaseConnector;
import org.framework42.ticketsystem.dao.MilestoneDAO;
import org.framework42.ticketsystem.model.Milestone;
import org.framework42.ticketsystem.model.impl.MilestoneImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MilestoneDAOImpl extends ProxyDAO<MilestoneDAOImpl> implements MilestoneDAO {

    public MilestoneDAOImpl(DatabaseConnector databaseConnector) {
        super("org.framework42.ticketsystem.dao", databaseConnector);
    }

    @Override
    public Milestone getById(int id) {

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

        String query = "SELECT * FROM milestone WHERE id = ?";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, id);

        return ps;
    }

    private Milestone executeGetById(PreparedStatement ps) throws SQLException {

        ResultSet rs = ps.executeQuery();
        rs.next();

        return createFromResultSet(rs);
    }

    @Override
    public List<Milestone> findAll() {

        Connection con = databaseConnector.getPooledConnection(CommitType.AUTO);
        PreparedStatement ps = null;
        try {

            ps = prepareFindAll(con);

            return executeFindAll(ps);

        } catch (SQLException e) {
            throw new RuntimeException(generateSQLErrorMessage(e, getClass(), getClass().getEnclosingMethod()));
        } finally {
            databaseConnector.releasePooledConnection(con, ps);
        }
    }

    private PreparedStatement prepareFindAll(Connection con) throws SQLException {

        String query = "SELECT * FROM milestone ORDER BY title";

        PreparedStatement ps = con.prepareStatement(query);

        return ps;
    }

    private List<Milestone> executeFindAll(PreparedStatement ps) throws SQLException {

        List<Milestone> foundList = new ArrayList<Milestone>();

        ResultSet rs = ps.executeQuery();

        while(rs.next()) {

            foundList.add(createFromResultSet(rs));
        }

        return foundList;
    }

    private Milestone createFromResultSet(ResultSet rs) throws SQLException {

        return new MilestoneImpl(
                rs.getInt("id"),
                rs.getString("title")
        );
    }

}
