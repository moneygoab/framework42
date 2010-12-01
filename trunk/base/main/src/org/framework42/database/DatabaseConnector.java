package org.framework42.database;

import br.com.gennex.connectionpool.MiniConnectionPoolManager;
import org.apache.log4j.Logger;

import javax.sql.ConnectionPoolDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Service that handles database connections through a light weight connection pool.
 * */
public enum DatabaseConnector {

    INSTANCE();

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

        poolManager = new MiniConnectionPoolManager(dataSource, maxConnections, timeoutInSeconds, idleTimeInSeconds);

    }

    /**
     * Simply returns a database connection for use.
     * @param commitType        If auto commit should be active or not on the connection.
     * @return The connection retrieved from the connection pool.
     * */
    public Connection getPooledConnection(CommitType commitType) {

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
     * */
    public void releasePooledConnection(Connection con) {

        try{

            if(!con.getAutoCommit()){
                con.commit();
            }

            con.close();

        }catch(SQLException e){
            logger.warn("Problem releasing pooled connection "+e);
        }

    }

    /**
     * Rollbacks the connections activities.
     * @param con       The connection to rollback.
     * */
    public void rollbackPooledConnection(Connection con){

        try{
            if(con!=null){
                con.rollback();
            }
        }catch(SQLException e){
            logger.warn("Problem with rollback of pooled connection "+e);
        }

    }

}