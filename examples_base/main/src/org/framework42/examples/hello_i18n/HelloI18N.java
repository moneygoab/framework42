package org.framework42.examples.hello_i18n;

import org.apache.log4j.BasicConfigurator;
import org.framework42.i18n.I18N;

import java.util.Locale;

public class HelloI18N {

    public static void main(String[] args) {

        BasicConfigurator.configure();

        I18N i18n = I18N.INSTANCE;
        i18n.setUpI18N(new HelloI18NDataProvider());

        Locale localeEnglish = new Locale("en", "EN");
        Locale localeSwedish = new Locale("sv", "SE");

        System.out.println(i18n.get("hello_i18n", localeEnglish));
        System.out.println(i18n.get("hello_i18n", localeSwedish));

    }

}

