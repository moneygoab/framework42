package org.framework42.i18n;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface EditDataProviderDAO {

    public Map<Locale, List<I18NEditObject>> findAll();

    public Map<Locale, List<I18NEditObject>> findAllOfTypeAndGroup(I18NType type, int groupId);

    public Map<Locale, List<I18NEditObject>> findByKey(String key);

    public void add(List<I18NEditObject> valuesToAdd);

    public void update(String language, String key, String value);

    public void update(List<I18NEditObject> valuesToUpdate);

    public void delete(List<I18NEditObject> valuesToDelete);

}
