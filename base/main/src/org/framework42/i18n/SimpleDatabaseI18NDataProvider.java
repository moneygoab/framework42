package org.framework42.i18n;

import org.apache.log4j.Logger;
import org.framework42.database.CommitType;
import org.framework42.database.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;

/**
 * A default implementation for an data provider that collects I18N data from a database.
 * Assumes that there's a database table named i18n_text and i18n_url.
 * */
public class SimpleDatabaseI18NDataProvider implements I18NDataProvider {

    private final DatabaseConnector databaseConnector;

    private final Logger logger = Logger.getLogger("org.framework42");

    /**
     * The constructor
     * @param databaseConnector     The database connector for the database that i18n data will be retrieved from.
     * */
    public SimpleDatabaseI18NDataProvider(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    @Override
    /**
     * Builds the in memory storage for the localized texts from the database.
     * @return Returns the in memory storage of the localized texts.
     * */
    public HashMap<Locale, HashMap<String, String>> getLocalizedTexts() {

        HashMap<Locale, HashMap<String, String>> hashMap = new HashMap<Locale, HashMap<String, String>>();

        Connection con = databaseConnector.getPooledConnection(CommitType.AUTO);
        try {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM i18n_text ORDER BY keyValue");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Locale locale = buildLocale(rs);

                if (!hashMap.containsKey(locale)) {
                    hashMap.put(locale, new HashMap<String, String>());
                }

                String keyValue = rs.getString("keyValue");
                if(hashMap.get(locale).containsKey(keyValue)) {
                    logger.warn("Duplicate key in I18N, key: " + keyValue);
                }
                hashMap.get(locale).put(keyValue, rs.getString("value"));

            }

        } catch (SQLException e) {
            logger.fatal("SimpleDatabaseI18NDataProvider.getLocalizedTexts: " + e);
            throw new RuntimeException("SimpleDatabaseI18NDataProvider.getLocalizedTexts: " + e);
        } finally {
            databaseConnector.releasePooledConnection(con);
        }

        return hashMap;


    }

    @Override
    /**
     * Builds the in memory storage for the localized URLs from the database.
     * @return Returns the in memory storage of the localized URLs.
     * */
    public HashMap<Locale, HashMap<String, String>> getLocalizedURLs() {

        HashMap<Locale, HashMap<String, String>> hashMapURL = new HashMap<Locale, HashMap<String, String>>();

        Connection con = databaseConnector.getPooledConnection(CommitType.AUTO);

        try {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM i18n_url ORDER BY keyValue");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Locale locale = buildLocale(rs);

                if (!hashMapURL.containsKey(locale)) {
                    hashMapURL.put(locale, new HashMap<String, String>());
                }

                String keyValue = rs.getString("keyValue");
                if(hashMapURL.get(locale).containsKey(keyValue)) {
                    logger.warn("Duplicate key in I18N URL, key: " + keyValue);
                }
                hashMapURL.get(locale).put(keyValue, rs.getString("value"));

            }

        } catch (SQLException e) {
            logger.fatal("SimpleDatabaseI18NDataProvider.getLocalizedURLs: " + e);
            throw new RuntimeException("SimpleDatabaseI18NDataProvider.getLocalizedURLs: " + e);
        } finally {
            databaseConnector.releasePooledConnection(con);
        }

        return hashMapURL;

    }

    private Locale buildLocale(ResultSet rs) throws SQLException {

        try{

            String[] loc = rs.getString("locale").split("_");

            return new Locale(loc[0], loc[1]);

        } catch (ArrayIndexOutOfBoundsException e) {

            logger.fatal("Locale wrongly formatted in i18n database, faulty value: " + rs.getString("locale"));
            throw new InstantiationError("Locale wrongly formatted in i18n database, faulty value: " + rs.getString("locale"));
        }

    }

}
