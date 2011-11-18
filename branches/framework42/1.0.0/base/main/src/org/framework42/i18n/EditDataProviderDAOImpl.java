package org.framework42.i18n;

import org.framework42.dao.ProxyDAO;
import org.framework42.database.CommitType;
import org.framework42.database.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class EditDataProviderDAOImpl extends ProxyDAO<EditDataProviderDAOImpl> implements EditDataProviderDAO {

    public EditDataProviderDAOImpl(DatabaseConnector databaseConnector) {

        super("org.nummer42.i18n", databaseConnector);
    }

    @Override
    public Map<Locale, List<I18NEditObject>> findAll() {

        Connection con = databaseConnector.getPooledConnection(CommitType.AUTO);
        PreparedStatement ps = null;
        try {

            ps = prepareFindAll(con);

            return executeFind(ps);

        } catch (SQLException e) {
            throw new RuntimeException(generateSQLErrorMessage(e, getClass(), getClass().getEnclosingMethod()));
        } finally {
            databaseConnector.releasePooledConnection(con, ps);
        }
    }

    private PreparedStatement prepareFindAll(Connection con) throws SQLException {

        String query = "SELECT * FROM i18n_text ORDER BY key_value";

        return con.prepareStatement(query);
    }

    @Override
    public Map<Locale, List<I18NEditObject>> findAllOfTypeAndGroup(I18NType type, int groupId) {

        Connection con = databaseConnector.getPooledConnection(CommitType.AUTO);
        PreparedStatement ps = null;
        try {

            ps = prepareFindAllOfTypeAndGroup(con, type, groupId);

            return executeFind(ps);

        } catch (SQLException e) {
            throw new RuntimeException(generateSQLErrorMessage(e, getClass(), getClass().getEnclosingMethod()));
        } finally {
            databaseConnector.releasePooledConnection(con, ps);
        }
    }

    private PreparedStatement prepareFindAllOfTypeAndGroup(Connection con, I18NType type, int groupId) throws SQLException {

        String query = "SELECT * FROM i18n_text ";

        if(groupId != 0 && type != null) {
            query += "WHERE ";
        }

        if(type != null) {

            query += "main_type = ? ";

        }

        if(groupId != 0 && type != null) {

            query += "AND group_id = ? ";

        } else if(groupId != 0) {

            query += "group_id = ? ";
        } else {
            throw new IllegalArgumentException("Both type and group can't be null!");
        }

        query += "ORDER BY key_value";

        PreparedStatement ps = con.prepareStatement(query);

        if(type != null) {

            ps.setInt(1, type.getId());

        }
        if(groupId != 0 && type != null) {

            ps.setInt(2, groupId);

        }else if(groupId != 0) {

            ps.setInt(1, groupId);
        }

        return ps;
    }

    private Map<Locale, List<I18NEditObject>> executeFind(PreparedStatement ps) throws SQLException {

        Map<Locale, List<I18NEditObject>> found = new HashMap<Locale, List<I18NEditObject>>();

        ResultSet rs = ps.executeQuery();

        while(rs.next()) {

            I18NEditObject editObject = createFromResultSet(rs);

            if(found.containsKey(editObject.getLocaleList())) {

                found.get(editObject.getLocaleList()).add(editObject);

            } else {

                //found.put(editObject.getLocaleList(), new ArrayList<I18NEditObject>());
                found.get(editObject.getLocaleList()).add(editObject);
            }

        }

        return found;
    }

    @Override
    public Map<Locale, List<I18NEditObject>> findByKey(String key) {

        Connection con = databaseConnector.getPooledConnection(CommitType.AUTO);
        PreparedStatement ps = null;
        try {

            ps = prepareFindByKey(con, key);

            return executeFind(ps);

        } catch (SQLException e) {
            throw new RuntimeException(generateSQLErrorMessage(e, getClass(), getClass().getEnclosingMethod()));
        } finally {
            databaseConnector.releasePooledConnection(con, ps);
        }
    }

    private PreparedStatement prepareFindByKey(Connection con, String key) throws SQLException {

        String query = "SELECT * FROM i18n_text WHERE key_value like ? ORDER BY key_value";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, "%"+key+"%");

        return ps;
    }

    @Override
    public void add(List<I18NEditObject> valuesToAdd) {

        Connection con = databaseConnector.getPooledConnection(CommitType.AUTO);
        PreparedStatement ps = null;
        try {

            prepareAndExecuteAdd(con, valuesToAdd);

            //return executeAdd(ps);

        } catch (SQLException e) {
            throw new RuntimeException(generateSQLErrorMessage(e, getClass(), getClass().getEnclosingMethod()));
        } finally {
            databaseConnector.releasePooledConnection(con, ps);
        }
    }

    private void prepareAndExecuteAdd(Connection con, List<I18NEditObject> valuesToAdd) throws SQLException {

        String query = "INSERT INTO i18n_text(locale, main_type, group_id, key_value, value) VALUES(?, ?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(query);

        for(I18NEditObject editObject: valuesToAdd) {

            //ps.setString(1, editObject.getLocaleList().getLanguage()+"_"+editObject.getLocaleList().getCountry());
            ps.setInt(2, editObject.getType().getId());
            ps.setInt(3, editObject.getGroupId());
            ps.setString(4, editObject.getKey());
            ps.setString(5, editObject.getValue());

            ps.execute();
        }

    }

    @Override
    public void update(List<I18NEditObject> valuesToUpdate) {

        Connection con = databaseConnector.getPooledConnection(CommitType.AUTO);
        PreparedStatement ps = null;
        try {

            prepareAndExecuteUpdate(con, valuesToUpdate);

            //return executeAdd(ps);

        } catch (SQLException e) {
            throw new RuntimeException(generateSQLErrorMessage(e, getClass(), getClass().getEnclosingMethod()));
        } finally {
            databaseConnector.releasePooledConnection(con, ps);
        }
    }

    private void prepareAndExecuteUpdate(Connection con, List<I18NEditObject> valuesToUpdate) throws SQLException {

        String query = "UPDATE i18n_text SET locale = ?, main_type = ?, group_id = ?, key_value = ?, value = ? WHERE id = ?";

        PreparedStatement ps = con.prepareStatement(query);

        for(I18NEditObject editObject: valuesToUpdate) {

            //ps.setString(1, editObject.getLocaleList().getLanguage()+"_"+editObject.getLocaleList().getCountry());
            ps.setInt(2, editObject.getType().getId());
            ps.setInt(3, editObject.getGroupId());
            ps.setString(4, editObject.getKey());
            ps.setString(5, editObject.getValue());

            ps.setInt(6, editObject.getId());

            ps.execute();
        }

    }

    @Override
    public void delete(List<I18NEditObject> valuesToDelete) {

        Connection con = databaseConnector.getPooledConnection(CommitType.AUTO);
        PreparedStatement ps = null;
        try {

            prepareAndExecuteDelete(con, valuesToDelete);

            //return executeAdd(ps);

        } catch (SQLException e) {
            throw new RuntimeException(generateSQLErrorMessage(e, getClass(), getClass().getEnclosingMethod()));
        } finally {
            databaseConnector.releasePooledConnection(con, ps);
        }

    }

    private void prepareAndExecuteDelete(Connection con, List<I18NEditObject> valuesToDelete) throws SQLException {

        String query = "DELETE FROM i18n_text WHERE id = ?";

        PreparedStatement ps = con.prepareStatement(query);

        for(I18NEditObject editObject: valuesToDelete) {

            ps.setInt(1, editObject.getId());

            ps.execute();
        }

    }

    private I18NEditObject createFromResultSet(ResultSet rs) throws SQLException {

        /*return new I18NEditObject(
                rs.getInt("id"),
                new Locale(rs.getString("locale").substring(0,2), rs.getString("locale").substring(3)),
                I18NType.getById(rs.getInt("main_type")),
                rs.getInt("group_id"),
                rs.getString("key_value"),
                rs.getString("value")
        );*/
        return null;
    }

}

