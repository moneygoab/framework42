package org.framework42.i18n;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class I18NEditView {

    private Map<Locale, List<I18NEditObject>> viewData;

    public I18NEditView(Map<Locale, List<I18NEditObject>> viewData) {

        this.viewData = viewData;
    }

    public Map<Locale, List<I18NEditObject>> getViewData() {
        return viewData;
    }

}
