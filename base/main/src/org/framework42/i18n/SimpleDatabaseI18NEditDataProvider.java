package org.framework42.i18n;

import org.apache.log4j.Logger;
import org.framework42.database.DatabaseConnector;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class SimpleDatabaseI18NEditDataProvider implements I18NEditDataProvider {

    private final DatabaseConnector databaseConnector;

    private final Logger logger = Logger.getLogger("org.framework42");

    public SimpleDatabaseI18NEditDataProvider(DatabaseConnector databaseConnector) {

        this.databaseConnector = databaseConnector;
    }

    @Override
    public Map<Locale, List<I18NEditObject>> findAll() {

        return null;
    }

    @Override
    public Map<Locale, List<I18NEditObject>> findAllOfType(I18NType type) {

        return null;
    }

    @Override
    public Map<Locale, List<I18NEditObject>> findAllOfGroup(int groupId) {

        return null;
    }

    @Override
    public Map<Locale, List<I18NEditObject>> findAllOfTypeAndGroup(I18NType type, int groupId) {

        return null;
    }

    @Override
    public Map<Locale, List<I18NEditObject>> findByKey(String key) {

        return null;
    }

    @Override
    public void add(List<I18NEditObject> valuesToAdd) {

    }

    @Override
    public void update(List<I18NEditObject> valuesToUpdate) {

    }

    @Override
    public void delete(List<I18NEditObject> valuesToDelete) {

    }
}
