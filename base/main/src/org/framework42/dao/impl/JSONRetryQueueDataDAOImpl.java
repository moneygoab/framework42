package org.framework42.dao.impl;

import org.framework42.dao.JSONRetryQueueDataDAO;
import org.framework42.dao.ProxyDAO;
import org.framework42.database.CommitType;
import org.framework42.database.DatabaseConnector;
import org.json.JSONRetryQueueData;
import org.json.JSONRetryQueueDataImpl;
import org.json.JSONRetryStatus;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.framework42.utils.NullChecker.notNull;

public class JSONRetryQueueDataDAOImpl extends ProxyDAO<JSONRetryQueueDataDAOImpl> implements JSONRetryQueueDataDAO {

    private final DatabaseConnector databaseConnector;

    public JSONRetryQueueDataDAOImpl(DatabaseConnector databaseConnector) {
        super("org.framework42", databaseConnector);

        this.databaseConnector = notNull(databaseConnector);
    }

    @Override
    public JSONRetryQueueData add(JSONRetryQueueData data) {

        Connection con = databaseConnector.getPooledConnection(CommitType.AUTO);
        PreparedStatement ps = null;
        try {

            ps = prepareAdd(con, data);

            return executeAdd(ps, data);

        } catch (SQLException e) {
            throw new RuntimeException(generateSQLErrorMessage(e, getClass(), getClass().getEnclosingMethod()));
        } finally {
            databaseConnector.releasePooledConnection(con, ps);
        }
    }

    private PreparedStatement prepareAdd(Connection con, JSONRetryQueueData data) throws SQLException {

        String query = "INSERT INTO json_retry_queue(status, added_date, last_retry_date, target_url, content_type, headers, post_data) VALUES(?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, data.getStatus().getId());
        ps.setTimestamp(2, Timestamp.valueOf(data.getAddedDate()));
        ps.setTimestamp(3, Timestamp.valueOf(data.getLastRetryDate()));
        ps.setString(4, data.getTargetURL());
        ps.setString(5, data.getContentType());
        ps.setString(6, data.getHeadersAsJson().toString());
        ps.setString(7, data.getPostData());

        return ps;
    }

    private JSONRetryQueueData executeAdd(PreparedStatement ps, JSONRetryQueueData data) throws SQLException {

        ps.execute();

        ResultSet rs = ps.getGeneratedKeys();

        rs.next();

        return new JSONRetryQueueDataImpl(
                rs.getInt(1),
                data.getStatus(),
                data.getAddedDate(),
                data.getLastRetryDate(),
                data.getTargetURL(),
                data.getContentType(),
                data.getHeaders(),
                data.getPostData()
        );
    }

    @Override
    public void updateStatus(int id, JSONRetryStatus newStatus) {

        Connection con = databaseConnector.getPooledConnection(CommitType.AUTO);
        PreparedStatement ps = null;
        try {

            ps = prepareUpdateStatus(con, id, newStatus);

            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(generateSQLErrorMessage(e, getClass(), getClass().getEnclosingMethod()));
        } finally {
            databaseConnector.releasePooledConnection(con, ps);
        }
    }

    private PreparedStatement prepareUpdateStatus(Connection con, int id, JSONRetryStatus newStatus) throws SQLException {

        String query = "UPDATE json_retry_queue SET status = ?, last_retry_date = ? WHERE id = ?";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, newStatus.getId());
        ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));

        ps.setInt(3, id);

        return ps;
    }

    @Override
    public List<JSONRetryQueueData> findAllInStatus(JSONRetryStatus status) {

        Connection con = databaseConnector.getPooledConnection(CommitType.AUTO);
        PreparedStatement ps = null;
        try {

            ps = prepareFindAllInStatus(con, status);

            return executeFindList(ps);

        } catch (SQLException e) {
            throw new RuntimeException(generateSQLErrorMessage(e, getClass(), getClass().getEnclosingMethod()));
        } finally {
            databaseConnector.releasePooledConnection(con, ps);
        }
    }

    private PreparedStatement prepareFindAllInStatus(Connection con, JSONRetryStatus status) throws SQLException {

        String query = "SELECT * FROM json_retry_queue WHERE status = ?";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, status.getId());

        return ps;
    }

    private List<JSONRetryQueueData> executeFindList(PreparedStatement ps) throws SQLException {

        List<JSONRetryQueueData> foundList = new ArrayList<>();

        return foundList;
    }

    private JSONRetryQueueData createFromResultSet(ResultSet rs) throws SQLException {

        return new JSONRetryQueueDataImpl(
                rs.getInt("id"),
                JSONRetryStatus.getById(rs.getInt("status")),
                rs.getTimestamp("created_date").toLocalDateTime(),
                rs.getTimestamp("last_retry_date").toLocalDateTime(),
                rs.getString("target_url"),
                rs.getString("content_type"),
                rs.getString("headers"),
                rs.getString("post_data")
        );
    }
}
