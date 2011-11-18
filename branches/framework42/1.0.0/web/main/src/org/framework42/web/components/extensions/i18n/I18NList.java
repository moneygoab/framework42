package org.framework42.web.components.extensions.i18n;

import org.framework42.i18n.I18NEditObject;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.components.standardhtml.Div;
import org.framework42.web.components.standardhtml.Table;
import org.framework42.web.components.standardhtml.TableData;
import org.framework42.web.components.standardhtml.TableRow;
import org.framework42.web.pages.WebPage;
import org.framework42.web.session.UserSession;

import java.util.*;

public class I18NList extends HtmlComponent {

    private Div component;

    private static Map<Locale, Map<String,String>> texts;

    public I18NList(UserSession session, Map<Locale, List<I18NEditObject>> editMap) {

        Locale locale = session.getLocale();

        setupTexts();

        Div.Builder builder = new Div.Builder("i18n_list_div");

        TableRow.Builder headlines = new TableRow.Builder();
        headlines.add(new TableData(texts.get(locale).get("key_headline")));
        headlines.add(new TableData(texts.get(locale).get("main_type")));
        headlines.add(new TableData(texts.get(locale).get("group_id")));

        Table.Builder table = new Table.Builder(headlines.build());

        Iterator<Locale> keys = editMap.keySet().iterator();

        while(keys.hasNext()) {
            Locale l = keys.next();
        }



        builder.add(table.build());

        component = builder.build();

    }

    private void setupTexts() {

        if(texts == null) {

            texts = new HashMap<Locale, Map<String, String>>();

            Map<String, String> swedish = new HashMap<String, String>();
            swedish.put("key_headline", "Nyckel");
            swedish.put("main_type", "Huvudtyp");
            swedish.put("group_id", "Grupperings id");

            texts.put(new Locale("sv", "SE"), swedish);

            Map<String, String> english = new HashMap<String, String>();
            english.put("key_headline", "Key");
            swedish.put("main_type", "Main type");
            swedish.put("group_id", "Group id");

            texts.put(Locale.ENGLISH, english);
            texts.put(Locale.US, english);
            texts.put(Locale.UK, english);
        }
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append(component.getHtml(page, parent, false));
    }
}
