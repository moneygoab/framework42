package org.framework42.i18n;

import org.apache.log4j.Logger;
import org.framework42.database.DatabaseConnector;
import org.framework42.services.ProxyService;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class SimpleDatabaseI18NEditDataProvider extends ProxyService<SimpleDatabaseI18NEditDataProvider> implements I18NEditDataProvider {

    private final EditDataProviderDAO dataProviderDAO;

    public SimpleDatabaseI18NEditDataProvider(EditDataProviderDAO dataProviderDAO) {
        super("org.framework42.i18n");

        this.dataProviderDAO = dataProviderDAO;
    }

    @Override
    public Map<Locale, List<I18NEditObject>> findAll() {

        return dataProviderDAO.findAll();
    }

    @Override
    public Map<Locale, List<I18NEditObject>> findAllOfType(I18NType type) {

        return dataProviderDAO.findAllOfTypeAndGroup(type, 0);
    }

    @Override
    public Map<Locale, List<I18NEditObject>> findAllOfGroup(int groupId) {

        return dataProviderDAO.findAllOfTypeAndGroup(null, groupId);
    }

    @Override
    public Map<Locale, List<I18NEditObject>> findAllOfTypeAndGroup(I18NType type, int groupId) {

        return dataProviderDAO.findAllOfTypeAndGroup(type, groupId);
    }

    @Override
    public Map<Locale, List<I18NEditObject>> findByKey(String key) {

        return dataProviderDAO.findByKey(key);
    }

    @Override
    public void add(List<I18NEditObject> valuesToAdd) {

        dataProviderDAO.add(valuesToAdd);
    }

    @Override
    public void update(List<I18NEditObject> valuesToUpdate) {

        dataProviderDAO.update(valuesToUpdate);
    }

    @Override
    public void delete(List<I18NEditObject> valuesToDelete) {

        dataProviderDAO.delete(valuesToDelete);
    }
}
