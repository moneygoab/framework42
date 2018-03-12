package org.framework42.i18n;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface EditDataProviderDAO {

    Map<Locale, List<I18NEditObject>> findAll();

    Map<Locale, List<I18NEditObject>> findAllOfTypeAndGroup(I18NType type, int groupId);

    Map<Locale, List<I18NEditObject>> findByKey(String key);

    void add(List<I18NEditObject> valuesToAdd);

    void update(String language, String key, String value);

    void update(List<I18NEditObject> valuesToUpdate);

    void delete(List<I18NEditObject> valuesToDelete);

}
