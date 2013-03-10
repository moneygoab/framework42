package org.framework42.database;

import br.com.gennex.connectionpool.MiniConnectionPoolManager;
import org.apache.log4j.Logger;

import javax.sql.ConnectionPoolDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.framework42.utils.NullChecker.notNull;

/**
 * Service that handles database connections through a light weight connection pool.
 * */
public enum DatabaseConnector {

    INSTANCE(), EVENT_INSTANCE(), OLD_INSTANCE(), MISC_INSTANCE();

    private MiniConnectionPoolManager poolManager;

    private final static Logger logger = Logger.getLogger("org.framework42.base");


    private DatabaseConnector() {

    }

    /**
     * A init method that sets up the data source, must be called before use.
     * @param dataSource            The data source to your actual database.
     * @param maxConnections        The max number of connections that should be created simultaneously.
     * */
    public void setUpEnvironment(ConnectionPoolDataSource dataSource, int maxConnections) {

        notNull(dataSource, "Data source can't be null!");

        poolManager = new MiniConnectionPoolManager(dataSource, maxConnections);

    }

    /**
     * A init method that sets up the data source, must be called before use.
     * @param dataSource            The data source to your actual database.
     * @param maxConnections        The max number of connections that should be created simultaneously.
     * @param timeoutInSeconds      The max number of seconds that a database call should wait before terminating.
     * @param idleTimeInSeconds     The max number of seconds that a connection should idle before terminating.
     * */
    public void setUpEnvironment(ConnectionPoolDataSource dataSource, int maxConnections, int timeoutInSeconds, int idleTimeInSeconds) {

        notNull(dataSource, "Data source can't ber null!");

        poolManager = new MiniConnectionPoolManager(dataSource, maxConnections, timeoutInSeconds, idleTimeInSeconds);

    }

    /**
     * Simply returns a database connection for use.
     * @param commitType        If auto commit should be active or not on the connection.
     * @return The connection retrieved from the connection pool.
     * */
    public Connection getPooledConnection(CommitType commitType) {

        notNull(commitType, "Commit type can't be null!");

        try {

            Connection con = poolManager.getConnection();

            if(commitType==CommitType.CONTROL){
                con.setAutoCommit(false);
            }

            return con;

        } catch(SQLException e) {
            logger.fatal("Could not get connection from pool "+e);
            throw new RuntimeException("Could not get connection from pool "+e);
        } catch(NullPointerException e) {
            logger.fatal("Pool manager not created, you must call setUpEnvironment before use! "+e);
            throw new RuntimeException("Pool manager not created, you must call setUpEnvironment before use! "+e);
        }

    }

    /**
     * Releases the connection back to the pool so other calls might use it. If this isn't called the connection
     * will eventually timeout.
     * @param con       The connection to release.
     * @param ps        The prepared statement to release.
     * */
    public void releasePooledConnection(Connection con, PreparedStatement ps) {

        notNull(con, "Connection can't be null!");

        try {

            if(!con.getAutoCommit()) {
                con.commit();
            }

            if(ps!=null) {
                ps.close();
            }

            con.close();

        } catch(SQLException e) {

            logger.error("Problem releasing pooled connection "+e);
        }

    }

    /**
     * Rollbacks the connections activities.
     * @param con       The connection to rollback.
     * */
    public void rollbackPooledConnection(Connection con){

        notNull(con, "Connection can't be null!");

        try {

            con.rollback();
            con.close();

        } catch(SQLException e) {

            logger.error("Problem with rollback of pooled connection "+e);
        }

    }

    /**
     * Commits the connections activities.
     * @param con       The connection to rollback.
     * */
    public void commitPooledConnection(Connection con){

        notNull(con, "Connection can't be null!");

        try {

            con.commit();

        } catch(SQLException e) {

            logger.error("Problem with commit of pooled connection "+e);
        }

    }

}
