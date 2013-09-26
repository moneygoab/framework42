package org.framework42.ticketsystem.web;

import org.framework42.i18n.I18N;
import org.framework42.ticketsystem.model.Ticket;
import org.framework42.web.components.ComponentGroup;
import org.framework42.web.components.HtmlComponent;
import org.framework42.web.pages.WebPage;
import org.framework42.web.session.UserSession;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class TicketsOverviewComponent extends HtmlComponent {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private ComponentGroup components;

    public TicketsOverviewComponent(UserSession session, Ticket ticket) {

        I18N i18n = I18N.INSTANCE;
        Locale locale = session.getLocale();

        ComponentGroup.Builder builder = new ComponentGroup.Builder();



        components = builder.build();
    }

    @Override
    protected void generateHtmlSpecific(WebPage page, HtmlComponent parent, boolean onSameRow) {

        htmlBuilder.append(components.getHtml(page, parent));
    }
}
