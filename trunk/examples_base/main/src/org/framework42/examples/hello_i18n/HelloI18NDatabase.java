package org.framework42.examples.hello_i18n;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import org.apache.log4j.BasicConfigurator;
import org.framework42.database.DatabaseConnector;
import org.framework42.i18n.I18N;
import org.framework42.i18n.SimpleDatabaseI18NDataProvider;

import java.util.Locale;

public class HelloI18NDatabase {

    public static void main(String[] args) {

        new HelloI18NDatabase();

    }

    public HelloI18NDatabase() {

        BasicConfigurator.configure();

        setUpDatabase();

        I18N i18n = I18N.INSTANCE;

        Locale localeEnglish = new Locale("en", "EN");
        Locale localeSwedish = new Locale("sv", "SE");

        System.out.println(i18n.get("hello_i18n", localeEnglish));
        System.out.println(i18n.get("hello_i18n", localeSwedish));

    }

    public void setUpDatabase() {

        MysqlConnectionPoolDataSource cpds= new MysqlConnectionPoolDataSource();
        cpds.setUrl("jdbc:mysql://localhost:3306/i18n_test");
        cpds.setUser("user");
        cpds.setPassword("password");
        DatabaseConnector.INSTANCE.setUpEnvironment(cpds, 5);

        I18N i18n = I18N.INSTANCE;
        i18n.setUpI18N(new SimpleDatabaseI18NDataProvider(DatabaseConnector.INSTANCE));

    }

}
